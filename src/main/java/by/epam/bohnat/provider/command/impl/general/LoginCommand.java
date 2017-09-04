package by.epam.bohnat.provider.command.impl.general;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bohnat.provider.bean.User;
import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.ErrorMessages;
import by.epam.bohnat.provider.command.util.JSPNames;
import by.epam.bohnat.provider.command.util.LogMessages;
import by.epam.bohnat.provider.service.IAccountService;
import by.epam.bohnat.provider.service.IUserService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.user.UserLoginServiceException;

/**
 * Class {@code LoginCommand} is a implementation of {@code Command} that
 * authorize user reading the parameters from the JSP and sending them to the
 * relevant service class. Checks the access rights of the user who is
 * performing this action.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class LoginCommand implements Command {

	private static final Logger logger = LogManager.getLogger(LoginCommand.class.getName());

	/**
	 * Indicates that the user is an administrator
	 */
	private static final int IS_ADMIN = 2;

	/**
	 * Handles request to the servlet by trying to log in a user with given
	 * credentials.
	 * 
	 * @param request
	 *            request to the servlet, containing user's login and password
	 * @param response
	 *            response from the servlet to the HTTP request
	 * @throws UserLoginServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IUserService
	 * @see IAccountService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String login = request.getParameter(Attributes.LOGIN);
		String password = request.getParameter(Attributes.PASSWORD);
		try {
			ServiceFactory f = ServiceFactory.getInstance();
			IUserService uService = f.getUserService();
			IAccountService aService = f.getAccountService();
			User user = uService.loginUser(login, password);
			if (user.getRole() != IS_ADMIN) {
				boolean aFlag = aService.isUserGetAccount(user.getId());
				if (!aFlag) {
					session.setAttribute(Attributes.HAVE_ACCOUNT, false);
				} else {
					session.setAttribute(Attributes.HAVE_ACCOUNT, true);
					session.setAttribute(Attributes.IS_BLOCKED, aService.isUserBlockedAccount(user.getId()));
					if (aService.isUserBlockedAccount(user.getId())) {
						request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.USER_IN_BAN);
					}
				}
			}
			LocalDate locDate = LocalDate.now();
			logger.debug(String.format(LogMessages.USER_LOGGED_IN, user.getLogin(), user.getId()));
			session.setAttribute(Attributes.REGISTERED_USER, user.getLogin());
			session.setAttribute(Attributes.LOCAL_DATE, locDate);
			session.setAttribute(Attributes.USER_ID, user.getId());
			session.setAttribute(Attributes.ROLE, user.getRole());
			request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
		} catch (UserLoginServiceException e) {
			logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
					this.getClass().getSimpleName()), e);
			request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
					this.getClass().getSimpleName()), e);
			request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JSPNames.ERROR_PAGE).forward(request, response);
		}
	}
}
