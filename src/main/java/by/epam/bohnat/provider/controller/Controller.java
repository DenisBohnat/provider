package by.epam.bohnat.provider.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.CommandHelper;

/**
 * {@code HttpServlet} implementation class {@code Controller} which receives
 * incoming {@code HttpServletRequest}, serves it by defining the necessary
 * command and calls its method {@code execute()} and returns back to the client
 * the {@code HttpServletResponse}
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * The name of the request parameter, whose value is the name of the command
	 */
	private static final String COMMAND = "command";

	/**
	 * Call the superclass constructor
	 */
	public Controller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handling HTTP GET and POST requests.
	 * <p>
	 * The method retrieves the command name from the request and receives the
	 * command object.
	 * 
	 * @param request
	 *            request context
	 * @param response
	 *            response context
	 * @throws ServletException
	 * @throws IOException
	 * @see CommandHelper
	 * @see Command
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName = request.getParameter(COMMAND);
		if (commandName == null || commandName.isEmpty()) {
			commandName = "unknown-command";
		}
		Command command = CommandHelper.getInstance().getCommand(commandName.toUpperCase());
		command.execute(request, response);
	}

}
