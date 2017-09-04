package by.epam.bohnat.provider.service;

import java.util.List;

import by.epam.bohnat.provider.bean.Account;
import by.epam.bohnat.provider.bean.Payment;
import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Defines methods that receive parameters from Command implementations, verify
 * them, construct necessary entities if needed and then pass them to the DAO
 * layer, possibly getting some objects or primitive values back and passing
 * them further back to the commands.
 * <p>
 * Represents an interface of a service for account-related actions.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public interface IAccountService {

	/**
	 * Verifies the input parameter and passes it to the DAO layer, receives the
	 * request entity, returns it back to the Controller layer or throws an
	 * exception
	 * 
	 * @param userId
	 *            user id
	 * @return found account entity
	 * @throws ServiceException
	 */
	Account getAccountByUserId(int userId) throws ServiceException;

	/**
	 * Verifies the input parameter and passes it to the DAO layer, receives the
	 * request entity, returns boolean variable
	 * 
	 * @param userId
	 *            user id
	 * @return boolean variable that indicates that the user has an account
	 * @throws ServiceException
	 */
	boolean isUserGetAccount(int userId) throws ServiceException;

	/**
	 * Constructs a new account entity based on input parameters received from
	 * the Controller layer, verifies them and either passes to the DAO layer or
	 * throws an exception
	 * 
	 * @param account
	 *            object
	 * @throws ServiceException
	 */
	void addAccount(Account account) throws ServiceException;

	/**
	 * Constructs an updated account entity based on input parameters received
	 * from the Controller layer, verifies them and either passes to the DAO
	 * layer or throws an exception
	 * 
	 * @param account
	 *            object
	 * @throws ServiceException
	 */
	void updateAccount(Account account) throws ServiceException;

	/**
	 * Verifies the input parameter and passes it to the DAO layer, receives the
	 * request entity, returns it back to the Controller layer or throws an
	 * exception
	 * 
	 * @param id
	 *            account id
	 * @return found account entity
	 * @throws ServiceException
	 */
	Account getAccountById(int id) throws ServiceException;

	/**
	 * Receives a particular list of all non-payers from the DAO layer depending
	 * on the current page and passes it back to the Controller layer or throws
	 * an exception
	 * 
	 * @return list of accounts
	 * @throws ServiceException
	 */
	List<Account> getAllNonPayersAccount() throws ServiceException;

	/**
	 * Counts the number of pages needed to locate all accounts within the
	 * pagination
	 * 
	 * @return number of pages
	 * @throws ServiceException
	 */
	int getNumberOfNonPayersPages() throws ServiceException;

	/**
	 * Receives a particular list of all accounts from the DAO layer depending
	 * on the current page and passes it back to the Controller layer or throws
	 * an exception
	 * 
	 * @param pageNumber
	 *            number of current page
	 * @return list of accounts
	 * @throws ServiceException
	 */
	List<Account> getNonPayersOnCurrentPage(int pageNumber) throws ServiceException;

	/**
	 * Verifies the input parameter and passes it to the DAO layer, receives the
	 * request entity, returns boolean variable
	 * 
	 * @param userId
	 *            user id
	 * @return boolean variable that indicates that the user account was blocked
	 * @throws ServiceException
	 */
	boolean isUserBlockedAccount(int userId) throws ServiceException;

	/**
	 * Verifies parameters and either passes to the DAO layer or throws an
	 * exception
	 * 
	 * @param id
	 *            account id
	 * @throws ServiceException
	 */
	void deleteAccountById(int id) throws ServiceException;

	/**
	 * Verifies parameters and either passes to the DAO layer or throws an
	 * exception
	 * 
	 * @param account
	 *            object
	 * @param requestId
	 *            request id
	 * @throws ServiceException
	 */
	void changeTariffPlan(Account account, int requestId) throws ServiceException;

	/**
	 * Verifies parameters and either passes to the DAO layer or throws an
	 * exception
	 * 
	 * @param account
	 *            object
	 * @param payment
	 *            object
	 * @throws ServiceException
	 */
	void bringMonthlyFee(Account account, Payment payment) throws ServiceException;

	/**
	 * Verifies parameters and either passes to the DAO layer or throws an
	 * exception
	 * 
	 * @param accountId
	 *            account id
	 * @throws ServiceException
	 */
	void terminateAccount(int accountId) throws ServiceException;
}
