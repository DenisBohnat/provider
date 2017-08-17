package by.epam.bohnat.provider.dao.util;

/**
 * Defines a set of String constants that describe occurred exceptions in the
 * DAO layer classes
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public final class ExceptionMessages {

	public static final String CLEAR_POOL_CONNECTIONS = "Error clearing connections from the pool.";
	public static final String CONNECTION_NOT_ALLOCATED = "Error while allocating the connection in the pool.";
	public static final String CONNECTION_NOT_CLOSE = "It is impossible to close the connection which is already closed.";
	public static final String CONNECTION_NOT_FOUND = "Connection was not found in the busy connections in the pool";
	public static final String CONNECTION_NOT_RETURNED = "Connection was not closed and returned to the pool";
	public static final String CONNECTION_NOT_TAKEN = "Failure during taking connection from the connection pool";
	public static final String INVALID_INITIALIZATION_PARAMETER = "Invalid connection pool initialization parameter! Please, check the properties file.";
	public static final String NOT_FIND_DATABASE_DRIVER = "Unable to find the driver for the database";
	public static final String POOL_NOT_DESTROY = "Connection pool has not been destroyed";
	public static final String POOL_NOT_INIT = "Connection pool has not been initialized";
	public static final String SQL_DELETE_FAILURE = "Failure during the SQL delete request execution";
	public static final String SQL_EXCEPTION_IN_POOL = "SQLException happened in ConnectionPool";
	public static final String SQL_INSERT_FAILURE = "Failure during the SQL insert request execution";
	public static final String SQL_SELECT_FAILURE = "Failure during the SQL select request execution";
	public static final String SQL_UPDATE_FAILURE = "Failure during the SQL update request execution";
	public static final String STATEMENT_NOT_CLOSED = "Result Set or Statement was not closed properly";
	public static final String UNKNOWN_DATA_SOURCE = "Unknown data source type.";
}
