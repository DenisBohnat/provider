package by.epam.bohnat.provider.dao.exception;

/**
 * Describes an exception that may be thrown in the DAO layer classes
 *
 * @author Denis Bohnat
 * @version 1.0
 */

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable e) {
		super(message, e);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable e) {
		super(e);
	}

}
