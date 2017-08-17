package by.epam.bohnat.provider.command.impl.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bohnat.provider.bean.Request;
import by.epam.bohnat.provider.bean.Tariff;
import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.ErrorMessages;
import by.epam.bohnat.provider.command.util.JSPNames;
import by.epam.bohnat.provider.command.util.LogMessages;
import by.epam.bohnat.provider.service.IRequestService;
import by.epam.bohnat.provider.service.ITariffService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.request.GetRequestServiceException;
import by.epam.bohnat.provider.service.exception.tariff.AddTariffServiceException;

/**
 * Class {@code OpenUserRequestPage} is an implementation of {@code Command} for
 * opening user request page.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class OpenUserRequestPage implements Command {

	private static final Logger logger = LogManager.getLogger(OpenUserRequestPage.class.getName());

	/**
	 * Performs the command that reads user request parameters from relevant
	 * service class and sends them the JSP. If user have not a request, he can
	 * send a new request for changing tariff plan.
	 * <p>
	 * The method accesses the service {@code IAccountService}.
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
	 * @throws GetRequestServiceException
	 * @throws AddTariffServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IRequestService
	 * @see ITariffService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.VIEW_REQUEST_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			int userId = Integer.valueOf(session.getAttribute(Attributes.USER_ID).toString());
			try {
				ServiceFactory f = ServiceFactory.getInstance();
				IRequestService rService = f.getRequestService();
				ITariffService tService = f.getTariffService();
				boolean rFlag = rService.isUserGetRequest(userId);
				if (!rFlag) {
					List<Tariff> tariffsList = tService.getAllTariffs();
					request.setAttribute(Attributes.HAVE_REQUEST, false);
					request.setAttribute(Attributes.ALL_TARIFFS, tariffsList);
					request.setAttribute(Attributes.SELECTED_TARIFF, tariffsList.get(0).getId());
				} else {
					Request userRequest = rService.getRequestByUser(userId);
					Tariff rTariff = tService.getTariffById(userRequest.getTariffId());
					request.setAttribute(Attributes.HAVE_REQUEST, true);
					request.setAttribute(Attributes.CURRENT_REQUEST, userRequest);
					request.setAttribute(Attributes.TARIFF_REQUEST, rTariff);
				}
				request.getRequestDispatcher(JSPNames.USER_REQUEST_PAGE).forward(request, response);
			} catch (GetRequestServiceException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
						this.getClass().getSimpleName()), e);
				request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
			} catch (AddTariffServiceException e) {
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
