package by.epam.bohnat.provider.service.exception.tariff;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * edit tariffs
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class EditTariffServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public EditTariffServiceException() {
		super();
	}

	public EditTariffServiceException(String message, Throwable e) {
		super(message, e);
	}

	public EditTariffServiceException(String message) {
		super(message);
	}

	public EditTariffServiceException(Throwable e) {
		super(e);
	}

}
