package by.epam.bohnat.provider.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bohnat.provider.bean.Request;
import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.ErrorMessages;
import by.epam.bohnat.provider.command.util.JSPNames;
import by.epam.bohnat.provider.command.util.LogMessages;
import by.epam.bohnat.provider.command.util.SuccessMessages;
import by.epam.bohnat.provider.service.IRequestService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Class {@code DeleteRequest} is an implementation of {@code Command} for
 * deleting user request from data source.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class DeleteRequest implements Command {

	private static final Logger logger = LogManager.getLogger(DeleteRequest.class.getName());

	/**
	 * Performs the command that reads user request ID parameter from the JSP
	 * and sends them to the relevant service class for deleting from the data
	 * source.
	 * <p>
	 * The method accesses the service {@code IRequestService}.
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
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IRequestService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.DELETE_REQUEST_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			if (Integer.valueOf(session.getAttribute(Attributes.ROLE).toString()) == 1) {
				request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.DELETE_REQUEST_POSSIBILITY);
				// ???
				request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
			} else {
				int requestId = Integer.valueOf(request.getParameter(Attributes.REQUEST_ID));
				try {
					ServiceFactory f = ServiceFactory.getInstance();
					IRequestService rService = f.getRequestService();
					Request userRequest = rService.getRequestById(requestId);
					int userId = userRequest.getUserId();
					rService.deleteRequestById(requestId);
					logger.debug(String.format(LogMessages.REQUEST_DELETED, requestId));
					// success message
					request.setAttribute(Attributes.SUCCESS_MESSAGE, SuccessMessages.REQUEST_DELETED);
					request.getRequestDispatcher("/Controller?command=open_user_details_page&curUserId=" + userId)
							.forward(request, response);
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
