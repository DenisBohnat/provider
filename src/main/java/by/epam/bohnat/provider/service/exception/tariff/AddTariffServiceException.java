package by.epam.bohnat.provider.service.exception.tariff;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * create and add new tariff to DAO layer
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class AddTariffServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public AddTariffServiceException() {
		super();
	}

	public AddTariffServiceException(String message, Throwable e) {
		super(message, e);
	}

	public AddTariffServiceException(String message) {
		super(message);
	}

	public AddTariffServiceException(Throwable e) {
		super(e);
	}

}
