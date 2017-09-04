package by.epam.bohnat.provider.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import by.epam.bohnat.provider.bean.Payment;
import by.epam.bohnat.provider.dao.IPaymentDao;
import by.epam.bohnat.provider.dao.exception.DAOException;
import by.epam.bohnat.provider.dao.pool.ConnectionPool;
import by.epam.bohnat.provider.dao.pool.exception.ConnectionPoolException;
import by.epam.bohnat.provider.dao.util.ExceptionMessages;

/**
 * {@code PaymentDAOImpl} is a {@code IPaymentDao} interface implementation that
 * works with MySQL database. Connections are taken and returned to the pool in
 * each method.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see IPaymentDao
 */
public class PaymentDAOImpl implements IPaymentDao {

	/**
	 * This method is used to get counts of current user payments in the data
	 * source
	 * 
	 * @param accountId
	 *            account id
	 * @return total payments amount
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public int getNumberOfPayments(int accountId) throws DAOException {
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_COUNT_PAYMENTS);
			ps.setInt(1, accountId);
			rs = ps.executeQuery();
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
		return 0;
	}

	/**
	 * This method is used to return necessary part of current user payments
	 * from the data source
	 * 
	 * @param start
	 *            position for getting payments from data source
	 * @param amount
	 *            amount of payments to be returned
	 * @param accountId
	 *            account id
	 * @throws DAOException
	 *             if some error occurred while processing data
	 * @see ConnectionPool
	 * @see DBHelper
	 */
	@Override
	public List<Payment> getPaymentsListPart(int start, int amount, int accountId) throws DAOException {
		List<Payment> paymentsList = new LinkedList<Payment>();
		ConnectionPool pool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.takeConnection();
			ps = con.prepareStatement(DBHelper.SQL_SELECT_PAYMENTS_LIST_PART);
			ps.setInt(1, accountId);
			ps.setInt(2, start);
			ps.setInt(3, amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				Payment payment = new Payment();
				payment.setId(rs.getInt(1));
				payment.setAccountId(rs.getInt(2));
				payment.setPaymentDate(rs.getDate(3));
				payment.setPaymentAmount(rs.getFloat(4));
				paymentsList.add(payment);
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
		return paymentsList;
	}

}
