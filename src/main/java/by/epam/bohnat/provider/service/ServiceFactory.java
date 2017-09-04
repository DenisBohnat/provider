package by.epam.bohnat.provider.service;

import by.epam.bohnat.provider.service.impl.AccountServiceImpl;
import by.epam.bohnat.provider.service.impl.PaymentServiceImpl;
import by.epam.bohnat.provider.service.impl.RequestServiceImpl;
import by.epam.bohnat.provider.service.impl.TariffServiceImpl;
import by.epam.bohnat.provider.service.impl.UserServiceImpl;

/**
 * This is a service factory class which produces different service interface
 * implementations each of which performs a logic of working with project
 * entities.
 * <p>
 * You can get the object of {@code ServiceFactory} class by calling the static
 * method {@code getInstance()}.
 * <p>
 * Service objects handle data received from a data source using DAO objects, in
 * accordance with the application logic. Service objects are required to
 * provide validation of data.
 * <p>
 * 
 * The {@code ServiceFactory} class provides methods for obtaining service
 * objects that encapsulate application logic relative to data model objects.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public class ServiceFactory {

	/**
	 * Factory object. It is created once when the class is loaded into memory.
	 * Then you can get the factory object by calling the static method
	 * {@code getInstance()} of this class.
	 */
	private static final ServiceFactory instance = new ServiceFactory();

	/**
	 * Service object for processing data about accounts
	 */
	private static final IAccountService accountService = new AccountServiceImpl();

	/**
	 * Service object for processing data about tariffs
	 */
	private static final ITariffService tariffService = new TariffServiceImpl();

	/**
	 * Service object for processing data about users
	 */
	private static final IUserService userService = new UserServiceImpl();

	/**
	 * Service object for processing data about requests
	 */
	private static final IRequestService requestService = new RequestServiceImpl();

	/**
	 * Service object for processing data about payments
	 */
	private static final IPaymentService paymentService = new PaymentServiceImpl();

	/**
	 * A constructor for creating a factory object. It can only be called from
	 * this class.
	 */
	private ServiceFactory() {

	}

	/**
	 * Static method {@code getInstance()} is used to get the factory object to
	 * get service objects.
	 * 
	 * @return factory object for receiving service objects
	 */
	public static ServiceFactory getInstance() {
		return instance;
	}

	/**
	 * Getting a service object for processing data about accounts
	 * 
	 * @return service object for processing data about accounts
	 * @see IAccountService
	 */
	public IAccountService getAccountService() {
		return accountService;
	}

	/**
	 * Getting a service object for processing data about tariffs
	 * 
	 * @return service object for processing data about tariffs
	 * @see ITariffService
	 */
	public ITariffService getTariffService() {
		return tariffService;
	}

	/**
	 * Getting a service object for processing data about users
	 * 
	 * @return service object for processing data about users
	 * @see IUserService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * Getting a service object for processing data about requests
	 * 
	 * @return service object for processing data about requests
	 * @see IRequestService
	 */
	public IRequestService getRequestService() {
		return requestService;
	}

	/**
	 * Getting a service object for processing data about payments
	 * 
	 * @return service object for processing data about payments
	 * @see IPaymentService
	 */
	public IPaymentService getPaymentService() {
		return paymentService;
	}
}
