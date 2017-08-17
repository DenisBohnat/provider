package by.epam.bohnat.provider.service.exception;

/**
 * Describes superclass for all exceptions that may occur in service layer
 * classes
 *
 * @author Denis Bohnat
 * @version 1.0
 */

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable e) {
		super(message, e);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable e) {
		super(e);
	}

}
