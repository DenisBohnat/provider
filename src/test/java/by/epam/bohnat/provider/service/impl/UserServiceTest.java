package by.epam.bohnat.provider.service.impl;

import java.sql.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.bohnat.provider.bean.User;
import by.epam.bohnat.provider.dao.pool.ConnectionPool;
import by.epam.bohnat.provider.dao.pool.exception.ConnectionPoolException;
import by.epam.bohnat.provider.service.IUserService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.user.EditUserServiceException;
import by.epam.bohnat.provider.service.exception.user.GetUserServiceException;
import by.epam.bohnat.provider.service.exception.user.ServiceSignUpException;
import by.epam.bohnat.provider.service.exception.user.UserLoginServiceException;

public class UserServiceTest {

	private static ConnectionPool pool;
	private static ServiceFactory serviceFactory;

	@BeforeClass
	public static void initTestContext() throws ConnectionPoolException {
		pool = ConnectionPool.getInstance();
		pool.initConnectionPool();
		serviceFactory = ServiceFactory.getInstance();
	}

	@Test(expected = ServiceSignUpException.class)
	public void testAddUserWithInvalidLogin() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		Date birthDate = Date.valueOf("1988-02-02");
		Date regDate = Date.valueOf("2017-06-12");
		User user = new User(1000, "Name", "Surname", "Log", "Password", "test@mail.test", birthDate, regDate,
				"124578965", 1);
		uService.addUser(user);
	}

	@Test(expected = ServiceSignUpException.class)
	public void testAddUserWithInvalidPassword() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		Date birthDate = Date.valueOf("1988-02-02");
		Date regDate = Date.valueOf("2017-06-12");
		User user = new User(1000, "Name", "Surname", "Login", "Passwo&rd", "test@mail.test", birthDate, regDate,
				"124578965", 1);
		uService.addUser(user);
	}

	@Test(expected = ServiceSignUpException.class)
	public void testAddUserWithInvalidName() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		Date birthDate = Date.valueOf("1988-02-02");
		Date regDate = Date.valueOf("2017-06-12");
		User user = new User(1000, "Name123", "Surname", "Login", "Password", "test@mail.test", birthDate, regDate,
				"124578965", 1);
		uService.addUser(user);
	}

	@Test(expected = ServiceSignUpException.class)
	public void testAddUserWithInvalidSurname() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		Date birthDate = Date.valueOf("1988-02-02");
		Date regDate = Date.valueOf("2017-06-12");
		User user = new User(1000, "Name", "Surname123", "Login", "Password", "test@mail.test", birthDate, regDate,
				"124578965", 1);
		uService.addUser(user);
	}

	@Test(expected = ServiceSignUpException.class)
	public void testAddUserWithInvalidBirthDate() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		Date birthDate = Date.valueOf("1920-02-02");
		Date regDate = Date.valueOf("2017-06-12");
		User user = new User(1000, "Name", "Surname123", "Login", "Password", "test@mail.test", birthDate, regDate,
				"124578965", 1);
		uService.addUser(user);
	}

	@Test(expected = ServiceSignUpException.class)
	public void testAddUserWithInvalidEmail() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		Date birthDate = Date.valueOf("1988-02-02");
		Date regDate = Date.valueOf("2017-06-12");
		User user = new User(1000, "Name", "Surname", "Login", "Password", "testmail.test", birthDate, regDate,
				"124578965", 1);
		uService.addUser(user);
	}

	@Test(expected = ServiceSignUpException.class)
	public void testAddUserWithInvalidPhone() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		Date birthDate = Date.valueOf("1988-02-02");
		Date regDate = Date.valueOf("2017-06-12");
		User user = new User(1000, "Name", "Surname", "Login", "Password", "test@mail.test", birthDate, regDate,
				"12457896", 1);
		uService.addUser(user);
	}

	@Test(expected = ServiceException.class)
	public void testDeleteUserWithInvalidId() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		uService.deleteUserById(0);
	}

	@Test(expected = EditUserServiceException.class)
	public void testUpdateUserWithInvalidId() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		Date birthDate = Date.valueOf("1988-02-02");
		Date regDate = Date.valueOf("2017-06-12");
		User user = new User(0, "Name", "Surname", "Login", "Password", "test@mail.test", birthDate, regDate,
				"124578965", 1);
		uService.updateUser(user);
	}

	@Test(expected = EditUserServiceException.class)
	public void testUpdateUserWithInvalidObject() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		User user = null;
		uService.updateUser(user);
	}

	@Test(expected = UserLoginServiceException.class)
	public void testLoginUserWithInvalidLogin() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		uService.loginUser("", "password");
	}

	@Test(expected = UserLoginServiceException.class)
	public void testLoginUserWithInvalidPassword() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		uService.loginUser("login", null);
	}

	@Test(expected = GetUserServiceException.class)
	public void testGetUserByIdWithInvalidId() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		User user = uService.getUserById(-2);
	}

	@Test(expected = GetUserServiceException.class)
	public void testGetNameByIdWithInvalidId() throws ServiceException {
		IUserService uService = serviceFactory.getUserService();
		String name = uService.getNameById(0);
	}

	@AfterClass
	public static void destroyTestContext() throws ConnectionPoolException {
		pool.destroyConnectionPool();
	}

}
