package by.epam.bohnat.provider.service.exception.request;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * create and add new user request to DAO layer
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class AddRequestServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public AddRequestServiceException() {
		super();
	}

	public AddRequestServiceException(String message, Throwable e) {
		super(message, e);
	}

	public AddRequestServiceException(String message) {
		super(message);
	}

	public AddRequestServiceException(Throwable e) {
		super(e);
	}

}
