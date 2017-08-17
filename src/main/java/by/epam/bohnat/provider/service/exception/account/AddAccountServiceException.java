package by.epam.bohnat.provider.service.exception.account;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * create and add new user account to DAO layer
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class AddAccountServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public AddAccountServiceException() {
		super();
	}

	public AddAccountServiceException(String message, Throwable e) {
		super(message, e);
	}

	public AddAccountServiceException(String message) {
		super(message);
	}

	public AddAccountServiceException(Throwable e) {
		super(e);
	}

}
