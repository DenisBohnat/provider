package by.epam.bohnat.provider.dao;

import java.util.List;

import by.epam.bohnat.provider.bean.Account;
import by.epam.bohnat.provider.dao.exception.DAOException;

/**
 * Defines methods for implementing in the DAO layer for the user account
 * entity.
 * <p>
 * Methods of reading / writing data throw {@link DAOException}, indicating a
 * read / write error.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public interface IAccountDAO {

	/**
	 * This method is used to search account in the data source by user ID
	 * 
	 * @param userId
	 *            user id
	 * @return found user account or null if it was not found
	 * @throws DAOException
	 */
	Account getAccountByUserId(int userId) throws DAOException;

	/**
	 * This method is used to add new user account to the data source
	 * 
	 * @param account
	 *            object
	 * @return boolean variable that indicates that adding new user account
	 *         entity to the data source was successful
	 * @throws DAOException
	 */
	boolean addAccount(Account account) throws DAOException;

	/**
	 * This method is used to update user account in the data source
	 * 
	 * @param account
	 *            object
	 * @return boolean variable that indicates that updating user account entity
	 *         in the data source was successful
	 * @throws DAOException
	 */
	boolean updateAccount(Account account) throws DAOException;

	/**
	 * This method is used to search account in the data source by ID
	 * 
	 * @param id
	 *            account id
	 * @return found user account or null if it was not found
	 * @throws DAOException
	 */
	Account getAccountById(int id) throws DAOException;

	/**
	 * This method is used to return all accounts from the data source
	 * 
	 * @return list of all accounts
	 * @throws DAOException
	 */
	List<Account> getAllNonPayersAccount() throws DAOException;

	/**
	 * This method is used to get counts of all non-payer accounts in the data
	 * source
	 * 
	 * @return total non-payer accounts amount
	 * @throws DAOException
	 */
	int getNumberOfNonPayers() throws DAOException;

	/**
	 * This method is used to return necessary part of all non-payer accounts
	 * from the data source
	 * 
	 * @param start
	 *            start index of non-payer accounts part
	 * @param amount
	 *            amount of non-payer accounts to be returned
	 * @return list of non-payer accounts
	 * @throws DAOException
	 */
	List<Account> getNonPayersListPart(int start, int amount) throws DAOException;
}
