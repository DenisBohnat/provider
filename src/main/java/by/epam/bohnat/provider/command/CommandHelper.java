package by.epam.bohnat.provider.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.bohnat.provider.command.impl.admin.AddTariff;
import by.epam.bohnat.provider.command.impl.admin.AddUser;
import by.epam.bohnat.provider.command.impl.admin.BanUser;
import by.epam.bohnat.provider.command.impl.admin.ChangeTariffPlan;
import by.epam.bohnat.provider.command.impl.admin.DeleteTariff;
import by.epam.bohnat.provider.command.impl.admin.DeleteUser;
import by.epam.bohnat.provider.command.impl.admin.EditTariff;
import by.epam.bohnat.provider.command.impl.admin.OpenAddingTariffPage;
import by.epam.bohnat.provider.command.impl.admin.OpenAddingUserPage;
import by.epam.bohnat.provider.command.impl.admin.OpenAllRequestsPage;
import by.epam.bohnat.provider.command.impl.admin.OpenAllTariffs;
import by.epam.bohnat.provider.command.impl.admin.OpenAllUsersAdmins;
import by.epam.bohnat.provider.command.impl.admin.OpenAllUsersCustomers;
import by.epam.bohnat.provider.command.impl.admin.OpenAllNonPayersPage;
import by.epam.bohnat.provider.command.impl.admin.OpenEditingTariffPage;
import by.epam.bohnat.provider.command.impl.admin.OpenUserDetailsPage;
import by.epam.bohnat.provider.command.impl.general.CancelCommand;
import by.epam.bohnat.provider.command.impl.general.ChangeLanguage;
import by.epam.bohnat.provider.command.impl.general.DeleteRequest;
import by.epam.bohnat.provider.command.impl.general.EditProfile;
import by.epam.bohnat.provider.command.impl.general.LoginCommand;
import by.epam.bohnat.provider.command.impl.general.LogoutCommand;
import by.epam.bohnat.provider.command.impl.general.OpenAboutUsPage;
import by.epam.bohnat.provider.command.impl.general.OpenHomePage;
import by.epam.bohnat.provider.command.impl.general.OpenLimTariffsPage;
import by.epam.bohnat.provider.command.impl.general.OpenProfilePage;
import by.epam.bohnat.provider.command.impl.general.OpenSignUpPage;
import by.epam.bohnat.provider.command.impl.general.OpenStartPage;
import by.epam.bohnat.provider.command.impl.general.OpenUnlimTariffsPage;
import by.epam.bohnat.provider.command.impl.general.SignUpCommand;
import by.epam.bohnat.provider.command.impl.user.BringMonthlyFee;
import by.epam.bohnat.provider.command.impl.user.BuyTariff;
import by.epam.bohnat.provider.command.impl.user.OpenUserAccountPage;
import by.epam.bohnat.provider.command.impl.user.OpenUserFeePage;
import by.epam.bohnat.provider.command.impl.user.OpenUserPaymentPage;
import by.epam.bohnat.provider.command.impl.user.OpenUserRequestPage;
import by.epam.bohnat.provider.command.impl.user.SendRequest;
import by.epam.bohnat.provider.command.impl.user.TerminateAccount;

/**
 * Sets the accordance between command names performed in {@code CommandName}
 * enumeration and Command implementation classes.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 * @see CommandName
 */
public class CommandHelper {

	/**
	 * Factory object. It is created once when the class is loaded into memory.
	 * Then you can get the factory object by calling the static method
	 * {@code getInstance()} of this class.
	 */
	private static final CommandHelper instance = new CommandHelper();

	/**
	 * Map where the key is the {@code CommandName} enumeration object and value
	 * is a different command.
	 */
	private Map<CommandName, Command> commands = new HashMap<>();

