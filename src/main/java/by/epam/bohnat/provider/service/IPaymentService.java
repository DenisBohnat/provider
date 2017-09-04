package by.epam.bohnat.provider.service;

import java.util.List;

import by.epam.bohnat.provider.bean.Payment;
import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Defines methods that receive parameters from Command implementations, verify
 * them, construct necessary entities if needed and then pass them to the DAO
 * layer, possibly getting some objects or primitive values back and passing
 * them further back to the commands.
 * <p>
 * Represents an interface of a service for payment-related actions.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public interface IPaymentService {

	/**
	 * Counts the number of pages needed to locate all current user payments
	 * within the pagination
	 * 
	 * @param accountId
	 *            account id
	 * @return number of pages
	 * @throws ServiceException
	 */
	int getNumberOfPayments(int accountId) throws ServiceException;

	/**
	 * Receives a particular list of all current user payments from the DAO
	 * layer depending on the current page and passes it back to the Controller
	 * layer or throws an exception
	 * 
	 * @param pageNumber
	 *            number of current page
	 * @param accountId
	 *            account id
	 * @return list of payments
	 * @throws ServiceException
	 */
	List<Payment> getPaymentsOnCurrentPage(int pageNumber, int accountId) throws ServiceException;
}
