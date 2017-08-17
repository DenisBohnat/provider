package by.epam.bohnat.provider.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.bohnat.provider.bean.Tariff;
import by.epam.bohnat.provider.dao.DAOFactory;
import by.epam.bohnat.provider.dao.ITariffDAO;
import by.epam.bohnat.provider.dao.exception.DAOException;
import by.epam.bohnat.provider.dao.pool.ConnectionPool;
import by.epam.bohnat.provider.dao.pool.exception.ConnectionPoolException;

public class TariffDAOTest {

	private static ConnectionPool pool;
	private static DAOFactory daoFactory;
	private static Tariff tariff;

	@BeforeClass
	public static void initTestContext() throws ConnectionPoolException {
		pool = ConnectionPool.getInstance();
		pool.initConnectionPool();
		daoFactory = DAOFactory.getInstance();

		tariff = new Tariff();
		tariff.setId(0);
		tariff.setName("Test tariff name");
		tariff.setType(2);
		tariff.setRecSpeed(25.5f);
		tariff.setTransSpeed(25.5f);
		tariff.setSubscriptionFee(15f);
		tariff.setTrafficVolume(54);
		tariff.setOverdraftAmount(0.4f);
	}

	@Test
	public void testAddTariff() throws DAOException {
		ITariffDAO daoTariff = daoFactory.getTariffDAO();
		List<Tariff> beforeTariffList = daoTariff.getAllTariffs();
		boolean flag = daoTariff.addTariff(tariff);
		assertTrue(flag);
		List<Tariff> afterTariffList = daoTariff.getAllTariffs();
		assertEquals(beforeTariffList.size() + 1, afterTariffList.size());
		afterTariffList.removeAll(beforeTariffList);
		int addedTariffId = afterTariffList.get(0).getId();
		Tariff dbTariff = daoTariff.getTariffById(addedTariffId);
		daoTariff.deleteTariffById(addedTariffId);

		assertEquals(tariff.getName(), dbTariff.getName());
		assertEquals(tariff.getType(), dbTariff.getType());
		assertEquals(tariff.getRecSpeed(), dbTariff.getRecSpeed(), 0.01f);
		assertEquals(tariff.getTransSpeed(), dbTariff.getTransSpeed(), 0.01f);
		assertEquals(tariff.getSubscriptionFee(), dbTariff.getSubscriptionFee(), 0.01f);
		assertEquals(tariff.getTrafficVolume(), dbTariff.getTrafficVolume());
		assertEquals(tariff.getOverdraftAmount(), dbTariff.getOverdraftAmount(), 0.01f);
	}

	@Test
	public void testDeleteTariff() throws DAOException {
		ITariffDAO daoTariff = daoFactory.getTariffDAO();
		List<Tariff> beforeTariffList = daoTariff.getAllTariffs();
		boolean flag = daoTariff.addTariff(tariff);
		assertTrue(flag);
		List<Tariff> afterTariffList = daoTariff.getAllTariffs();
		assertEquals(beforeTariffList.size() + 1, afterTariffList.size());
		afterTariffList.removeAll(beforeTariffList);
		int addedTariffId = afterTariffList.get(0).getId();
		daoTariff.deleteTariffById(addedTariffId);
		Tariff dbTariff = daoTariff.getTariffById(addedTariffId);
		assertNull(dbTariff);
	}

	@Test
	public void testUpdateTariff() throws DAOException {
		ITariffDAO daoTariff = daoFactory.getTariffDAO();
		List<Tariff> beforeTariffList = daoTariff.getAllTariffs();
		boolean flag = daoTariff.addTariff(tariff);
		assertTrue(flag);
		List<Tariff> afterTariffList = daoTariff.getAllTariffs();
		assertEquals(beforeTariffList.size() + 1, afterTariffList.size());
		afterTariffList.removeAll(beforeTariffList);
		int addedTariffId = afterTariffList.get(0).getId();

		tariff.setName("Changed test name");
		tariff.setSubscriptionFee(3f);
		tariff.setTrafficVolume(100);
		tariff.setId(addedTariffId);
		daoTariff.updateTariff(tariff);
		Tariff dbTariff = daoTariff.getTariffById(addedTariffId);
		daoTariff.deleteTariffById(addedTariffId);

		assertEquals(tariff.getName(), dbTariff.getName());
		assertEquals(tariff.getType(), dbTariff.getType());
		assertEquals(tariff.getRecSpeed(), dbTariff.getRecSpeed(), 0.01f);
		assertEquals(tariff.getTransSpeed(), dbTariff.getTransSpeed(), 0.01f);
		assertEquals(tariff.getSubscriptionFee(), dbTariff.getSubscriptionFee(), 0.01f);
		assertEquals(tariff.getTrafficVolume(), dbTariff.getTrafficVolume());
		assertEquals(tariff.getOverdraftAmount(), dbTariff.getOverdraftAmount(), 0.01f);
		tariff.setId(0);
	}

	@Test
	public void testGetAllTariffs() throws DAOException {
		ITariffDAO daoTariff = daoFactory.getTariffDAO();
		List<Tariff> allTariffList = daoTariff.getAllTariffs();
		List<Tariff> limTariffList = daoTariff.getLimitedTariffs();
		List<Tariff> unlimTariffList = daoTariff.getUnlimitedTariffs();
		assertEquals(allTariffList.size(), limTariffList.size() + unlimTariffList.size());
	}

	@AfterClass
	public static void destroyTestContext() throws ConnectionPoolException {
		pool.destroyConnectionPool();
	}

}
