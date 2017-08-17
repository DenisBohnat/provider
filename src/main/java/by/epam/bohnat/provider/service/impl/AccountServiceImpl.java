package by.epam.bohnat.provider.service.impl;

import java.util.LinkedList;
import java.util.List;

import by.epam.bohnat.provider.bean.Account;
import by.epam.bohnat.provider.dao.DAOFactory;
import by.epam.bohnat.provider.dao.IAccountDAO;
import by.epam.bohnat.provider.dao.exception.DAOException;
import by.epam.bohnat.provider.service.IAccountService;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.account.AddAccountServiceException;
import by.epam.bohnat.provider.service.exception.account.EditAccountServiceException;
import by.epam.bohnat.provider.service.exception.account.GetAccountServiceException;
import by.epam.bohnat.provider.service.util.ExceptionMessages;
import by.epam.bohnat.provider.service.util.Validator;

/**
 * {@code AccountServiceImpl} is a {@code IAccountService} interface
 * implementation that works with {@code IAccountDAO} implementation.
 * 
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see IAccountService
 */
public class AccountServiceImpl implements IAccountService {

	/**
	 * Number of requests per page
	 */
	private static final int NUMBER_OF_NON_PAYERS_ON_PAGE = 2;

	/**
	 * This method is used to get user account by user ID and validate input
	 * data
	 * 
	 * @param userId
	 *            user id
	 * @return found user account
	 * @throws GetAccountServiceException
	 *             if account entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IAccountDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public Account getAccountByUserId(int userId) throws ServiceException {

		if (!Validator.validateId(userId)) {
			throw new GetAccountServiceException(ExceptionMessages.INVALID_ID);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IAccountDAO aDAO = factory.getAccountDAO();
			Account account = aDAO.getAccountByUserId(userId);
			if (account == null) {
				throw new GetAccountServiceException(ExceptionMessages.NO_CURRENT_ACCOUNT_IN_DB);
			}
			return account;
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to check if user has an account
	 * 
	 * @param userId
	 *            user id
	 * @return boolean variable that indicates that the user has an account
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IAccountDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public boolean isUserGetAccount(int userId) throws ServiceException {

		if (!Validator.validateId(userId)) {
			throw new ServiceException(ExceptionMessages.INVALID_ID);
		}

		boolean flag = false;
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IAccountDAO aDAO = factory.getAccountDAO();
			Account account = aDAO.getAccountByUserId(userId);
			if (account != null) {
				flag = true;
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return flag;
	}

	/**
	 * This method is used to add new account and validate input data
	 * 
	 * @param account
	 *            object
	 * @throws AddAccountServiceException
	 *             if account entity not added
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IAccountDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public void addAccount(Account account) throws ServiceException {

		if (!Validator.validateObject(account)) {
			throw new AddAccountServiceException(ExceptionMessages.INVALID_OBJECT);
		}

		if (!Validator.validateId(account.getUserId())) {
			throw new AddAccountServiceException(ExceptionMessages.INVALID_ID);
		}

		if (!Validator.validateId(account.getTariffId())) {
			throw new AddAccountServiceException(ExceptionMessages.INVALID_ID);
		}

		if (!Validator.validateId(account.getBlock())) {
			throw new AddAccountServiceException(ExceptionMessages.INVALID_ID);
		}

		if (account.getAccountNumber() < 0) {
			throw new AddAccountServiceException(ExceptionMessages.INVALID_ACCOUNT_NUMBER);
		}

		if (!Validator.validateInt(account.getSpentTraffic())) {
			throw new AddAccountServiceException(ExceptionMessages.INVALID_SPENT_TRAFFIC);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IAccountDAO aDAO = factory.getAccountDAO();
			boolean flag = aDAO.addAccount(account);
			if (flag == false) {
				throw new AddAccountServiceException(ExceptionMessages.ACCOUNT_NOT_ADDED);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to update account and validate input data
	 * 
	 * @param account
	 *            object
	 * @throws EditAccountServiceException
	 *             if request entity not updated
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IAccountDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public void updateAccount(Account account) throws ServiceException {

		if (!Validator.validateObject(account)) {
			throw new EditAccountServiceException(ExceptionMessages.INVALID_OBJECT);
		}

		if (!Validator.validateId(account.getId())) {
			throw new EditAccountServiceException(ExceptionMessages.INVALID_ID);
		}

		if (!Validator.validateId(account.getUserId())) {
			throw new EditAccountServiceException(ExceptionMessages.INVALID_ID);
		}

		if (!Validator.validateId(account.getTariffId())) {
			throw new EditAccountServiceException(ExceptionMessages.INVALID_ID);
		}

		if (!Validator.validateId(account.getBlock())) {
			throw new EditAccountServiceException(ExceptionMessages.INVALID_ID);
		}

		if (account.getAccountNumber() < 0) {
			throw new EditAccountServiceException(ExceptionMessages.INVALID_ACCOUNT_NUMBER);
		}

		if (!Validator.validateInt(account.getSpentTraffic())) {
			throw new EditAccountServiceException(ExceptionMessages.INVALID_SPENT_TRAFFIC);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IAccountDAO aDAO = factory.getAccountDAO();
			boolean flag = aDAO.updateAccount(account);
			if (flag == false) {
				throw new EditAccountServiceException(ExceptionMessages.ACCOUNT_NOT_UPDATED);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to get user account by ID and validate input data
	 * 
	 * @param id
	 *            account id
	 * @return found user account
	 * @throws GetAccountServiceException
	 *             if account entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IAccountDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public Account getAccountById(int id) throws ServiceException {

		if (!Validator.validateId(id)) {
			throw new GetAccountServiceException(ExceptionMessages.INVALID_ID);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IAccountDAO aDAO = factory.getAccountDAO();
			Account account = aDAO.getAccountById(id);
			if (account == null) {
				throw new GetAccountServiceException(ExceptionMessages.NO_CURRENT_ACCOUNT_IN_DB);
			}
			return account;
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to get all non-payer accounts
	 * 
	 * @return list of accounts
	 * @throws GetAccountServiceException
	 *             if account entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IAccountDAO
	 */
	@Override
	public List<Account> getAllNonPayersAccount() throws ServiceException {
		List<Account> accList = new LinkedList<Account>();
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IAccountDAO aDAO = factory.getAccountDAO();
			accList = aDAO.getAllNonPayersAccount();
			if (accList.isEmpty()) {
				throw new GetAccountServiceException(ExceptionMessages.NO_CURRENT_ACCOUNT_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return accList;
	}

	/**
	 * This method is used to get counts of pages needed to locate all
	 * non-payers accounts
	 * 
	 * @return total pages amount
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IAccountDAO
	 */
	@Override
	public int getNumberOfNonPayersPages() throws ServiceException {
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IAccountDAO aDAO = factory.getAccountDAO();
			int numberOfNonPayers = aDAO.getNumberOfNonPayers();
			if (numberOfNonPayers % NUMBER_OF_NON_PAYERS_ON_PAGE == 0) {
				return numberOfNonPayers / NUMBER_OF_NON_PAYERS_ON_PAGE;
			} else {
				return numberOfNonPayers / NUMBER_OF_NON_PAYERS_ON_PAGE + 1;
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}

	}

	/**
	 * This method is used to return necessary part of all non-payer accounts
	 * and validate input data
	 * 
	 * @param pageNumber
	 *            page number
	 * @return list of accounts
	 * @throws GetAccountServiceException
	 *             if account entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IAccountDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public List<Account> getNonPayersOnCurrentPage(int pageNumber) throws ServiceException {
		if (!Validator.validateInt(pageNumber)) {
			throw new GetAccountServiceException(ExceptionMessages.INVALID_INT);
		}
		List<Account> accList = new LinkedList<Account>();
		int start = (pageNumber - 1) * NUMBER_OF_NON_PAYERS_ON_PAGE;
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IAccountDAO aDAO = factory.getAccountDAO();
			accList = aDAO.getNonPayersListPart(start, NUMBER_OF_NON_PAYERS_ON_PAGE);
			if (accList.isEmpty()) {
				throw new GetAccountServiceException(ExceptionMessages.NO_CURRENT_ACCOUNT_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return accList;
	}

}
