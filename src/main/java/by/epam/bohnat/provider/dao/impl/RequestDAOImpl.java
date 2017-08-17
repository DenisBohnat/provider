package by.epam.bohnat.provider.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import by.epam.bohnat.provider.bean.Request;
import by.epam.bohnat.provider.dao.IRequestDAO;
import by.epam.bohnat.provider.dao.exception.DAOException;
import by.epam.bohnat.provider.dao.pool.ConnectionPool;
import by.epam.bohnat.provider.dao.pool.exception.ConnectionPoolException;
import by.epam.bohnat.provider.dao.util.ExceptionMessages;

/**
 * {@code RequestDAOImpl} is a {@code IRequestDAO} interface implementation that
 * works with MySQL database. Connections are taken and returned to the pool in
 * each method.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see IRequestDAO
 */
public class RequestDAOImpl implements IRequestDAO {

	/**
	 * Method that getting request by user ID from data source.
	 * 
	 * @param userId
	 *            user id
	 * @return found user request or null if it was not found
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public Request getRequestByUser(int userId) throws DAOException {
		Request request = null;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_REQUEST_BY_USER);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				request = new Request();
				request.setId(rs.getInt(1));
				request.setUserId(userId);
				request.setTariffId(rs.getInt(2));
				request.setDescription(rs.getString(3));
				request.setReqDate(rs.getDate(4));
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
		return request;
	}

	/**
	 * This method is used to return all requests from the data source.
	 * 
	 * @return list of all requests
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public List<Request> getAllRequests() throws DAOException {
		List<Request> requests = new LinkedList<Request>();
		ConnectionPool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(DBHelper.SQL_SELECT_ALL_REQUESTS);
			while (rs.next()) {
				Request request = new Request();
				request.setId(rs.getInt(1));
				request.setUserId(rs.getInt(2));
				request.setTariffId(rs.getInt(3));
				request.setDescription(rs.getString(4));
				request.setReqDate(rs.getDate(5));
				requests.add(request);
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
		return requests;
	}

	/**
	 * This method is used to delete user request from the data source by ID.
	 * 
	 * @param requestId
	 *            request id
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public void deleteRequestById(int requestId) throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_DELETE_REQUEST_BY_ID);
			ps.setInt(1, requestId);
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
	 * This method is used to search request in the data source by ID.
	 * 
	 * @param id
	 *            request id
	 * @return found user request or null if it was not found
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public Request getRequestById(int id) throws DAOException {
		Request request = null;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_REQUEST_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				request = new Request();
				request.setId(id);
				request.setUserId(rs.getInt(1));
				request.setTariffId(rs.getInt(2));
				request.setDescription(rs.getString(3));
				request.setReqDate(rs.getDate(4));
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
		return request;
	}

	/**
	 * Method that adding new request into the data source.
	 * 
	 * @param request
	 *            object
	 * @return boolean variable that indicates that adding new request entity to
	 *         the data source was successful
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public boolean sendRequest(Request request) throws DAOException {
		boolean flag = false;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_INSERT_REQUEST);
			ps.setInt(1, request.getUserId());
			ps.setInt(2, request.getTariffId());
			ps.setString(3, request.getDescription());
			ps.setDate(4, request.getReqDate());
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
	 * This method is used to get counts of all requests in the data source
	 * 
	 * @return total requests amount
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public int getNumberOfRequests() throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(DBHelper.SQL_SELECT_COUNT_REQUESTS);
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
	 * This method is used to return necessary part of all requests from the
	 * data source
	 * 
	 * @param start
	 *            position for getting requests from data source
	 * @param amount
	 *            amount of requests to be returned
	 * @return list of requests
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public List<Request> getRequestPart(int start, int amount) throws DAOException {
		List<Request> requests = new LinkedList<Request>();
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_REQUESTS_LIST_PART);
			ps.setInt(1, start);
			ps.setInt(2, amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				Request request = new Request();
				request.setId(rs.getInt(1));
				request.setUserId(rs.getInt(2));
				request.setTariffId(rs.getInt(3));
				request.setDescription(rs.getString(4));
				request.setReqDate(rs.getDate(5));
				requests.add(request);
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
		return requests;
	}

}
