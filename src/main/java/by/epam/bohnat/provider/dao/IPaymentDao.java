package by.epam.bohnat.provider.dao;

import java.util.List;

import by.epam.bohnat.provider.bean.Payment;
import by.epam.bohnat.provider.dao.exception.DAOException;

/**
 * Defines methods for implementing in the DAO layer for the user payment
 * entity.
 * <p>
 * Methods of reading / writing data throw {@link DAOException}, indicating a
 * read / write error.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public interface IPaymentDao {

	/**
	 * This method is used to get counts of all current user payments in the
	 * data source
	 * 
	 * @param accountId
	 *            account id
	 * @return total current user payments amount
	 * @throws DAOException
	 */
	int getNumberOfPayments(int accountId) throws DAOException;

	/**
	 * This method is used to return necessary part of current user payments
	 * from the data source
	 * 
	 * @param start
	 *            start index of payments part
	 * @param amount
	 *            amount of payments to be returned
	 * @param accountId
	 *            account id
	 * @return list of payments
	 * @throws DAOException
	 */
	List<Payment> getPaymentsListPart(int start, int amount, int accountId) throws DAOException;
}
