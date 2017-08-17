package by.epam.bohnat.provider.dao.pool.exception;

/**
 * Describes an exception that may be thrown in the connection pool classes
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message, Throwable e) {
		super(message, e);
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Throwable e) {
		super(e);
	}

}
