package by.epam.bohnat.provider.command.impl.general;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.ErrorMessages;
import by.epam.bohnat.provider.command.util.JSPNames;
import by.epam.bohnat.provider.command.util.LogMessages;

/**
 * Class {@code LogoutCommand} is an implementation of {@code Command} for
 * signing out user from the system.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class LogoutCommand implements Command {

	private static final Logger logger = LogManager.getLogger(LogoutCommand.class.getName());

	/**
	 * Handles request to the servlet by invalidating current user session.
	 * <p>
	 * Logging out of the system is done by closing the session. Then the
	 * request is redirected to the index page.
	 * 
	 * @param request
	 *            request to the servlet, used to access query parameters and
	 *            request / session / application attributes
	 * @param response
	 *            response from the servlet to the HTTP request
	 * @see HttpSession
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.USER_NOT_LOGIN);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			int userId = Integer.valueOf(session.getAttribute(Attributes.USER_ID).toString());
			String userLogin = session.getAttribute(Attributes.REGISTERED_USER).toString();
			session.invalidate();
			logger.debug(String.format(LogMessages.USER_LOGGED_OUT, userLogin, userId));
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		}
	}
}
