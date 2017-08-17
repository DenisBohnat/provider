package by.epam.bohnat.provider.service.exception.user;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * get users from DAO layer
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class GetUserServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public GetUserServiceException() {
		super();
	}

	public GetUserServiceException(String message, Throwable e) {
		super(message, e);
	}

	public GetUserServiceException(String message) {
		super(message);
	}

	public GetUserServiceException(Throwable e) {
		super(e);
	}

}
