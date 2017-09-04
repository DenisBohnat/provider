package by.epam.bohnat.provider.service;

import java.util.List;

import by.epam.bohnat.provider.bean.User;
import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Defines methods that receive parameters from Command implementations, verify
 * them, construct necessary entities if needed and then pass them to the DAO
 * layer, possibly getting some objects or primitive values back and passing
 * them further back to the commands.
 * <p>
 * Represents an interface of a service for user-related actions.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public interface IUserService {

	/**
	 * Verifies parameters and either passes to the DAO layer or throws an
	 * exception
	 * 
	 * @param id
	 *            user id
	 * @throws ServiceException
	 */
	void deleteUserById(int id) throws ServiceException;

	/**
	 * Verifies the input parameter and passes it to the DAO layer, receives the
	 * user entity, returns it back to the Controller layer or throws an
	 * exception
	 * 
	 * @param login
	 *            user login
	 * @return found user entity
	 * @throws ServiceException
	 */
	User getUserByLogin(String login) throws ServiceException;

	/**
	 * Verifies the input parameter and passes it to the DAO layer, receives the
	 * user entity, returns it back to the Controller layer or throws an
	 * exception
	 * 
	 * @param id
	 *            user id
	 * @return found user entity
	 * @throws ServiceException
	 */
	User getUserById(int id) throws ServiceException;

	/**
	 * Receives a list of all users from the DAO layer and passes it back to the
	 * Controller
	 * 
	 * @return list of all users
	 * @throws ServiceException
	 */
	List<User> getUserList() throws ServiceException;

	/**
	 * Receives a list of all users from the DAO layer and passes it back to the
	 * Controller
	 * 
	 * @return list of all administrators
	 * @throws ServiceException
	 */
	List<User> getAdminList() throws ServiceException;

	/**
	 * Attempts to authenticate and authorize a user with a given login and
	 * password
	 * 
	 * @param login
	 *            user login
	 * @param password
	 *            user password
	 * @return found user entity
	 * @throws ServiceException
	 */
	User loginUser(String login, String password) throws ServiceException;

	/**
	 * Constructs a new user entity based on input parameters received from the
	 * Controller layer, verifies them and either passes to the DAO layer or
	 * throws an exception
	 * 
	 * @param user
	 *            object
	 * @throws ServiceException
	 */
	void addUser(User user) throws ServiceException;

	/**
	 * Constructs an updated user entity based on input parameters received from
	 * the Controller layer, verifies them and either passes to the DAO layer or
	 * throws an exception
	 * 
	 * @param user
	 *            object
	 * @throws ServiceException
	 */
	void updateUser(User user) throws ServiceException;

	/**
	 * Verifies parameters and either passes to the DAO layer or throws an
	 * exception
	 * 
	 * @param id
	 *            user id
	 * @return user name
	 * @throws ServiceException
	 */
	String getNameById(int id) throws ServiceException;

	/**
	 * Verifies parameters and either passes to the DAO layer or throws an
	 * exception
	 * 
	 * @param id
	 *            user id
	 * @return user surname
	 * @throws ServiceException
	 */
	String getSurnameById(int id) throws ServiceException;

	/**
	 * Counts the number of pages needed to locate all users within the
	 * pagination
	 * 
	 * @param elementsPerPage
	 *            option
	 * @return number of pages
	 * @throws ServiceException
	 */
	int getNumberOfUserPages(int elementsPerPage) throws ServiceException;

	/**
	 * Receives a particular list of all users from the DAO layer depending on
	 * the current page and passes it back to the Controller layer or throws an
	 * exception
	 * 
	 * @param pageNumber
	 *            number of current page
	 * @param elementsPerPage
	 *            option
	 * @return list of users
	 * @throws ServiceException
	 */
	List<User> getUsersOnCurrentPage(int pageNumber, int elementsPerPage) throws ServiceException;

	/**
	 * Counts the number of pages needed to locate all administrators within the
	 * pagination
	 * 
	 * @param elementsPerPage
	 *            option
	 * @return number of pages
	 * @throws ServiceException
	 */
	int getNumberOfAdminsPages(int elementsPerPage) throws ServiceException;

	/**
	 * Receives a particular list of all administrators from the DAO layer
	 * depending on the current page and passes it back to the Controller layer
	 * or throws an exception
	 * 
	 * @param pageNumber
	 *            number of current page
	 * @param elementsPerPage
	 *            option
	 * @return list of administrators
	 * @throws ServiceException
	 */
	List<User> getAdminsOnCurrentPage(int pageNumber, int elementsPerPage) throws ServiceException;
}
