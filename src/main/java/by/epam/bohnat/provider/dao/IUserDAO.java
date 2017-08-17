package by.epam.bohnat.provider.dao;

import java.util.List;

import by.epam.bohnat.provider.bean.User;
import by.epam.bohnat.provider.dao.exception.DAOException;

/**
 * Defines methods for implementing in the DAO layer for the user entity.
 * <p>
 * Methods of reading / writing data throw {@link DAOException}, indicating a
 * read / write error.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public interface IUserDAO {

	/**
	 * This method is used to add new user to the data source
	 * 
	 * @param user
	 *            object
	 * @return boolean variable that indicates that adding a new user entity to
	 *         the data source was successful
	 * @throws DAOException
	 */
	boolean addUser(User user) throws DAOException;

	/**
	 * This method is used to delete user from the data source by ID
	 * 
	 * @param id
	 *            user ID
	 * @throws DAOException
	 */
	void deleteUserById(int id) throws DAOException;

	/**
	 * This method is used to update user in the data source
	 * 
	 * @param user
	 *            object
	 * @return boolean variable that indicates that updating user entity in the
	 *         data source was successful
	 * @throws DAOException
	 */
	boolean updateUser(User user) throws DAOException;

	/**
	 * This method is used to search user in the data source by ID
	 * 
	 * @param id
	 *            user ID
	 * @return found user or null if it was not found
	 * @throws DAOException
	 */
	User getUserById(int id) throws DAOException;

	/**
	 * This method is used to search user in the data source by Login
	 * 
	 * @param login
	 *            user login
	 * @return found user or null if it was not found
	 * @throws DAOException
	 */
	User getUserByLogin(String login) throws DAOException;

	/**
	 * This method is used to return all users-customers from the data source
	 * 
	 * @return list of all users-customers
	 * @throws DAOException
	 */
	List<User> getUserList() throws DAOException;

	/**
	 * This method is used to return all administrators from the data source
	 * 
	 * @return list of all administrators
	 * @throws DAOException
	 */
	List<User> getAdminList() throws DAOException;

	/**
	 * This method is used to return user Password by user Login
	 * 
	 * @param login
	 *            user login
	 * @return string password or null if it was not found
	 * @throws DAOException
	 */
	String getPasswordByLogin(String login) throws DAOException;

	/**
	 * This method is used to get counts of all users-customers in the data
	 * source
	 * 
	 * @return total users-customers amount
	 * @throws DAOException
	 */
	int getNumberOfUsers() throws DAOException;

	/**
	 * This method is used to return necessary part of all users-customers from
	 * the data source
	 * 
	 * @param start
	 *            start index of users part
	 * @param amount
	 *            amount of users to be returned
	 * @return list of users-customers
	 * @throws DAOException
	 */
	List<User> getUsersListPart(int start, int amount) throws DAOException;

	/**
	 * This method is used to get counts of all users-administrators in the data
	 * source
	 * 
	 * @return total users-administrators amount
	 * @throws DAOException
	 */
	int getNumberOfAdmins() throws DAOException;

	/**
	 * This method is used to return necessary part of all users-administrators
	 * from the data source
	 * 
	 * @param start
	 *            start index of users part
	 * @param amount
	 *            amount of users to be returned
	 * @return list of users-administrators
	 * @throws DAOException
	 */
	List<User> getAdminsListPart(int start, int amount) throws DAOException;

}
