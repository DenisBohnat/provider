package by.epam.bohnat.provider.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import by.epam.bohnat.provider.bean.Account;
import by.epam.bohnat.provider.dao.IAccountDAO;
import by.epam.bohnat.provider.dao.exception.DAOException;
import by.epam.bohnat.provider.dao.pool.ConnectionPool;
import by.epam.bohnat.provider.dao.pool.exception.ConnectionPoolException;
import by.epam.bohnat.provider.dao.util.ExceptionMessages;

/**
 * {@code AccountDAOImpl} is a {@code IAccountDAO} interface implementation that
 * works with MySQL database. Connections are taken and returned to the pool in
 * each method.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see IAccountDAO
 */
public class AccountDAOImpl implements IAccountDAO {

	/**
	 * Method that getting account entity by user ID from data source.
	 * 
	 * @param userId
	 *            user id
	 * @return found user account or null if it was not found
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public Account getAccountByUserId(int userId) throws DAOException {
		Account account = null;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_ACCOUNT_BY_USER);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				account = new Account();
				account.setId(rs.getInt(1));
				account.setTariffId(rs.getInt(2));
				account.setBlock(rs.getInt(3));
				account.setAccountNumber(rs.getLong(4));
				account.setAmount(rs.getFloat(5));
				account.setPaymentDate(rs.getDate(6));
				account.setSpentTraffic(rs.getInt(7));
				account.setUserId(userId);
			}
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) {
					try {
						pool.returnConnection(con);
					} catch (ConnectionPoolException e) {
						throw new DAOException(ExceptionMessages.CONNECTION_NOT_RETURNED, e);
					}
				}
			}
		}
		return account;
	}

	/**
	 * Method that adding new user account into data source.
	 * 
	 * @param account
	 *            object
	 * @return boolean variable that indicates that adding new user account
	 *         entity to the data source was successful
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public boolean addAccount(Account account) throws DAOException {
		boolean flag = false;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_INSERT_ACCOUNT);
			ps.setInt(1, account.getUserId());
			ps.setInt(2, account.getTariffId());
			ps.setInt(3, account.getBlock());
			ps.setLong(4, account.getAccountNumber());
			ps.setFloat(5, account.getAmount());
			ps.setDate(6, account.getPaymentDate());
			ps.setInt(7, account.getSpentTraffic());
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_INSERT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) {
					try {
						pool.returnConnection(con);
					} catch (ConnectionPoolException e) {
						throw new DAOException(ExceptionMessages.CONNECTION_NOT_RETURNED, e);
					}
				}
			}
		}
		return flag;
	}

	/**
	 * Method that editing user account in data source.
	 * 
	 * @param account
	 *            object
	 * @return boolean variable that indicates that updating user account entity
	 *         in the data source was successful
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public boolean updateAccount(Account account) throws DAOException {
		boolean flag = false;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_UPDATE_ACCOUNT);
			ps.setInt(1, account.getUserId());
			ps.setInt(2, account.getTariffId());
			ps.setInt(3, account.getBlock());
			ps.setLong(4, account.getAccountNumber());
			ps.setFloat(5, account.getAmount());
			ps.setDate(6, account.getPaymentDate());
			ps.setInt(7, account.getSpentTraffic());
			ps.setInt(8, account.getId());
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_UPDATE_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) {
					try {
						pool.returnConnection(con);
					} catch (ConnectionPoolException e) {
						throw new DAOException(ExceptionMessages.CONNECTION_NOT_RETURNED, e);
					}
				}
			}
		}
		return flag;
	}

	/**
	 * Method that getting account by ID from data source.
	 * 
	 * @param id
	 *            account id
	 * @return found user account or null if it was not found
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public Account getAccountById(int id) throws DAOException {
		Account account = null;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_ACCOUNT_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				account = new Account();
				account.setUserId(rs.getInt(1));
				account.setTariffId(rs.getInt(2));
				account.setBlock(rs.getInt(3));
				account.setAccountNumber(rs.getLong(4));
				account.setAmount(rs.getFloat(5));
				account.setPaymentDate(rs.getDate(6));
				account.setSpentTraffic(rs.getInt(7));
				account.setId(id);
			}
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) {
					try {
						pool.returnConnection(con);
					} catch (ConnectionPoolException e) {
						throw new DAOException(ExceptionMessages.CONNECTION_NOT_RETURNED, e);
					}
				}
			}
		}
		return account;
	}

	/**
	 * Method that getting non-payers from data source.
	 * 
	 * @return list of all accounts
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public List<Account> getAllNonPayersAccount() throws DAOException {
		List<Account> accList = new LinkedList<Account>();
		ConnectionPool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(DBHelper.SQL_SELECT_ALL_NON_PAYERS);
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt(1));
				account.setUserId(rs.getInt(2));
				account.setTariffId(rs.getInt(3));
				account.setBlock(rs.getInt(4));
				account.setAccountNumber(rs.getLong(5));
				account.setAmount(rs.getFloat(6));
				account.setPaymentDate(rs.getDate(7));
				account.setSpentTraffic(rs.getInt(8));
				accList.add(account);
			}
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) {
					try {
						pool.returnConnection(con);
					} catch (ConnectionPoolException e) {
						throw new DAOException(ExceptionMessages.CONNECTION_NOT_RETURNED, e);
					}
				}
			}
		}
		return accList;
	}

	/**
	 * This method is used to get counts of all non-payer accounts in the data
	 * source
	 * 
	 * @return total non-payer accounts amount
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public int getNumberOfNonPayers() throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(DBHelper.SQL_SELECT_COUNT_NON_PAYERS);
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) {
					try {
						pool.returnConnection(con);
					} catch (ConnectionPoolException e) {
						throw new DAOException(ExceptionMessages.CONNECTION_NOT_RETURNED, e);
					}
				}
			}
		}
		return 0;
	}

	/**
	 * This method is used to return necessary part of all non-payer accounts
	 * from the data source
	 * 
	 * @param start
	 *            position for getting non-payer accounts from data source
	 * @param amount
	 *            amount of non-payer accounts to be returned
	 * @return list of non-payer accounts
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public List<Account> getNonPayersListPart(int start, int amount) throws DAOException {
		List<Account> accList = new LinkedList<Account>();
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_NON_PAYERS_LIST_PART);
			ps.setInt(1, start);
			ps.setInt(2, amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt(1));
				account.setUserId(rs.getInt(2));
				account.setTariffId(rs.getInt(3));
				account.setBlock(rs.getInt(4));
				account.setAccountNumber(rs.getLong(5));
				account.setAmount(rs.getFloat(6));
				account.setPaymentDate(rs.getDate(7));
				account.setSpentTraffic(rs.getInt(8));
				accList.add(account);
			}
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_SELECT_FAILURE, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(ExceptionMessages.CONNECTION_NOT_TAKEN, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				throw new DAOException(ExceptionMessages.STATEMENT_NOT_CLOSED, e);
			} finally {
				if (con != null) {
					try {
						pool.returnConnection(con);
					} catch (ConnectionPoolException e) {
						throw new DAOException(ExceptionMessages.CONNECTION_NOT_RETURNED, e);
					}
				}
			}
		}
		return accList;
	}

}
