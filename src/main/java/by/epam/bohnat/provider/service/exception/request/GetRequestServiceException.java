package by.epam.bohnat.provider.service.exception.request;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * get requests from DAO layer
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class GetRequestServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public GetRequestServiceException() {
		super();
	}

	public GetRequestServiceException(String message, Throwable e) {
		super(message, e);
	}

	public GetRequestServiceException(String message) {
		super(message);
	}

	public GetRequestServiceException(Throwable e) {
		super(e);
	}

}
