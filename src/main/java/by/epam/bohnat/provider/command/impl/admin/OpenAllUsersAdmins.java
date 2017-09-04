package by.epam.bohnat.provider.command.impl.admin;

import java.io.IOException;
import java.util.List;

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
 * Class {@code OpenAllUsersAdmins} is an implementation of {@code Command} for
 * opening page with all administrators of the system.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class OpenAllUsersAdmins implements Command {

	private static final Logger logger = LogManager.getLogger(OpenAllUsersAdmins.class.getName());

	/**
	 * Identifier that indicates that the user is not an administrator
	 */
	private static final int NOT_ADMIN = 1;

	/**
	 * Performs the command that gets list of all administrators (user entities)
	 * from the the service layer and passes it to the relevant JSP.
	 * <p>
	 * Checks the access rights of the user who is performing this action. Only
	 * administrators can use this command. All entities can be edited and
	 * deleted. If the client is not the system administrator, the request will
	 * be redirected to the main page.
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
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.WORK_USER_INFORMATION_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			if (Integer.valueOf(session.getAttribute(Attributes.ROLE).toString()) == NOT_ADMIN) {
				request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.WORK_USER_INFORMATION_POSSIBILITY);
				request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
			} else {

				int pageNumber = Integer.parseInt(request.getParameter(Attributes.PAGE_NUMBER));
				int elementsPerPage = Integer.parseInt(request.getParameter(Attributes.ELEMENTS_PER_PAGE_ATTR));

				try {
					ServiceFactory f = ServiceFactory.getInstance();
					IUserService uService = f.getUserService();
					int amountPage = uService.getNumberOfAdminsPages(elementsPerPage);
					List<User> adminList = uService.getAdminsOnCurrentPage(pageNumber, elementsPerPage);
					request.setAttribute(Attributes.USERS_LIST, adminList);
					request.setAttribute(Attributes.IS_ADMIN, true);
					request.setAttribute(Attributes.CURRENT_PAGE, pageNumber);
					request.setAttribute(Attributes.ELEMENTS_PER_PAGE_ATTR, elementsPerPage);
					request.setAttribute(Attributes.PAGE_AMOUNT, amountPage);
					request.getRequestDispatcher(JSPNames.ADMIN_USERS_PAGE).forward(request, response);
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
}
