package by.epam.bohnat.provider.command.impl.user;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

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
import by.epam.bohnat.provider.service.exception.request.AddRequestServiceException;

/**
 * Class {@code SendRequest} is an implementation of {@code Command} for sending
 * user request to change tariff plan.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class SendRequest implements Command {

	private static final Logger logger = LogManager.getLogger(SendRequest.class.getName());

	/**
	 * Performs the command that reads new user request parameters from the JSP
	 * and sends them to the relevant service class.
	 * <p>
	 * The method accesses the service {@code IRequestService}.
	 * <p>
	 * Checks the access rights of the user who is performing this action. Only
	 * registered user can use this command. If the client is not registered,
	 * the request will be redirected to the main page.
	 * 
	 * @param request
	 *            request to the servlet, used to access query parameters and
	 *            request / session / application attributes
	 * @param response
	 *            response from the servlet to the HTTP request
	 * @throws AddRequestServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IRequestService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.SEND_REQUEST_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			int userId = Integer.valueOf(session.getAttribute(Attributes.USER_ID).toString());
			int tariffId = Integer.parseInt(request.getParameter(Attributes.TYPE_ID));
			String description = request.getParameter(Attributes.DESCRIPTION);
			Date regDate = Date.valueOf(LocalDate.now());

			Request uRequest = new Request(0, userId, tariffId, description, regDate);

			try {
				ServiceFactory f = ServiceFactory.getInstance();
				IRequestService rService = f.getRequestService();
				rService.sendRequest(uRequest);
				logger.debug(LogMessages.REQUEST_ADDED);
				request.setAttribute(Attributes.SUCCESS_MESSAGE, SuccessMessages.REQUEST_ADDED);
				request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
			} catch (AddRequestServiceException e) {
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
