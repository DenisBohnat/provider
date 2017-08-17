package by.epam.bohnat.provider.dao;

import by.epam.bohnat.provider.dao.impl.AccountDAOImpl;
import by.epam.bohnat.provider.dao.impl.RequestDAOImpl;
import by.epam.bohnat.provider.dao.impl.TariffDAOImpl;
import by.epam.bohnat.provider.dao.impl.UserDaoImpl;

/**
 * Class {@code DAOFactory} is a factory for obtaining DAO objects - Data Access
 * Objects.
 * <p>
 * You can get the object of {@code DAOFactory} class by calling the static
 * method {@code getInstance()}.
 * <p>
 * DAO objects are designed to provide a single interface for access to a
 * database. DAO objects are used by application logic classes to retrieve data
 * from a data source. These data are processed by the application logic.
 * <p>
 * The {@code DAOFactory} class provides methods for getting DAO objects that
 * can extract data from the data source that are needed for the application,
 * such as information about users, tariffs, requests, accounts.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */

public class DAOFactory {

	/**
	 * Factory object. It is created once when the class is loaded into memory.
	 * Then you can get the factory object by calling the static method
	 * {@code getInstance()} of this class.
	 */
	private final static DAOFactory instance = new DAOFactory();

	/**
	 * DAO object for extracting data about users from data source
	 */
	private final static IUserDAO userDAO = new UserDaoImpl();

	/**
	 * DAO object for extracting data about tariffs from data source
	 */
	private final static ITariffDAO tariffDAO = new TariffDAOImpl();

	/**
	 * DAO object for extracting data about accounts from data source
	 */
	private final static IAccountDAO accountDAO = new AccountDAOImpl();

	/**
	 * DAO object for extracting data about requests from data source
	 */
	private final static IRequestDAO requestDAO = new RequestDAOImpl();

	/**
	 * A constructor for creating a factory object. It can only be called from
	 * this class.
	 */
	private DAOFactory() {

	}

	/**
	 * Static method {@code getInstance()} is used to get the factory object to
	 * get DAO objects.
	 * 
	 * @return factory object for receiving DAO objects
	 */
	public static DAOFactory getInstance() {
		return instance;
	}

	/**
	 * Getting a DAO object that can extract data about users from a data source
	 * 
	 * @return DAO object for extracting data about users
	 * @see IUserDAO
	 */
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * Getting a DAO object that can extract data about tariffs from a data
	 * source
	 * 
	 * @return DAO object for extracting data about tariffs
	 * @see ITariffDAO
	 */
	public ITariffDAO getTariffDAO() {
		return tariffDAO;
	}

	/**
	 * Getting a DAO object that can extract data about accounts from a data
	 * source
	 * 
	 * @return DAO object for extracting data about accounts
	 * @see IAccountDAO
	 */
	public IAccountDAO getAccountDAO() {
		return accountDAO;
	}

	/**
	 * Getting a DAO object that can extract data about requests from a data
	 * source
	 * 
	 * @return DAO object for extracting data about requests
	 * @see IRequestDAO
	 */
	public IRequestDAO getRequestDAO() {
		return requestDAO;
	}
}
