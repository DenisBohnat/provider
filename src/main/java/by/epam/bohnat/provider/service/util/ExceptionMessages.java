package by.epam.bohnat.provider.service.util;

/**
 * Defines a set of String constants that describe occurred exceptions in the
 * service layer classes.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public final class ExceptionMessages {

	public static final String ACCOUNT_NOT_ADDED = "The account was not added to the database.";
	public static final String ACCOUNT_NOT_UPDATED = "The account was not updated to the database.";
	public static final String ALREADY_EXIST = "Duplicate user.";
	public static final String FEE_NOT_ADDED = "Monthly fee not added.";
	public static final String INCORECT_PASSWORD = "Wrong password.";
	public static final String INVALID_ACCOUNT_NUMBER = "Account number is not valid.";
	public static final String INVALID_AMOUNT = "Amount is not valid";
	public static final String INVALID_BIRTH_DATE = "The year must exceed 1940 and the date can not exceed today";
	public static final String INVALID_EMAIL = "E-mail you entered is not valid.";
	public static final String INVALID_ID = "Id is not valid.";
	public static final String INVALID_INT = "Int number is not valid.";
	public static final String INVALID_LOGIN = "Login must start with the letter and consist at least 4 symbols.";
	public static final String INVALID_OBJECT = "Object is not valid.";
	public static final String INVALID_PASSWORD = "Password must be between 6 and 30 symbols.";
	public static final String INVALID_PHONE = "Phone must be 9 symbols.";
	public static final String INVALID_REG_DATE = "Registration date is not valid.";
	public static final String INVALID_SPENT_TRAFFIC = "Spent traffic must be more than 0 or equalls 0.";
	public static final String INVALID_STRING = "String is not valid.";
	public static final String INVALID_TARIFF_NAME = "Tariff name is not valid.";
	public static final String INVALID_TARIFF_OVERDRAFT_AMOUNT = "Overdraft amount must be more than 0.";
	public static final String INVALID_TARIFF_REC_SPEED = "Reception speed must be more than 0.";
	public static final String INVALID_TARIFF_SUBSTRIPTION_FEE = "Subscription fee must be more than 0.";
	public static final String INVALID_TARIFF_TRAFFIC_VOLUME = "Traffic volume must be more than 0.";
	public static final String INVALID_TARIFF_TRANS_SPEED = "Transmission speed must be more than 0.";
	public static final String INVALID_TARIFF_TYPE = "Tariff type is not valid.";
	public static final String INVALID_USER_NAME = "Name must consist at least 4 symbols and max 40 symbols.";
	public static final String INVALID_USER_ROLE = "User role is not valid.";
	public static final String INVALID_USER_SURNAME = "Surname must consist at least 4 symbols and max 40 symbols.";
	public static final String LOGIN_NOT_FOUND = "No user with that login in the database.";
	public static final String NO_CURRENT_ACCOUNT_IN_DB = "No account current user in the database.";
	public static final String NO_CURRENT_REQUEST_IN_DB = "No request current user in the database.";
	public static final String NO_NON_PAYERS_IN_DB = "No non-payers accounts in the database.";
	public static final String NO_PAYMENTS_IN_DB = "No payments in the database.";
	public static final String NO_REQUESRS_IN_DB = "No requests in the database.";
	public static final String NO_TARIFFS_IN_DB = "No tariffs in the database.";
	public static final String NO_USER_IN_DB = "No user in the database.";
	public static final String REQUEST_NOT_ADDED = "The request was not added to the database.";
	public static final String SIGN_UP_FAILED = "User was not registered.";
	public static final String SOURCE_ERROR = "The error occured in the data source!";
	public static final String TARIFF_NOT_ADDED = "The tariff was not added to the database.";
	public static final String TARIFF_NOT_UPDATED = "The tariff was not updated to the database.";
	public static final String TARIFF_PLAN_NOT_CHANGED = "Tariff plan not changed.";
	public static final String USER_NOT_UPDATED = "User was not updated to the database.";
}
