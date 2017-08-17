package by.epam.bohnat.provider.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import by.epam.bohnat.provider.bean.User;
import by.epam.bohnat.provider.dao.IUserDAO;
import by.epam.bohnat.provider.dao.exception.DAOException;
import by.epam.bohnat.provider.dao.pool.ConnectionPool;
import by.epam.bohnat.provider.dao.pool.exception.ConnectionPoolException;
import by.epam.bohnat.provider.dao.util.ExceptionMessages;

/**
 * {@code UserDaoImpl} is a {@code IUserDAO} interface implementation that works
 * with MySQL database. Connections are taken and returned to the pool in each
 * method.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see IUserDAO
 */
public class UserDaoImpl implements IUserDAO {

	/**
	 * Method that adding a new user to the data source.
	 * 
	 * @param user
	 *            object
	 * @return boolean variable that indicates that adding a new user entity to
	 *         the data source was successful
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public boolean addUser(User user) throws DAOException {
		boolean flag = false;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_INSERT_USER);
			ps.setString(1, user.getName());
			ps.setString(2, user.getSurname());
			ps.setString(3, user.getLogin());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.geteMail());
			ps.setDate(6, user.getBirthDate());
			ps.setDate(7, user.getRegDate());
			ps.setString(8, user.getPhone());
			ps.setInt(9, user.getRole());
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
	 * Method that deleting user from the data source.
	 * 
	 * @param id
	 *            user id
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public void deleteUserById(int id) throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_DELETE_USER_BY_ID);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(ExceptionMessages.SQL_DELETE_FAILURE, e);
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
	}

	/**
	 * Method that editing user in the data source.
	 * 
	 * @param user
	 *            object
	 * @return boolean variable that indicates that editing user entity in the
	 *         data source was successful
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public boolean updateUser(User user) throws DAOException {
		boolean flag = false;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_UPDATE_USER_BY_ID);
			ps.setString(1, user.getName());
			ps.setString(2, user.getSurname());
			ps.setString(3, user.getLogin());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.geteMail());
			ps.setDate(6, user.getBirthDate());
			ps.setDate(7, user.getRegDate());
			ps.setString(8, user.getPhone());
			ps.setInt(9, user.getRole());
			ps.setInt(10, user.getId());
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
	 * Method that gets user entity by ID from the data source.
	 * 
	 * @param id
	 *            user id
	 * @return found user or null if it was not found
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public User getUserById(int id) throws DAOException {
		User user = null;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_USER_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSurname(rs.getString(3));
				user.setLogin(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.seteMail(rs.getString(6));
				user.setBirthDate(rs.getDate(7));
				user.setRegDate(rs.getDate(8));
				user.setPhone(rs.getString(9));
				user.setRole(rs.getInt(10));
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
		return user;
	}

	/**
	 * Method that gets user entity by Login from the data source.
	 * 
	 * @param login
	 *            user login
	 * @return found user or null if it was not found
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public User getUserByLogin(String login) throws DAOException {
		User user = null;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_USER_BY_LOGIN);
			ps.setString(1, login);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSurname(rs.getString(3));
				user.setLogin(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.seteMail(rs.getString(6));
				user.setBirthDate(rs.getDate(7));
				user.setRegDate(rs.getDate(8));
				user.setPhone(rs.getString(9));
				user.setRole(rs.getInt(10));
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
		return user;
	}

	/**
	 * This method is used to return all users-customers from the data source
	 * 
	 * @return list of all users-customers
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public List<User> getUserList() throws DAOException {
		List<User> userList = new LinkedList<User>();
		ConnectionPool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(DBHelper.SQL_SELECT_USER_LIST);
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSurname(rs.getString(3));
				user.setLogin(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.seteMail(rs.getString(6));
				user.setBirthDate(rs.getDate(7));
				user.setRegDate(rs.getDate(8));
				user.setPhone(rs.getString(9));
				user.setRole(rs.getInt(10));
				userList.add(user);
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
		return userList;
	}

	/**
	 * This method is used to return all administrators from the data source
	 * 
	 * @return list of all administrators
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public List<User> getAdminList() throws DAOException {
		List<User> adminList = new LinkedList<User>();
		ConnectionPool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(DBHelper.SQL_SELECT_ADMIN_LIST);
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSurname(rs.getString(3));
				user.setLogin(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.seteMail(rs.getString(6));
				user.setBirthDate(rs.getDate(7));
				user.setRegDate(rs.getDate(8));
				user.setPhone(rs.getString(9));
				user.setRole(rs.getInt(10));
				adminList.add(user);
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
		return adminList;
	}

	/**
	 * This method is used to return user Password by user Login
	 * 
	 * @param login
	 *            user login
	 * @return string password or null if it was not found
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public String getPasswordByLogin(String login) throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_PASSWORD_BY_LOGIN);
			ps.setString(1, login);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
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
		return null;
	}

	/**
	 * This method is used to get counts of all users-customers in the data
	 * source
	 * 
	 * @return total users-customers amount
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public int getNumberOfUsers() throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(DBHelper.SQL_SELECT_COUNT_USERS);
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
	 * This method is used to return necessary part of all users-customers from
	 * the data source
	 * 
	 * @param start
	 *            position for getting users from data source
	 * @param amount
	 *            amount of users to be returned
	 * @return list of users-customers
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public List<User> getUsersListPart(int start, int amount) throws DAOException {
		List<User> userList = new LinkedList<User>();
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_USERS_LIST_PART);
			ps.setInt(1, start);
			ps.setInt(2, amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSurname(rs.getString(3));
				user.setLogin(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.seteMail(rs.getString(6));
				user.setBirthDate(rs.getDate(7));
				user.setRegDate(rs.getDate(8));
				user.setPhone(rs.getString(9));
				user.setRole(rs.getInt(10));
				userList.add(user);
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
		return userList;
	}

	/**
	 * This method is used to get counts of all administrators in the data
	 * source
	 * 
	 * @return total administrators amount
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public int getNumberOfAdmins() throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(DBHelper.SQL_SELECT_COUNT_ADMINS);
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
	 * This method is used to return necessary part of all administrators from
	 * the data source
	 * 
	 * @param start
	 *            position for getting administrators from data source
	 * @param amount
	 *            amount of users to be returned
	 * @return list of administrators
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public List<User> getAdminsListPart(int start, int amount) throws DAOException {
		List<User> userList = new LinkedList<User>();
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_ADMINS_LIST_PART);
			ps.setInt(1, start);
			ps.setInt(2, amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSurname(rs.getString(3));
				user.setLogin(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.seteMail(rs.getString(6));
				user.setBirthDate(rs.getDate(7));
				user.setRegDate(rs.getDate(8));
				user.setPhone(rs.getString(9));
				user.setRole(rs.getInt(10));
				userList.add(user);
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
		return userList;
	}

}
