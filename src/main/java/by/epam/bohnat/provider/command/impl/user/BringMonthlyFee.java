package by.epam.bohnat.provider.command.impl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bohnat.provider.bean.Account;
import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.ErrorMessages;
import by.epam.bohnat.provider.command.util.JSPNames;
import by.epam.bohnat.provider.command.util.LogMessages;
import by.epam.bohnat.provider.command.util.SuccessMessages;
import by.epam.bohnat.provider.service.IAccountService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.account.EditAccountServiceException;
import by.epam.bohnat.provider.service.exception.account.GetAccountServiceException;

/**
 * Class {@code BringMonthlyFee} is an implementation of {@code Command} for
 * bringing fee.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class BringMonthlyFee implements Command {

	private static final Logger logger = LogManager.getLogger(BringMonthlyFee.class.getName());

	/**
	 * Performs the command that reads user account parameters from the JSP and
	 * sends them to the relevant service class.
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
	 * @throws EditAccountServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IAccountService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.MONTHLY_FEE_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {

			int userId = Integer.valueOf(session.getAttribute(Attributes.USER_ID).toString());
			float fee = Float.parseFloat(request.getParameter(Attributes.ACCOUNT_FEE));

			try {
				ServiceFactory f = ServiceFactory.getInstance();
				IAccountService aService = f.getAccountService();
				Account account = aService.getAccountByUserId(userId);
				account.setAmount(fee);
				aService.updateAccount(account);
				logger.debug(String.format(LogMessages.MONTHLY_FEE_ADDED, userId));
				request.setAttribute(Attributes.SUCCESS_MESSAGE, SuccessMessages.MONTHLY_FEE_ADDED);
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
			} catch (ServiceException e) {
				logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
						this.getClass().getSimpleName()), e);
				request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(JSPNames.ERROR_PAGE).forward(request, response);
			}
		}
	}
}
