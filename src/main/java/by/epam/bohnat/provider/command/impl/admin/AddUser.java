package by.epam.bohnat.provider.command.impl.admin;

import java.io.IOException;
import java.sql.Date;
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
import by.epam.bohnat.provider.command.util.SuccessMessages;
import by.epam.bohnat.provider.service.IUserService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.user.ServiceSignUpException;

/**
 * Class {@code AddUser} is an implementation of {@code Command} for adding new
 * user into data source.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class AddUser implements Command {

	private static final Logger logger = LogManager.getLogger(AddUser.class.getName());

	/**
	 * Identifier that indicates that the user is not an administrator
	 */
	private static final int NOT_ADMIN = 1;
	
	/**
	 * Performs the command that reads new user parameters from the JSP and
	 * sends them to the relevant service class.
	 * <p>
	 * The method accesses the service {@code IUserService}.
	 * <p>
	 * Checks the access rights of the user who is performing this action. Only
	 * administrators can use this command. If the client is not the system
	 * administrator, the request will be redirected to the main page.
	 * 
	 * @param request
	 *            request to the servlet, used to access query parameters and
	 *            request / session / application attributes
	 * @param response
	 *            response from the servlet to the HTTP request
	 * @throws ServiceSignUpException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IUserService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.ADD_USER_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			if (Integer.valueOf(session.getAttribute(Attributes.ROLE).toString()) == NOT_ADMIN) {
				request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.ADD_USER_POSSIBILITY);
				request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
			} else {
				String name = request.getParameter(Attributes.USER_NAME);
				String surname = request.getParameter(Attributes.USER_SURNAME);
				String login = request.getParameter(Attributes.USER_LOGIN);
				String password = request.getParameter(Attributes.USER_PASSWORD);
				String eMail = request.getParameter(Attributes.USER_EMAIL);
				Date birthDate = Date.valueOf(request.getParameter(Attributes.USER_BIRTHDAY));
				String phone = request.getParameter(Attributes.USER_PHONE);
				int role = Integer.parseInt(request.getParameter(Attributes.USER_ROLE));
				Date regDate = Date.valueOf(LocalDate.now());

				User user = new User(0, name, surname, login, password, eMail, birthDate, regDate, phone, role);

				try {
					ServiceFactory f = ServiceFactory.getInstance();
					IUserService uService = f.getUserService();
					uService.addUser(user);
					logger.debug(String.format(LogMessages.USER_ADDED, user.getLogin()));
					request.setAttribute(Attributes.SUCCESS_MESSAGE, SuccessMessages.USER_ADDED);
					request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
				} catch (ServiceSignUpException e) {
					logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
							this.getClass().getSimpleName()), e);
					request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
					request.getRequestDispatcher(JSPNames.ADMIN_ADDING_USER_PAGE).forward(request, response);
				} catch (ServiceException e) {
					logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
							this.getClass().getSimpleName()), e);
					request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
					request.getRequestDispatcher(JSPNames.ERROR_PAGE).forward(request, response);
				}
			}
		}
	}
}
