package by.epam.bohnat.provider.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class {@code Command} is an interface, representing a command, handled by a
 * servlet.
 * <p>
 * Command layer situated between Controller(gets client request) and
 * Service(logic of application).
 * <p>
 * The interface {@code Command} defines a single method {@code execute()} which
 * implements an algorithm for executing a specific command.
 * <p>
 * Command is used for handling request parameters and passing them to Service.
 * As the result of completing command should set attributes to request or
 * session scope, which will inform client about the result of request handling.
 * <p>
 * Also command dispatches or redirects requests on different pages depending on
 * the result of Service methods calls.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public interface Command {

	/**
	 * Performs necessary actions, retrieves information from the service layers
	 * and sets it to the request if necessary.
	 * 
	 * @param request
	 *            object of class {@code HttpServletRequest}, representing
	 *            HTTP-request context;designed to access the parameters and
	 *            attributes of the request and to obtain a session
	 *            ({@code HttpSession})
	 * @param response
	 *            object of class {@code HttpServletResponse}, representing the
	 *            context of the response to the HTTP request
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServletRequest
	 * @see HttpServletResponse
	 * @see HttpSession
	 * @see HttpServletRequest
	 * @see HttpServletResponse
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
