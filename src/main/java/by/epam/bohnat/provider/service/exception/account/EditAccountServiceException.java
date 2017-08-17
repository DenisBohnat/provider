package by.epam.bohnat.provider.service.exception.account;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * edit accounts
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class EditAccountServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public EditAccountServiceException() {
		super();
	}

	public EditAccountServiceException(String message, Throwable e) {
		super(message, e);
	}

	public EditAccountServiceException(String message) {
		super(message);
	}

	public EditAccountServiceException(Throwable e) {
		super(e);
	}

}
