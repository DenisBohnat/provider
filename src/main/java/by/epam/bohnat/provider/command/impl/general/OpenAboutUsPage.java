package by.epam.bohnat.provider.command.impl.general;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.JSPNames;

/**
 * Class {@code OpenAboutUsPage} is an implementation of {@code Command} for
 * opening about us page.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class OpenAboutUsPage implements Command {

	/**
	 * The method describes the behavior of the command to process the request
	 * to redirect to about us page.
	 * 
	 * @param request
	 *            request to the servlet, used to access query parameters and
	 *            request / session / application attributes
	 * @param response
	 *            response from the servlet to the HTTP request
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher(JSPNames.ABOUT_US_PAGE).forward(request, response);
	}

}
