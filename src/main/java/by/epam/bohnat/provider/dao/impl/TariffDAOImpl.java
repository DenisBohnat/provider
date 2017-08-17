package by.epam.bohnat.provider.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import by.epam.bohnat.provider.bean.Tariff;
import by.epam.bohnat.provider.dao.ITariffDAO;
import by.epam.bohnat.provider.dao.exception.DAOException;
import by.epam.bohnat.provider.dao.pool.ConnectionPool;
import by.epam.bohnat.provider.dao.pool.exception.ConnectionPoolException;
import by.epam.bohnat.provider.dao.util.ExceptionMessages;

/**
 * {@code TariffDAOImpl} is a {@code ITariffDAO} interface implementation that
 * works with MySQL database. Connections are taken and returned to the pool in
 * each method.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ITariffDAO
 */
public class TariffDAOImpl implements ITariffDAO {

	/**
	 * Method that getting all tariffs from data source.
	 * 
	 * @return list of tariffs
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public List<Tariff> getAllTariffs() throws DAOException {
		List<Tariff> tariffList = new LinkedList<Tariff>();
		ConnectionPool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(DBHelper.SQL_SELECT_ALL_TARIFFS);
			while (rs.next()) {
				Tariff tariff = new Tariff();
				tariff.setId(rs.getInt(1));
				tariff.setName(rs.getString(2));
				tariff.setType(rs.getInt(3));
				tariff.setRecSpeed(rs.getFloat(4));
				tariff.setTransSpeed(rs.getFloat(5));
				tariff.setSubscriptionFee(rs.getFloat(6));
				tariff.setTrafficVolume(rs.getInt(7));
				tariff.setOverdraftAmount(rs.getFloat(8));
				tariffList.add(tariff);
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
		return tariffList;
	}

	/**
	 * Method that getting all unlimited tariffs from data source.
	 * 
	 * @return list of unlimited tariffs
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public List<Tariff> getUnlimitedTariffs() throws DAOException {
		List<Tariff> unlimTariffList = new LinkedList<Tariff>();
		ConnectionPool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(DBHelper.SQL_SELECT_UNLIM_TARIFFS);
			while (rs.next()) {
				Tariff tariff = new Tariff();
				tariff.setId(rs.getInt(1));
				tariff.setName(rs.getString(2));
				tariff.setType(rs.getInt(3));
				tariff.setRecSpeed(rs.getFloat(4));
				tariff.setTransSpeed(rs.getFloat(5));
				tariff.setSubscriptionFee(rs.getFloat(6));
				tariff.setTrafficVolume(0);
				tariff.setOverdraftAmount(0);
				unlimTariffList.add(tariff);
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
		return unlimTariffList;
	}

	/**
	 * Method that getting all limited tariffs from data source.
	 * 
	 * @return list of limited tariffs
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public List<Tariff> getLimitedTariffs() throws DAOException {
		List<Tariff> limTariffList = new LinkedList<Tariff>();
		ConnectionPool pool = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(DBHelper.SQL_SELECT_LIM_TARIFFS);
			while (rs.next()) {
				Tariff tariff = new Tariff();
				tariff.setId(rs.getInt(1));
				tariff.setName(rs.getString(2));
				tariff.setType(rs.getInt(3));
				tariff.setRecSpeed(rs.getFloat(4));
				tariff.setTransSpeed(rs.getFloat(5));
				tariff.setSubscriptionFee(rs.getFloat(6));
				tariff.setTrafficVolume(rs.getInt(7));
				tariff.setOverdraftAmount(rs.getFloat(8));
				limTariffList.add(tariff);
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
		return limTariffList;
	}

	/**
	 * Method that deleting tariff by ID from data source.
	 * 
	 * @param id
	 *            tariff id
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public void deleteTariffById(int id) throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_DELETE_TARIFF_BY_ID);
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
	 * Method that editing tariff in data source.
	 * 
	 * @param tariff
	 *            object
	 * @return boolean variable that indicates that updating tariff entity in
	 *         the data source was successful
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public boolean updateTariff(Tariff tariff) throws DAOException {
		boolean flag = false;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_UPDATE_TARIFF);
			ps.setInt(1, tariff.getType());
			ps.setString(2, tariff.getName());
			ps.setFloat(3, tariff.getRecSpeed());
			ps.setFloat(4, tariff.getTransSpeed());
			ps.setFloat(5, tariff.getSubscriptionFee());
			ps.setInt(6, tariff.getTrafficVolume());
			ps.setFloat(7, tariff.getOverdraftAmount());
			ps.setInt(8, tariff.getId());
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
	 * Method that adding new tariff into data source.
	 * 
	 * @param tariff
	 *            object
	 * @return boolean variable that indicates that adding new tariff entity to
	 *         the data source was successful
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public boolean addTariff(Tariff tariff) throws DAOException {
		boolean flag = false;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_INSERT_TARIFF);
			ps.setInt(1, tariff.getType());
			ps.setString(2, tariff.getName());
			ps.setFloat(3, tariff.getRecSpeed());
			ps.setFloat(4, tariff.getTransSpeed());
			ps.setFloat(5, tariff.getSubscriptionFee());
			ps.setInt(6, tariff.getTrafficVolume());
			ps.setFloat(7, tariff.getOverdraftAmount());
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
	 * Method that getting tariff by ID from data source.
	 * 
	 * @param id
	 *            tariff id
	 * @return found tariff or null if it was not found
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public Tariff getTariffById(int id) throws DAOException {
		Tariff tariff = null;
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_TARIFF_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				tariff = new Tariff();
				tariff.setId(rs.getInt(1));
				tariff.setName(rs.getString(2));
				tariff.setType(rs.getInt(3));
				tariff.setRecSpeed(rs.getFloat(4));
				tariff.setTransSpeed(rs.getFloat(5));
				tariff.setSubscriptionFee(rs.getFloat(6));
				tariff.setTrafficVolume(rs.getInt(7));
				tariff.setOverdraftAmount(rs.getFloat(8));
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
		return tariff;
	}

}
