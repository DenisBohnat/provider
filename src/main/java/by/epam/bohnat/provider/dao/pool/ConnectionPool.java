package by.epam.bohnat.provider.dao.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bohnat.provider.dao.pool.exception.ConnectionPoolException;
import by.epam.bohnat.provider.dao.util.ExceptionMessages;

/**
 * A pool of connections for database access.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public final class ConnectionPool {

	private static final Logger logger = LogManager.getLogger(ConnectionPool.class.getName());

	/**
	 * Connection pool object
	 */
	private static ConnectionPool instance;

	/**
	 * Connection pool storage. Represents a queue
	 */
	private BlockingQueue<Connection> freeConnections;

	/**
	 * A storage of connections in use.Represents a queue
	 */
	private BlockingQueue<Connection> busyConnections;

	/**
	 * The value of the database connection property associated with the
	 * database driver
	 */
	private String driverName;

	/**
	 * The value of the database connection property associated with URL of the
	 * database
	 */
	private String url;

	/**
	 * The value of the database connection property associated with the
	 * database user
	 */
	private String user;

	/**
	 * The value of the database connection property associated with a password
	 * for accessing the database
	 */
	private String password;

	/**
	 * The value of the database connection property associated with the number
	 * of connections in the pool
	 */
	private int poolSize;

	/**
	 * Constructs a connection pool by reading the connection properties from
	 * the properties file using the object {@code DBResourceManager}.
	 * <p>
	 * If an error occurred while reading the number of connections, the number
	 * of connections is set to the default value (10).
	 */
	private ConnectionPool() {
		DBResourceManager dbResourceManager = DBResourceManager.getInstance();
		this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
		this.url = dbResourceManager.getValue(DBParameter.DB_URL);
		this.user = dbResourceManager.getValue(DBParameter.DB_USER);
		this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
		try {
			this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
		} catch (NumberFormatException e) {
			logger.error(ExceptionMessages.INVALID_INITIALIZATION_PARAMETER, e);
			this.poolSize = 10;
		}
		logger.debug("All necessary properties have been setted to the connection pool");
	}

	/**
	 * Getting a Connection pool.
	 * 
	 * @return Connection pool
	 */
	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	/**
	 * Initializing the connection pool.
	 * 
	 * @throws ConnectionPoolException
	 *             if an error occurred while initializing the connection pool
	 */
	public void initConnectionPool() throws ConnectionPoolException {
		try {
			Class.forName(driverName);
			logger.debug("The database driver has been found");
			freeConnections = new ArrayBlockingQueue<Connection>(poolSize);
			busyConnections = new ArrayBlockingQueue<Connection>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				PoolConnection poolConnection = new PoolConnection(connection);
				freeConnections.add(poolConnection);
			}
			logger.debug("The connections have been added to the connection pool");
		} catch (SQLException e) {
			logger.error(ExceptionMessages.SQL_EXCEPTION_IN_POOL, e);
			throw new ConnectionPoolException(ExceptionMessages.SQL_EXCEPTION_IN_POOL, e);
		} catch (ClassNotFoundException e) {
			logger.error(ExceptionMessages.NOT_FIND_DATABASE_DRIVER, e);
			throw new ConnectionPoolException(ExceptionMessages.NOT_FIND_DATABASE_DRIVER, e);
		}
	}

	/**
	 * Destroying the connection pool.
	 * 
	 * @throws ConnectionPoolException
	 *             if there was an error when closing connections
	 */
	public void destroyConnectionPool() throws ConnectionPoolException {
		try {
			clearPoolConnections();
			logger.debug("The connection pool has been destroyed");
		} catch (SQLException e) {
			logger.error(ExceptionMessages.CLEAR_POOL_CONNECTIONS, e);
			throw new ConnectionPoolException(ExceptionMessages.CLEAR_POOL_CONNECTIONS, e);
		}
	}

	/**
	 * Getting the connection from the pool.
	 * 
	 * @return connection to the database
	 * @throws ConnectionPoolException
	 *             if an error occurred while getting the connection from the
	 *             free connection queue
	 */
	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = freeConnections.take();
			busyConnections.add(connection);
			logger.debug("The connection has been taken from the connection pool");
		} catch (InterruptedException e) {
			logger.error(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
			throw new ConnectionPoolException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		}
		return connection;
	}

	/**
	 * Returning the connection to the connection pool.
	 * 
	 * @param connection
	 *            connection to the database
	 * @throws ConnectionPoolException
	 *             if an error occurred while returning the connection to the
	 *             pool
	 */
	public void returnConnection(Connection connection) throws ConnectionPoolException {
		try {
			connection.close();
			logger.debug("The connection has been returned to the connection pool");
		} catch (SQLException e) {
			logger.error(ExceptionMessages.CONNECTION_NOT_RETURNED, e);
			throw new ConnectionPoolException(ExceptionMessages.CONNECTION_NOT_RETURNED, e);
		}
	}

	/**
	 * Closing all connections.
	 * 
	 * @throws SQLException
	 *             if an error occurred when closing connections
	 */
	private void clearPoolConnections() throws SQLException {
		closeConnectionsQueue(freeConnections);
		closeConnectionsQueue(busyConnections);
	}

	/**
	 * Closes all connections from the specified connection queue.
	 * 
	 * @param queue
	 *            connection queue
	 * @throws SQLException
	 *             if an error occurred when closing connections
	 */
	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			if (connection instanceof PoolConnection) {
				((PoolConnection) connection).reallyClose();
			}
		}
	}

	/**
	 * The inner {@code PoolConnection} implements the interface
	 * {@code Connection}.
	 * <p>
	 * The purpose of this class is to prevent closing connections on the
	 * application side. So this class overrides the {@code close()} method and
	 * adds a new method {@code reallyClose()}, which physically closes the
	 * connection to the database, but can only be called by the connection
	 * pool.
	 * 
	 * @author Denis Bohnat
	 * @version 1.0
	 */
	private class PoolConnection implements Connection {

		private Connection connection;

		public PoolConnection(Connection connection) throws SQLException {
			this.connection = connection;
			this.connection.setAutoCommit(true);
		}

		public void reallyClose() throws SQLException {
			connection.close();
		}

		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			return connection.unwrap(iface);
		}

		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return connection.isWrapperFor(iface);
		}

		@Override
		public Statement createStatement() throws SQLException {
			return connection.createStatement();
		}

		@Override
		public PreparedStatement prepareStatement(String sql) throws SQLException {
			return connection.prepareStatement(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {
			return connection.prepareCall(sql);
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {
			return connection.nativeSQL(sql);
		}

		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException {
			connection.setAutoCommit(autoCommit);
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			return connection.getAutoCommit();
		}

		@Override
		public void commit() throws SQLException {
			connection.commit();
		}

		@Override
		public void rollback() throws SQLException {
			connection.rollback();
		}

		@Override
		public void close() throws SQLException {
			if (connection.isClosed()) {
				throw new SQLException(ExceptionMessages.CONNECTION_NOT_CLOSE);
			}
			if (connection.isReadOnly()) {
				connection.setReadOnly(false);
			}
			if (!busyConnections.remove(this)) {
				throw new SQLException(ExceptionMessages.CONNECTION_NOT_FOUND);
			}
			if (!freeConnections.offer(this)) {
				throw new SQLException(ExceptionMessages.CONNECTION_NOT_ALLOCATED);
			}
		}

		@Override
		public boolean isClosed() throws SQLException {
			return connection.isClosed();
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			return connection.getMetaData();
		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			connection.setReadOnly(readOnly);
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			return connection.isReadOnly();
		}

		@Override
		public void setCatalog(String catalog) throws SQLException {
			connection.setCatalog(catalog);
		}

		@Override
		public String getCatalog() throws SQLException {
			return connection.getCatalog();
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			connection.setTransactionIsolation(level);
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			return connection.getTransactionIsolation();
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			return connection.getWarnings();
		}

		@Override
		public void clearWarnings() throws SQLException {
			connection.clearWarnings();
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			return connection.getTypeMap();
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			connection.setTypeMap(map);
		}

		@Override
		public void setHoldability(int holdability) throws SQLException {
			connection.setHoldability(holdability);

		}

		@Override
		public int getHoldability() throws SQLException {
			return connection.getHoldability();
		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			return connection.setSavepoint();
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {
			return connection.setSavepoint(name);
		}

		@Override
		public void rollback(Savepoint savepoint) throws SQLException {
			connection.rollback(savepoint);
		}

		@Override
		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			connection.releaseSavepoint(savepoint);
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			return connection.prepareStatement(sql, autoGeneratedKeys);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			return connection.prepareStatement(sql, columnIndexes);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			return connection.prepareStatement(sql, columnNames);
		}

		@Override
		public Clob createClob() throws SQLException {
			return connection.createClob();
		}

		@Override
		public Blob createBlob() throws SQLException {
			return connection.createBlob();
		}

		@Override
		public NClob createNClob() throws SQLException {
			return connection.createNClob();
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			return connection.createSQLXML();
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {
			return connection.isValid(timeout);
		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			connection.setClientInfo(name, value);
		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			connection.setClientInfo(properties);
		}

		@Override
		public String getClientInfo(String name) throws SQLException {
			return connection.getClientInfo(name);
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			return connection.getClientInfo();
		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			return connection.createArrayOf(typeName, elements);
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			return connection.createStruct(typeName, attributes);
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			connection.setSchema(schema);
		}

		@Override
		public String getSchema() throws SQLException {
			return connection.getSchema();
		}

		@Override
		public void abort(Executor executor) throws SQLException {
			connection.abort(executor);
		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			connection.setNetworkTimeout(executor, milliseconds);
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			return connection.getNetworkTimeout();
		}
	}

}
