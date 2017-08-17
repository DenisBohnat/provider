package by.epam.bohnat.provider.service.exception.user;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * update user profile
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class EditUserServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public EditUserServiceException() {
		super();
	}

	public EditUserServiceException(String message, Throwable e) {
		super(message, e);
	}

	public EditUserServiceException(String message) {
		super(message);
	}

	public EditUserServiceException(Throwable e) {
		super(e);
	}
}
