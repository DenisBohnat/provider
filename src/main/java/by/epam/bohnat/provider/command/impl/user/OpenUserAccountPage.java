package by.epam.bohnat.provider.command.impl.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bohnat.provider.bean.Account;
import by.epam.bohnat.provider.bean.Tariff;
import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.ErrorMessages;
import by.epam.bohnat.provider.command.util.JSPNames;
import by.epam.bohnat.provider.command.util.LogMessages;
import by.epam.bohnat.provider.service.IAccountService;
import by.epam.bohnat.provider.service.ITariffService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.account.GetAccountServiceException;
import by.epam.bohnat.provider.service.exception.tariff.GetTariffServiceException;

/**
 * Class {@code OpenUserAccountPage} is an implementation of {@code Command} for
 * opening user account page.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class OpenUserAccountPage implements Command {

	private static final Logger logger = LogManager.getLogger(OpenUserAccountPage.class.getName());

	/**
	 * Performs the command that reads user account parameters from relevant
	 * service class and sends them the JSP. If user have not an account, he can
	 * buy a new tariff plan.
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
	 * @throws GetAccountServiceException
	 * @throws GetTariffServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IAccountService
	 * @see ITariffService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.VIEW_ACCOUNT_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			int userId = Integer.valueOf(session.getAttribute(Attributes.USER_ID).toString());
			try {
				ServiceFactory f = ServiceFactory.getInstance();
				IAccountService aService = f.getAccountService();
				ITariffService tService = f.getTariffService();
				boolean aFlag = aService.isUserGetAccount(userId);
				if (!aFlag) {
					List<Tariff> tariffsList = tService.getAllTariffs();
					request.setAttribute(Attributes.HAVE_ACCOUNT, false);
					request.setAttribute(Attributes.ALL_TARIFFS, tariffsList);
					request.setAttribute(Attributes.SELECTED_TARIFF, tariffsList.get(0).getId());
				} else {
					Account account = aService.getAccountByUserId(userId);
					Tariff aTariff = tService.getTariffById(account.getTariffId());
					request.setAttribute(Attributes.HAVE_ACCOUNT, true);
					request.setAttribute(Attributes.CURRENT_ACCOUNT, account);
					request.setAttribute(Attributes.TARIFF_ACCOUNT, aTariff);
				}
				request.getRequestDispatcher(JSPNames.USER_ACCOUNT_PAGE).forward(request, response);
			} catch (GetAccountServiceException e) {
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
