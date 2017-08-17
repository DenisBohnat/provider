package by.epam.bohnat.provider.service.impl;

import java.util.LinkedList;
import java.util.List;

import by.epam.bohnat.provider.bean.User;
import by.epam.bohnat.provider.dao.DAOFactory;
import by.epam.bohnat.provider.dao.IUserDAO;
import by.epam.bohnat.provider.dao.exception.DAOException;
import by.epam.bohnat.provider.service.IUserService;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.user.EditUserServiceException;
import by.epam.bohnat.provider.service.exception.user.GetUserServiceException;
import by.epam.bohnat.provider.service.exception.user.ServiceSignUpException;
import by.epam.bohnat.provider.service.exception.user.UserLoginServiceException;
import by.epam.bohnat.provider.service.util.ExceptionMessages;
import by.epam.bohnat.provider.service.util.Hasher;
import by.epam.bohnat.provider.service.util.Validator;

/**
 * {@code UserServiceImpl} is a {@code IUserService} interface implementation
 * that works with {@code IUserDAO} implementation.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see IUserService
 */
public class UserServiceImpl implements IUserService {

	/**
	 * Number of users per page
	 */
	private static final int NUMBER_OF_USERS_ADMINS_ON_PAGE = 8;

	/**
	 * This method is used to delete user by ID and validate input data
	 * 
	 * @param id
	 *            user id
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see Validator
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public void deleteUserById(int id) throws ServiceException {

		if (!Validator.validateId(id)) {
			throw new ServiceException(ExceptionMessages.INVALID_ID);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			userDAO.deleteUserById(id);
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to get user by ID and validate input data
	 * 
	 * @param id
	 *            user id
	 * @return found user or null if it was not found
	 * @throws GetUserServiceException
	 *             if user entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see Validator
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public User getUserById(int id) throws ServiceException {

		if (!Validator.validateId(id)) {
			throw new GetUserServiceException(ExceptionMessages.INVALID_ID);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			User user = userDAO.getUserById(id);
			if (user == null) {
				throw new GetUserServiceException(ExceptionMessages.NO_USER_IN_DB);
			}
			return user;
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to get user by Login and validate input data
	 * 
	 * @param login
	 *            user login
	 * @return found user or null if it was not found
	 * @throws UserLoginServiceException
	 *             if user entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see Validator
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public User getUserByLogin(String login) throws ServiceException {

		if (!Validator.validateString(login)) {
			throw new UserLoginServiceException(ExceptionMessages.INVALID_LOGIN);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			User user = userDAO.getUserByLogin(login);
			if (user == null) {
				throw new UserLoginServiceException(ExceptionMessages.LOGIN_NOT_FOUND);
			}
			return user;
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to return all users-customers
	 * 
	 * @return list of users
	 * @throws GetUserServiceException
	 *             if list of users not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public List<User> getUserList() throws ServiceException {
		List<User> userList = new LinkedList<User>();
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			userList = userDAO.getUserList();
			if (userList == null) {
				throw new GetUserServiceException(ExceptionMessages.NO_USER_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return userList;
	}

	/**
	 * This method is used to return all administrators
	 * 
	 * @return list of administrators
	 * @throws GetUserServiceException
	 *             if list of administrators not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public List<User> getAdminList() throws ServiceException {
		List<User> adminList = new LinkedList<User>();
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			adminList = userDAO.getAdminList();
			if (adminList == null) {
				throw new GetUserServiceException(ExceptionMessages.NO_USER_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return adminList;
	}

	/**
	 * This method is used to get user by Login and Password and validate input
	 * data
	 * 
	 * @param login
	 *            user login
	 * @param password
	 *            user password
	 * @return found user or null if it was not found
	 * @throws UserLoginServiceException
	 *             if user entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see Validator
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public User loginUser(String login, String password) throws ServiceException {

		if (!Validator.vaidateLogin(login)) {
			throw new UserLoginServiceException(ExceptionMessages.INVALID_LOGIN);
		}

		if (!Validator.vaidatePassword(password)) {
			throw new UserLoginServiceException(ExceptionMessages.INVALID_PASSWORD);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			User user = userDAO.getUserByLogin(login);
			if (user == null) {
				throw new UserLoginServiceException(ExceptionMessages.LOGIN_NOT_FOUND);
			}
			String hasedPassword = Hasher.hashMd5(password);
			String userPassword = userDAO.getPasswordByLogin(login);
			if (!userPassword.equals(hasedPassword)) {
				throw new UserLoginServiceException(ExceptionMessages.INCORECT_PASSWORD);
			}
			return user;
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to add new user and validate input data
	 * 
	 * @param user
	 *            object
	 * @throws ServiceSignUpException
	 *             if user entity not added
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see Validator
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public void addUser(User user) throws ServiceException {

		if (!Validator.validateObject(user)) {
			throw new ServiceSignUpException(ExceptionMessages.INVALID_OBJECT);
		}

		if (!Validator.validateUserNameSurname(user.getName())) {
			throw new ServiceSignUpException(ExceptionMessages.INVALID_USER_NAME);
		}

		if (!Validator.validateUserNameSurname(user.getSurname())) {
			throw new ServiceSignUpException(ExceptionMessages.INVALID_USER_SURNAME);
		}

		if (!Validator.vaidateLogin(user.getLogin())) {
			throw new ServiceSignUpException(ExceptionMessages.INVALID_LOGIN);
		}

		if (!Validator.vaidatePassword(user.getPassword())) {
			throw new ServiceSignUpException(ExceptionMessages.INVALID_PASSWORD);
		}

		if (!Validator.validateEmail(user.geteMail())) {
			throw new ServiceSignUpException(ExceptionMessages.INVALID_EMAIL);
		}

		if (!Validator.validateBirtDate(user.getBirthDate())) {
			throw new ServiceSignUpException(ExceptionMessages.INVALID_BIRTH_DATE);
		}

		if (!Validator.validatePhone(user.getPhone())) {
			throw new ServiceSignUpException(ExceptionMessages.INVALID_PHONE);
		}

		if (!Validator.validateId(user.getRole())) {
			throw new ServiceSignUpException(ExceptionMessages.INVALID_USER_ROLE);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			User userDB = userDAO.getUserByLogin(user.getLogin());
			if (userDB != null) {
				throw new ServiceSignUpException(ExceptionMessages.ALREADY_EXIST);
			}
			String hasedPassword = Hasher.hashMd5(user.getPassword());
			user.setPassword(hasedPassword);
			boolean flag = userDAO.addUser(user);
			if (flag == false) {
				throw new ServiceSignUpException(ExceptionMessages.SIGN_UP_FAILED);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to update user and validate input data
	 * 
	 * @param user
	 *            object
	 * @throws EditUserServiceException
	 *             if user entity not updated
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see Validator
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public void updateUser(User user) throws ServiceException {

		if (!Validator.validateObject(user)) {
			throw new EditUserServiceException(ExceptionMessages.INVALID_OBJECT);
		}

		if (!Validator.validateId(user.getId())) {
			throw new EditUserServiceException(ExceptionMessages.INVALID_ID);
		}

		if (!Validator.validateUserNameSurname(user.getName())) {
			throw new EditUserServiceException(ExceptionMessages.INVALID_USER_NAME);
		}

		if (!Validator.validateUserNameSurname(user.getSurname())) {
			throw new EditUserServiceException(ExceptionMessages.INVALID_USER_SURNAME);
		}

		if (!Validator.vaidateLogin(user.getLogin())) {
			throw new EditUserServiceException(ExceptionMessages.INVALID_LOGIN);
		}

		if (!Validator.vaidatePassword(user.getPassword())) {
			throw new EditUserServiceException(ExceptionMessages.INVALID_PASSWORD);
		}

		if (!Validator.validateEmail(user.geteMail())) {
			throw new EditUserServiceException(ExceptionMessages.INVALID_EMAIL);
		}

		if (!Validator.validateBirtDate(user.getBirthDate())) {
			throw new EditUserServiceException(ExceptionMessages.INVALID_BIRTH_DATE);
		}

		if (!Validator.validateDate(user.getRegDate())) {
			throw new EditUserServiceException(ExceptionMessages.INVALID_REG_DATE);
		}

		if (!Validator.validatePhone(user.getPhone())) {
			throw new EditUserServiceException(ExceptionMessages.INVALID_PHONE);
		}

		if (!Validator.validateId(user.getRole())) {
			throw new EditUserServiceException(ExceptionMessages.INVALID_USER_ROLE);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			User userDB = userDAO.getUserByLogin(user.getLogin());
			if (userDB != null && user.getId() != userDB.getId()) {
				throw new EditUserServiceException(ExceptionMessages.ALREADY_EXIST);
			}
			String hasedPassword = Hasher.hashMd5(user.getPassword());
			user.setPassword(hasedPassword);
			boolean flag = userDAO.updateUser(user);
			if (flag == false) {
				throw new EditUserServiceException(ExceptionMessages.USER_NOT_UPDATED);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to get user name and validate input data
	 * 
	 * @param id
	 *            user id
	 * @return user name
	 * @throws GetUserServiceException
	 *             if user entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see Validator
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public String getNameById(int id) throws ServiceException {

		if (!Validator.validateId(id)) {
			throw new GetUserServiceException(ExceptionMessages.INVALID_ID);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			User user = userDAO.getUserById(id);
			if (user == null) {
				throw new GetUserServiceException(ExceptionMessages.NO_USER_IN_DB);
			}
			return user.getName();
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to get user surname and validate input data
	 * 
	 * @param id
	 *            user id
	 * @return user surname
	 * @throws GetUserServiceException
	 *             if user entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see Validator
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public String getSurnameById(int id) throws ServiceException {

		if (!Validator.validateId(id)) {
			throw new GetUserServiceException(ExceptionMessages.INVALID_ID);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			User user = userDAO.getUserById(id);
			if (user == null) {
				throw new GetUserServiceException(ExceptionMessages.NO_USER_IN_DB);
			}
			return user.getSurname();
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to get counts of pages needed to locate all users
	 * 
	 * @return total pages amount
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public int getNumberOfUserPages() throws ServiceException {
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			int numberOfUses = userDAO.getNumberOfUsers();
			if (numberOfUses % NUMBER_OF_USERS_ADMINS_ON_PAGE == 0) {
				return numberOfUses / NUMBER_OF_USERS_ADMINS_ON_PAGE;
			} else {
				return numberOfUses / NUMBER_OF_USERS_ADMINS_ON_PAGE + 1;
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to return necessary part of all users-customers and
	 * validate input data
	 * 
	 * @param pageNumber
	 *            page number
	 * @return list of users-customers
	 * @throws GetUserServiceException
	 *             if user entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see Validator
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public List<User> getUsersOnCurrentPage(int pageNumber) throws ServiceException {
		if (!Validator.validateInt(pageNumber)) {
			throw new GetUserServiceException(ExceptionMessages.INVALID_INT);
		}
		List<User> userList = new LinkedList<User>();
		int start = (pageNumber - 1) * NUMBER_OF_USERS_ADMINS_ON_PAGE;
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			userList = userDAO.getUsersListPart(start, NUMBER_OF_USERS_ADMINS_ON_PAGE);
			if (userList == null) {
				throw new GetUserServiceException(ExceptionMessages.NO_USER_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return userList;
	}

	/**
	 * This method is used to get counts of pages needed to locate all
	 * administrators
	 * 
	 * @return total pages amount
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public int getNumberOfAdminsPages() throws ServiceException {
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			int numberOfAdmins = userDAO.getNumberOfAdmins();
			if (numberOfAdmins % NUMBER_OF_USERS_ADMINS_ON_PAGE == 0) {
				return numberOfAdmins / NUMBER_OF_USERS_ADMINS_ON_PAGE;
			} else {
				return numberOfAdmins / NUMBER_OF_USERS_ADMINS_ON_PAGE + 1;
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to return necessary part of all administrators and
	 * validate input data
	 * 
	 * @param pageNumber
	 *            page number
	 * @return list of administrators
	 * @throws GetUserServiceException
	 *             if user entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see Validator
	 * @see DAOFactory
	 * @see IUserDAO
	 * @see ExceptionMessages
	 */
	@Override
	public List<User> getAdminsOnCurrentPage(int pageNumber) throws ServiceException {
		if (!Validator.validateInt(pageNumber)) {
			throw new GetUserServiceException(ExceptionMessages.INVALID_INT);
		}
		List<User> adminList = new LinkedList<User>();
		int start = (pageNumber - 1) * NUMBER_OF_USERS_ADMINS_ON_PAGE;
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IUserDAO userDAO = factory.getUserDAO();
			adminList = userDAO.getAdminsListPart(start, NUMBER_OF_USERS_ADMINS_ON_PAGE);
			if (adminList == null) {
				throw new GetUserServiceException(ExceptionMessages.NO_USER_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return adminList;
	}

}
