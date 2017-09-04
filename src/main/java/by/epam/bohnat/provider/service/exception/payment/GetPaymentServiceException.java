package by.epam.bohnat.provider.service.exception.payment;

import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Describes an exception that may be thrown in the service layer methods that
 * get payments from DAO layer
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ServiceException
 */
public class GetPaymentServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public GetPaymentServiceException() {
		super();
	}

	public GetPaymentServiceException(String message, Throwable e) {
		super(message, e);
	}

	public GetPaymentServiceException(String message) {
		super(message);
	}

	public GetPaymentServiceException(Throwable e) {
		super(e);
	}

}
