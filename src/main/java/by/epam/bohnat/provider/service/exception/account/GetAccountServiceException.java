package by.epam.bohnat.provider.service.exception.account;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * get accounts from DAO layer
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class GetAccountServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public GetAccountServiceException() {
		super();
	}

	public GetAccountServiceException(String message, Throwable e) {
		super(message, e);
	}

	public GetAccountServiceException(String message) {
		super(message);
	}

	public GetAccountServiceException(Throwable e) {
		super(e);
	}

}
