package by.epam.bohnat.provider.service.exception.user;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * create and add new user to DAO layer
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class ServiceSignUpException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ServiceSignUpException() {
		super();
	}

	public ServiceSignUpException(String message, Throwable e) {
		super(message, e);
	}

	public ServiceSignUpException(String message) {
		super(message);
	}

	public ServiceSignUpException(Throwable e) {
		super(e);
	}

}
