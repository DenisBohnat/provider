package by.epam.bohnat.provider.command.impl.admin;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
import by.epam.bohnat.provider.service.IRequestService;
import by.epam.bohnat.provider.service.ITariffService;
import by.epam.bohnat.provider.service.IUserService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.request.GetRequestServiceException;
import by.epam.bohnat.provider.service.exception.tariff.GetTariffServiceException;
import by.epam.bohnat.provider.service.exception.user.GetUserServiceException;

/**
 * Class {@code OpenAllRequestsPage} is an implementation of {@code Command} for
 * opening page with all user requests.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class OpenAllRequestsPage implements Command {

	private static final Logger logger = LogManager.getLogger(OpenAllRequestsPage.class.getName());

	/**
	 * Identifier that indicates that the user is not an administrator
	 */
	private static final int NOT_ADMIN = 1;
	
	/**
	 * Performs the command that gets list of all user requests entities from
	 * the the service layer and passes it to the relevant JSP.
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
	 * @throws GetRequestServiceException
	 * @throws GetUserServiceException
	 * @throws GetTariffServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IRequestService
	 * @see IUserService
	 * @see ITariffService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.WORK_WITH_REQUEST_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			if (Integer.valueOf(session.getAttribute(Attributes.ROLE).toString()) == NOT_ADMIN) {
				request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.WORK_WITH_REQUEST_POSSIBILITY);
				request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
			} else {

				int pageNumber = Integer.parseInt(request.getParameter(Attributes.PAGE_NUMBER));

				try {
					ServiceFactory f = ServiceFactory.getInstance();
					IRequestService rService = f.getRequestService();
					IUserService uService = f.getUserService();
					ITariffService tService = f.getTariffService();

					int amountPage = rService.getNumberOfRequestsPages();
					List<Request> rList = rService.getRequestsOnCurrentPage(pageNumber);

					List<String> names = new LinkedList<String>();
					List<String> surnames = new LinkedList<String>();
					List<String> tariffNames = new LinkedList<String>();

					for (Request req : rList) {
						names.add(uService.getNameById(req.getUserId()));
						surnames.add(uService.getSurnameById(req.getUserId()));
						tariffNames.add(tService.getTariffNameById(req.getTariffId()));
					}
					request.setAttribute(Attributes.USER_REQUESTS, rList);
					request.setAttribute(Attributes.USER_NAMES, names);
					request.setAttribute(Attributes.USER_SURNAMES, surnames);
					request.setAttribute(Attributes.TARIFF_NAMES, tariffNames);
					request.setAttribute(Attributes.CURRENT_PAGE, pageNumber);
					request.setAttribute(Attributes.PAGE_AMOUNT, amountPage);
					request.getRequestDispatcher(JSPNames.ADMIN_USER_REQUESTS).forward(request, response);
				} catch (GetRequestServiceException e) {
					logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
							this.getClass().getSimpleName()), e);
					request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
					request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
				} catch (GetUserServiceException e) {
					logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
							this.getClass().getSimpleName()), e);
					request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
					request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
				} catch (GetTariffServiceException e) {
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
