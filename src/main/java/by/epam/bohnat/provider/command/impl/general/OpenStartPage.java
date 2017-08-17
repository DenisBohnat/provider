package by.epam.bohnat.provider.command.impl.general;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.ErrorMessages;
import by.epam.bohnat.provider.command.util.JSPNames;

/**
 * Class {@code OpenStartPage} is an implementation of {@code Command} for
 * opening start page of user and administrator program interface.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class OpenStartPage implements Command {

	/**
	 * The method describes the behavior of the command to process the request
	 * to redirect to start page of user and administrator.
	 * <p>
	 * Checks the access rights of the user who is performing this action.
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
			request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
		}
	}
}
