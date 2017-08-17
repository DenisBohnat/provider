package by.epam.bohnat.provider.command.util;

/**
 * Defines a set of String constants that describe occurred exceptions in the
 * Command implementation classes. Each of these messages will be shown to the
 * user in case relevant exception occurs.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public final class ErrorMessages {

	public static final String ADD_TARIFF_POSSIBILITY = "Only admin is able to add new tariff.";
	public static final String ADD_USER_POSSIBILITY = "Only admin is able to add new user.";
	public static final String ALREADY_SIGN_IN = "User already login.";
	public static final String BAN_USER_POSSIBILITY = "Only admin is able to ban user.";
	public static final String BUY_TARIFF_POSSIBILITY = "Only registered user is able to buy tariff.";
	public static final String CHANGE_NON_PAYERS_POSSIBILITY = "Only admin is able to work with non-payers.";
	public static final String CHANGE_TARIFF_BY_REQUEST_POSSIBILITY = "Only admin is able to change tariff by user's request.";
	public static final String DELETE_REQUEST_POSSIBILITY = "Only admin is able to delete user's request.";
	public static final String DELETE_TARIFF_POSSIBILITY = "Only admin is able to delete tariff.";
	public static final String DELETE_USER_POSSIBILITY = "Only admin is able to delete user.";
	public static final String EDIT_PROFILE_POSSIBILITY = "Only registered user is able to edit profile.";
	public static final String EDIT_TARIFF_POSSIBILITY = "Only admin is able to edit tariff.";
	public static final String MONTHLY_FEE_POSSIBILITY = "Only registered user is able to pay a subscription fee.";
	public static final String SEND_REQUEST_POSSIBILITY = "Only registered user is able to send request to change tariff.";
	public static final String USER_NOT_LOGIN = "User need to sign in.";
	public static final String VIEW_ACCOUNT_POSSIBILITY = "Only registered user is able to view account information.";
	public static final String VIEW_REQUEST_POSSIBILITY = "Only registered user is able to view request information.";
	public static final String WORK_USER_INFORMATION_POSSIBILITY = "Only admin is able to change user's information.";
	public static final String WORK_WITH_REQUEST_POSSIBILITY = "Only admin is able to see information about user's requests.";
	public static final String WORK_WITH_TARIFF_POSSIBILITY = "Only admin is able to add, delete and edit information about tariffs.";
}
