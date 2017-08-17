package by.epam.bohnat.provider.service.exception.user;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * authenticate users
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class UserLoginServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public UserLoginServiceException() {
		super();
	}

	public UserLoginServiceException(String message, Throwable e) {
		super(message, e);
	}

	public UserLoginServiceException(String message) {
		super(message);
	}

	public UserLoginServiceException(Throwable e) {
		super(e);
	}

}
