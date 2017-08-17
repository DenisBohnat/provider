package by.epam.bohnat.provider.command.impl.general;

import java.io.IOException;

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
import by.epam.bohnat.provider.service.IUserService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.user.GetUserServiceException;

/**
 * Class {@code OpenProfilePage} is an implementation of {@code Command} for
 * opening page with user profile details.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class OpenProfilePage implements Command {

	private static final Logger logger = LogManager.getLogger(OpenProfilePage.class.getName());

	/**
	 * Performs the command that gets user entity from the the service layer and
	 * passes it to the relevant JSP.
	 * <p>
	 * Checks the access rights of the user who is performing this action. Only
	 * registered user can use this command.
	 * 
	 * @param request
	 *            request to the servlet, used to access query parameters and
	 *            request / session / application attributes
	 * @param response
	 *            response from the servlet to the HTTP request
	 * @throws GetUserServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IUserService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.USER_NOT_LOGIN);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			int userId = Integer.valueOf(session.getAttribute(Attributes.USER_ID).toString());
			try {
				ServiceFactory f = ServiceFactory.getInstance();
				IUserService uService = f.getUserService();
				User user = uService.getUserById(userId);
				request.setAttribute(Attributes.CURRENT_USER, user);
				request.getRequestDispatcher(JSPNames.PROFILE_PAGE).forward(request, response);
			} catch (GetUserServiceException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
						this.getClass().getSimpleName()), e);
				request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
			} catch (ServiceException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
						this.getClass().getSimpleName()), e);
				request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JSPNames.ERROR_PAGE).forward(request, response);
			}
		}
	}
}
