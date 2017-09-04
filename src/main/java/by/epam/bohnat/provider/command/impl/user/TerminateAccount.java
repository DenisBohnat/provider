package by.epam.bohnat.provider.command.impl.user;

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
import by.epam.bohnat.provider.service.exception.account.GetAccountServiceException;
import by.epam.bohnat.provider.service.exception.tariff.GetTariffServiceException;

/**
 * Class {@code TerminateAccount} is an implementation of {@code Command} for
 * terminating user account.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class TerminateAccount implements Command {

	private static final Logger logger = LogManager.getLogger(TerminateAccount.class.getName());

	/**
	 * Performs the command that reads user account parameters from relevant
	 * service class and sends them the JSP. Use can terminate account.
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
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.TERMINATE_ACCOUNT_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {

			int accountId = Integer.valueOf(request.getParameter(Attributes.ACCOUNT_ID));

			try {
				ServiceFactory f = ServiceFactory.getInstance();
				IAccountService aService = f.getAccountService();
				ITariffService tService = f.getTariffService();
				Account account = aService.getAccountById(accountId);
				Tariff aTariff = tService.getTariffById(account.getTariffId());
				float spentTraficAmount = account.getSpentTraffic() * aTariff.getOverdraftAmount();
				float repaidAmount = spentTraficAmount + aTariff.getSubscriptionFee();
				if (account.getAmount() >= repaidAmount) {
					aService.terminateAccount(accountId);
					logger.debug(String.format(LogMessages.ACCOUNT_DELETED, accountId));
					session.setAttribute(Attributes.HAVE_ACCOUNT, false);
					request.setAttribute(Attributes.SUCCESS_MESSAGE, SuccessMessages.ACCOUNT_DELETED);
					request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
				} else {
					request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.ACCOUNT_TERMINATED);
					request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
				}
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
