package by.epam.bohnat.provider.dao.pool;

/**
 * {@code DBParameter} class contains string constants, which are the names of
 * the properties of the connection to the database.
 * <p>
 * These properties are stored in the properties file.
 * <p>
 * {@code DBParameter} class can not be inherited.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public final class DBParameter {

	public static final String DB_DRIVER = "db.driver";
	public static final String DB_URL = "db.url";
	public static final String DB_USER = "db.user";
	public static final String DB_PASSWORD = "db.password";
	public static final String DB_POOL_SIZE = "db.poolsize";

	public DBParameter() {

	}
}
