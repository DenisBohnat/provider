package by.epam.bohnat.provider.command.impl.admin;

import java.io.IOException;

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
import by.epam.bohnat.provider.command.util.SuccessMessages;
import by.epam.bohnat.provider.service.IAccountService;
import by.epam.bohnat.provider.service.ITariffService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.account.EditAccountServiceException;
import by.epam.bohnat.provider.service.exception.account.GetAccountServiceException;
import by.epam.bohnat.provider.service.exception.tariff.GetTariffServiceException;

/**
 * Class {@code ChangeTariffPlan} is an implementation of {@code Command} for
 * changing tariff plan by user request.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class ChangeTariffPlan implements Command {

	private static final Logger logger = LogManager.getLogger(ChangeTariffPlan.class.getName());

	/**
	 * Performs the command that reads user ID and tariff ID parameters from the
	 * JSP and sends them to the relevant service class for changing user tariff
	 * plan by request.
	 * <p>
	 * The method accesses the service {@code IAccountService}.
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
	 * @throws GetAccountServiceException
	 * @throws EditAccountServiceException
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
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.CHANGE_TARIFF_BY_REQUEST_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			if (Integer.valueOf(session.getAttribute(Attributes.ROLE).toString()) == 1) {
				request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.CHANGE_TARIFF_BY_REQUEST_POSSIBILITY);
				// ???
				request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
			} else {

				int userId = Integer.parseInt(request.getParameter(Attributes.USER_ID));
				int tariffId = Integer.parseInt(request.getParameter(Attributes.TARIFF_ID));

				try {
					ServiceFactory f = ServiceFactory.getInstance();
					IAccountService aService = f.getAccountService();
					ITariffService tService = f.getTariffService();
					Account account = aService.getAccountByUserId(userId);
					Tariff aTariff = tService.getTariffById(account.getTariffId());
					account.setTariffId(tariffId);
					float trafficAmount = account.getAmount()
							- account.getSpentTraffic() * aTariff.getOverdraftAmount();
					account.setAmount(trafficAmount);
					aService.updateAccount(account);
					logger.debug(String.format(LogMessages.TARIFF_PLAN_CHANGED, userId));
					request.setAttribute(Attributes.SUCCESS_MESSAGE, SuccessMessages.ACCOUNT_UPDATED);
					request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
				} catch (GetAccountServiceException e) {
					logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
							this.getClass().getSimpleName()), e);
					request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
					request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
				} catch (EditAccountServiceException e) {
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
