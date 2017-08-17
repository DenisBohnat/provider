package by.epam.bohnat.provider.dao.pool;

import java.util.ResourceBundle;

/**
 * {@code DBResourceManager} class Is intended to extract the properties of the
 * connection to the database from the properties file.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public class DBResourceManager {

	/**
	 * Factory object. It is created once when the class is loaded into memory.
	 * Then you can get the factory object by calling the static method
	 * {@code getInstance()} of this class.
	 */
	private static final DBResourceManager instance = new DBResourceManager();

	/**
	 * Name of the database connection properties file
	 */
	private static final String DB_PROPERTIES_FILE = "db";

	/**
	 * The {@code ResourceBundle} object to work with the property file
	 */
	private ResourceBundle bundle = ResourceBundle.getBundle(DB_PROPERTIES_FILE);

	/**
	 * A constructor for creating a factory object. It can only be called from
	 * this class.
	 */
	private DBResourceManager() {

	}

	/**
	 * Static method {@code getInstance()} is used to get the factory object.
	 * 
	 * @return {@code DBResourceManager} class object
	 */
	public static DBResourceManager getInstance() {
		return instance;
	}

	/**
	 * Getting the property value as specified in the {@code key}.
	 * 
	 * @param key
	 *            property name
	 * @return property value
	 */
	public String getValue(String key) {
		return bundle.getString(key);
	}
}
