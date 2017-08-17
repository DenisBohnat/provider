package by.epam.bohnat.provider.command.impl.general;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.JSPNames;

/**
 * Class {@code ChangeLanguage} is an implementation of {@code Command} for
 * changing locale for the session.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class ChangeLanguage implements Command {

	/**
	 * Performs the command that changes the current session language to another
	 * one
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
		String language = request.getParameter(Attributes.LANGUAGE);
		request.getSession().setAttribute(Attributes.LANGUAGE, language);
		request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
	}
}
