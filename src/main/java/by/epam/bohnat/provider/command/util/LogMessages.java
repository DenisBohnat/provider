package by.epam.bohnat.provider.command.util;

/**
 * Defines a set of String constants that describe messages to be logged in the
 * Command implementation classes.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public final class LogMessages {

	public static final String EXCEPTION_IN_COMMAND = "%s occured in %s command.";
	public static final String MONTHLY_FEE_ADDED = "User (id #%s) added monthly fee.";
	public static final String REQUEST_ADDED = "Request was added to the database.";
	public static final String REQUEST_DELETED = "Request (had id #%s) was deleted from the database.";
	public static final String TARIFF_ADDED = "New tariff %s was added to the database.";
	public static final String TARIFF_BOUGHT = "User (id #%s) bought a tariff.";
	public static final String TARIFF_DELETED = "Tariff (had id #%s) was deleted from the database.";
	public static final String TARIFF_PLAN_CHANGED = "User's (id #%s) tariff plan was changed.";
	public static final String TARIFF_UPDATED = "Tariff (id #%s) was updated in the database.";
	public static final String USER_ADDED = "New user %s was added to the database.";
	public static final String USER_BANNED = "User (id #%s) was banned.";
	public static final String USER_DELETED = "User (had id #%s) was deleted from the database.";
	public static final String USER_LOGGED_IN = "User %s (id #%s) logged into the system.";
	public static final String USER_LOGGED_OUT = "User %s (id #%s) logged out from the system.";
	public static final String USER_UPDATED = "User (id #%s) was updated in the database.";
}