	/**
	 * Puts all existing commands to the map where the key is the
	 * {@code CommandName} enumeration object.
	 */
	private CommandHelper() {
		commands.put(CommandName.SHOW_TARIFFS, new OpenAllTariffs());
		commands.put(CommandName.ADD_TARIFF, new AddTariff());
		commands.put(CommandName.ADDING_TARIFF, new OpenAddingTariffPage());
		commands.put(CommandName.ABOUT_US, new OpenAboutUsPage());
		commands.put(CommandName.HOME, new OpenHomePage());
		commands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguage());
		commands.put(CommandName.UNLIMITED_TARIFFS, new OpenUnlimTariffsPage());
		commands.put(CommandName.LIMITED_TARIFFS, new OpenLimTariffsPage());
		commands.put(CommandName.LOGIN, new LoginCommand());
		commands.put(CommandName.LOGOUT, new LogoutCommand());
		commands.put(CommandName.DELETE_TARIFF, new DeleteTariff());
		commands.put(CommandName.SHOW_CUSTOMERS, new OpenAllUsersCustomers());
		commands.put(CommandName.SHOW_ADMINS, new OpenAllUsersAdmins());
		commands.put(CommandName.DELETE_USER, new DeleteUser());
		commands.put(CommandName.OPEN_SIGN_UP_PAGE, new OpenSignUpPage());
		commands.put(CommandName.SIGN_UP, new SignUpCommand());
		commands.put(CommandName.EDITING_TARIFF, new OpenEditingTariffPage());
		commands.put(CommandName.EDIT_TARIFF, new EditTariff());
		commands.put(CommandName.OPEN_PROFILE, new OpenProfilePage());
		commands.put(CommandName.CANCEL, new CancelCommand());
		commands.put(CommandName.EDIT_PROFILE, new EditProfile());
		commands.put(CommandName.OPEN_ADD_USER_PAGE, new OpenAddingUserPage());
		commands.put(CommandName.ADD_USER, new AddUser());
		commands.put(CommandName.OPEN_USER_DETAILS_PAGE, new OpenUserDetailsPage());
		commands.put(CommandName.DELETE_REQUEST, new DeleteRequest());
		commands.put(CommandName.OPEN_USER_REQUEST_PAGE, new OpenUserRequestPage());
		commands.put(CommandName.SEND_REQUEST, new SendRequest());
		commands.put(CommandName.OPEN_USER_ACCOUNT_PAGE, new OpenUserAccountPage());
		commands.put(CommandName.BRING_MONTHLY_FEE, new BringMonthlyFee());
		commands.put(CommandName.OPEN_USER_FEE_PAGE, new OpenUserFeePage());
		commands.put(CommandName.BAN_USER, new BanUser());
		commands.put(CommandName.OPEN_ALL_REQUESTS_PAGE, new OpenAllRequestsPage());
		commands.put(CommandName.OPEN_ALL_NON_PAYERS_PAGE, new OpenAllNonPayersPage());
		commands.put(CommandName.OPEN_START_PAGE, new OpenStartPage());
		commands.put(CommandName.BUY_TARIFF, new BuyTariff());
		commands.put(CommandName.TERMINATE_ACCOUNT, new TerminateAccount());
		commands.put(CommandName.CHANGE_TARIFF_PLAN, new ChangeTariffPlan());
		commands.put(CommandName.OPEN_USER_PAYMENT_PAGE, new OpenUserPaymentPage());
	}

	/**
	 * Static method {@code getInstance()} is used to get the factory object to
	 * get {@code CommandName} enumeration object.
	 * 
	 * @return factory object for {@code CommandName} enumeration object
	 */
	public static CommandHelper getInstance() {
		return instance;
	}

	/**
	 * Takes {@code CommandName} object by command name string and defines
	 * relevant {@code Command} interface implementation to be returned
	 * 
	 * @param name
	 *            command name
	 * @return command interface implementation
	 */
	public Command getCommand(String name) {
		CommandName commandName = CommandName.valueOf(name);
		Command command = commands.get(commandName);
		return command;
	}
}
