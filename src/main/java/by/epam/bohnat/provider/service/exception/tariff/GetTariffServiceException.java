package by.epam.bohnat.provider.service.exception.tariff;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * get tariffs from DAO layer
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class GetTariffServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public GetTariffServiceException() {
		super();
	}

	public GetTariffServiceException(String message, Throwable e) {
		super(message, e);
	}

	public GetTariffServiceException(String message) {
		super(message);
	}

	public GetTariffServiceException(Throwable e) {
		super(e);
	}

}
