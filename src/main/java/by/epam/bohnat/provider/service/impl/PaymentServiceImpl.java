package by.epam.bohnat.provider.service.impl;

import java.util.LinkedList;
import java.util.List;

import by.epam.bohnat.provider.bean.Payment;
import by.epam.bohnat.provider.dao.DAOFactory;
import by.epam.bohnat.provider.dao.IPaymentDao;
import by.epam.bohnat.provider.dao.exception.DAOException;
import by.epam.bohnat.provider.service.IPaymentService;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.payment.GetPaymentServiceException;
import by.epam.bohnat.provider.service.util.ExceptionMessages;
import by.epam.bohnat.provider.service.util.Validator;

/**
 * {@code PaymentServiceImpl} is a {@code IPaymentService} interface
 * implementation that works with {@code IPaymentDAO} implementation.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see IPaymentService
 */
public class PaymentServiceImpl implements IPaymentService {

	/**
	 * Number of payments per page
	 */
	private static final int NUMBER_OF_PAYMENTS_ON_PAGE = 6;

	/**
	 * This method is used to get counts of pages needed to locate all user
	 * payments
	 * 
	 * @param accountId
	 *            account id
	 * @return total pages amount
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IPaymentDao
	 */
	@Override
	public int getNumberOfPayments(int accountId) throws ServiceException {
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IPaymentDao pDAO = factory.getPaymentDAO();
			int numberOfPayments = pDAO.getNumberOfPayments(accountId);
			if (numberOfPayments % NUMBER_OF_PAYMENTS_ON_PAGE == 0) {
				return numberOfPayments / NUMBER_OF_PAYMENTS_ON_PAGE;
			} else {
				return numberOfPayments / NUMBER_OF_PAYMENTS_ON_PAGE + 1;
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to return necessary part of all current user payments
	 * and validate input data
	 * 
	 * @param pageNumber
	 *            page number
	 * @param accountId
	 *            account id
	 * @return list of payments
	 * @throws GetPaymentServiceException
	 *             if payment entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IPaymentDao
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public List<Payment> getPaymentsOnCurrentPage(int pageNumber, int accountId) throws ServiceException {
		if (!Validator.validateInt(pageNumber)) {
			throw new GetPaymentServiceException(ExceptionMessages.INVALID_INT);
		}

		List<Payment> paymentsList = new LinkedList<Payment>();
		int start = (pageNumber - 1) * NUMBER_OF_PAYMENTS_ON_PAGE;
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IPaymentDao pDAO = factory.getPaymentDAO();
			paymentsList = pDAO.getPaymentsListPart(start, NUMBER_OF_PAYMENTS_ON_PAGE, accountId);
			if (paymentsList.isEmpty()) {
				throw new GetPaymentServiceException(ExceptionMessages.NO_PAYMENTS_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return paymentsList;
	}

}
