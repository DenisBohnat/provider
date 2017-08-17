package by.epam.bohnat.provider.dao.impl;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.bohnat.provider.bean.User;
import by.epam.bohnat.provider.dao.DAOFactory;
import by.epam.bohnat.provider.dao.IUserDAO;
import by.epam.bohnat.provider.dao.exception.DAOException;
import by.epam.bohnat.provider.dao.pool.ConnectionPool;
import by.epam.bohnat.provider.dao.pool.exception.ConnectionPoolException;

public class UserDAOTest {

	private static ConnectionPool pool;
	private static DAOFactory daoFactory;
	private static User user;

	@BeforeClass
	public static void initTestContext() throws ConnectionPoolException {
		pool = ConnectionPool.getInstance();
		pool.initConnectionPool();
		daoFactory = DAOFactory.getInstance();

		user = new User();
		user.setId(0);
		user.setName("Test name");
		user.setSurname("Test surname");
		user.setLogin("Test login");
		user.setPassword("Test password");
		user.seteMail("Test eMail");
		Date birthDate = Date.valueOf("1988-02-22");
		user.setBirthDate(birthDate);
		Date regDate = Date.valueOf(LocalDate.now());
		user.setRegDate(regDate);
		user.setPhone("375125684264");
		user.setRole(1);
	}

	@Test
	public void testAddUser() throws DAOException {
		IUserDAO daoUser = daoFactory.getUserDAO();
		List<User> baforeUserList = daoUser.getUserList();
		boolean flag = daoUser.addUser(user);
		assertTrue(flag);
		List<User> afterUserList = daoUser.getUserList();
		assertEquals(baforeUserList.size() + 1, afterUserList.size());
		afterUserList.removeAll(baforeUserList);
		int addedUserId = afterUserList.get(0).getId();
		User userDb = daoUser.getUserById(addedUserId);
		daoUser.deleteUserById(addedUserId);

		assertEquals(user.getName(), userDb.getName());
		assertEquals(user.getSurname(), userDb.getSurname());
		assertEquals(user.getLogin(), userDb.getLogin());
		assertEquals(user.getPassword(), userDb.getPassword());
		assertEquals(user.geteMail(), userDb.geteMail());
		assertEquals(user.getBirthDate(), userDb.getBirthDate());
		assertEquals(user.getRegDate(), userDb.getRegDate());
		assertEquals(user.getPhone(), userDb.getPhone());
		assertEquals(user.getRole(), userDb.getRole());
	}

	@Test
	public void testDeleteUser() throws DAOException {
		IUserDAO daoUser = daoFactory.getUserDAO();
		List<User> baforeUserList = daoUser.getUserList();
		boolean flag = daoUser.addUser(user);
		assertTrue(flag);
		List<User> afterUserList = daoUser.getUserList();
		assertEquals(baforeUserList.size() + 1, afterUserList.size());
		afterUserList.removeAll(baforeUserList);
		int addedUserId = afterUserList.get(0).getId();
		daoUser.deleteUserById(addedUserId);
		User userDb = daoUser.getUserById(addedUserId);
		assertNull(userDb);
	}

	@Test
	public void testUpdateUser() throws DAOException {
		IUserDAO daoUser = daoFactory.getUserDAO();
		List<User> baforeUserList = daoUser.getUserList();
		boolean flag = daoUser.addUser(user);
		assertTrue(flag);
		List<User> afterUserList = daoUser.getUserList();
		assertEquals(baforeUserList.size() + 1, afterUserList.size());
		afterUserList.removeAll(baforeUserList);
		int addedUserId = afterUserList.get(0).getId();

		user.setName("Changed name");
		user.setSurname("Changed surname");
		user.setId(addedUserId);
		daoUser.updateUser(user);
		User userDb = daoUser.getUserById(addedUserId);
		daoUser.deleteUserById(addedUserId);

		assertEquals(user.getName(), userDb.getName());
		assertEquals(user.getSurname(), userDb.getSurname());
		assertEquals(user.getLogin(), userDb.getLogin());
		assertEquals(user.getPassword(), userDb.getPassword());
		assertEquals(user.geteMail(), userDb.geteMail());
		assertEquals(user.getBirthDate(), userDb.getBirthDate());
		assertEquals(user.getRegDate(), userDb.getRegDate());
		assertEquals(user.getPhone(), userDb.getPhone());
		assertEquals(user.getRole(), userDb.getRole());
		user.setId(0);
	}

	@Test
	public void testGetUserByLogin() throws DAOException {
		IUserDAO daoUser = daoFactory.getUserDAO();
		List<User> baforeUserList = daoUser.getUserList();
		boolean flag = daoUser.addUser(user);
		assertTrue(flag);
		List<User> afterUserList = daoUser.getUserList();
		assertEquals(baforeUserList.size() + 1, afterUserList.size());
		afterUserList.removeAll(baforeUserList);
		int addedUserId = afterUserList.get(0).getId();
		User userDb = daoUser.getUserByLogin(user.getLogin());
		daoUser.deleteUserById(addedUserId);

		assertEquals(user.getName(), userDb.getName());
		assertEquals(user.getSurname(), userDb.getSurname());
		assertEquals(user.getLogin(), userDb.getLogin());
		assertEquals(user.getPassword(), userDb.getPassword());
		assertEquals(user.geteMail(), userDb.geteMail());
		assertEquals(user.getBirthDate(), userDb.getBirthDate());
		assertEquals(user.getRegDate(), userDb.getRegDate());
		assertEquals(user.getPhone(), userDb.getPhone());
		assertEquals(user.getRole(), userDb.getRole());
	}

	@Test
	public void testGetPasswordByLogin() throws DAOException {
		IUserDAO daoUser = daoFactory.getUserDAO();
		List<User> baforeUserList = daoUser.getUserList();
		boolean flag = daoUser.addUser(user);
		assertTrue(flag);
		List<User> afterUserList = daoUser.getUserList();
		assertEquals(baforeUserList.size() + 1, afterUserList.size());
		afterUserList.removeAll(baforeUserList);
		int addedUserId = afterUserList.get(0).getId();
		String pass = daoUser.getPasswordByLogin(user.getLogin());
		daoUser.deleteUserById(addedUserId);

		assertEquals(user.getPassword(), pass);
	}

	@AfterClass
	public static void destroyTestContext() throws ConnectionPoolException {
		pool.destroyConnectionPool();
	}

}
