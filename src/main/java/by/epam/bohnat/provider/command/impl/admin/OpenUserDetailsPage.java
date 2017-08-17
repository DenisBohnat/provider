package by.epam.bohnat.provider.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bohnat.provider.bean.Account;
import by.epam.bohnat.provider.bean.Request;
import by.epam.bohnat.provider.bean.Tariff;
import by.epam.bohnat.provider.bean.User;
import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.ErrorMessages;
import by.epam.bohnat.provider.command.util.JSPNames;
import by.epam.bohnat.provider.command.util.LogMessages;
import by.epam.bohnat.provider.service.IAccountService;
import by.epam.bohnat.provider.service.IRequestService;
import by.epam.bohnat.provider.service.ITariffService;
import by.epam.bohnat.provider.service.IUserService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.tariff.GetTariffServiceException;
import by.epam.bohnat.provider.service.exception.user.GetUserServiceException;

/**
 * Class {@code OpenUserDetailsPage} is an implementation of {@code Command} for
 * opening page with user profile details, account details, request details.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class OpenUserDetailsPage implements Command {

	private static final Logger logger = LogManager.getLogger(OpenUserDetailsPage.class.getName());

	/**
	 * Performs the command that gets tariff, user, account, request entities
	 * from the the service layer and passes it to the relevant JSP.
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
	 * @throws GetUserServiceException
	 * @throws GetTariffServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IUserService
	 * @see IRequestService
	 * @see IAccountService
	 * @see ITariffService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.WORK_USER_INFORMATION_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			if (Integer.valueOf(session.getAttribute(Attributes.ROLE).toString()) == 1) {
				request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.WORK_USER_INFORMATION_POSSIBILITY);
				// ???
				request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
			} else {
				int curUserId = Integer.valueOf(request.getParameter(Attributes.CURRENT_USER_ID));
				try {
					ServiceFactory f = ServiceFactory.getInstance();
					IUserService uService = f.getUserService();
					User curUser = uService.getUserById(curUserId);

					if (curUser.getRole() == 1) {
						IRequestService rService = f.getRequestService();
						IAccountService aService = f.getAccountService();
						ITariffService tService = f.getTariffService();
						boolean aFlag = aService.isUserGetAccount(curUserId);
						boolean rFlag = rService.isUserGetRequest(curUserId);
						if (!aFlag) {
							request.setAttribute(Attributes.HAVE_ACCOUNT, false);
						} else {
							Account account = aService.getAccountByUserId(curUserId);
							Tariff aTariff = tService.getTariffById(account.getTariffId());
							request.setAttribute(Attributes.HAVE_ACCOUNT, true);
							request.setAttribute(Attributes.CURRENT_ACCOUNT, account);
							request.setAttribute(Attributes.TARIFF_ACCOUNT, aTariff);
						}
						if (!rFlag) {
							request.setAttribute(Attributes.HAVE_REQUEST, false);
						} else {
							Request userRequest = rService.getRequestByUser(curUserId);
							Tariff rTariff = tService.getTariffById(userRequest.getTariffId());
							request.setAttribute(Attributes.HAVE_REQUEST, true);
							request.setAttribute(Attributes.CURRENT_REQUEST, userRequest);
							request.setAttribute(Attributes.TARIFF_REQUEST, rTariff);
						}
						request.setAttribute(Attributes.CURRENT_USER, curUser);
						request.setAttribute(Attributes.CURRENT_USER_ROLE, curUser.getRole());
						request.getRequestDispatcher(JSPNames.ADMIN_USER_DETAILS_PAGE).forward(request, response);
					} else {
						request.setAttribute(Attributes.CURRENT_USER, curUser);
						request.setAttribute(Attributes.CURRENT_USER_ROLE, curUser.getRole());
						request.getRequestDispatcher(JSPNames.ADMIN_USER_DETAILS_PAGE).forward(request, response);
					}
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
