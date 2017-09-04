package by.epam.bohnat.provider.command.impl.user;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

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
import by.epam.bohnat.provider.service.exception.account.AddAccountServiceException;

/**
 * Class {@code BuyTariff} is an implementation of {@code Command} for buying a
 * new tariff plan and creating new user account.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class BuyTariff implements Command {

	private static final Logger logger = LogManager.getLogger(BuyTariff.class.getName());

	/**
	 * New account after creating not blocked.
	 */
	private static final int NOT_BLOCKED_ACCOUNT = 1;

	/**
	 * Amount after creating account is not debt.
	 */
	private static final int AMOUNT_AFTER_CREATION = 0;

	/**
	 * Spent traffic after creating account is not debt.
	 */
	private static final int SPENT_TRAFFIC_AFTER_CREATION = 0;

	/**
	 * Performs the command that reads new user account parameters from the JSP
	 * and sends them to the relevant service class.
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
	 * @throws AddAccountServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IAccountService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.BUY_TARIFF_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			int userId = Integer.valueOf(session.getAttribute(Attributes.USER_ID).toString());
			int tariffId = Integer.parseInt(request.getParameter(Attributes.TYPE_ID));
			Long accNumber = generateAccNumber();

			Date paymentDate = Date.valueOf(LocalDate.now());

			Account account = new Account(0, userId, tariffId, NOT_BLOCKED_ACCOUNT, accNumber, AMOUNT_AFTER_CREATION,
					paymentDate, SPENT_TRAFFIC_AFTER_CREATION);

			try {
				ServiceFactory f = ServiceFactory.getInstance();
				IAccountService aService = f.getAccountService();
				aService.addAccount(account);
				logger.debug(String.format(LogMessages.TARIFF_BOUGHT, userId));
				session.setAttribute(Attributes.HAVE_ACCOUNT, true);
				request.setAttribute(Attributes.SUCCESS_MESSAGE, SuccessMessages.TARIFF_BOUGHT);
				request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
			} catch (AddAccountServiceException e) {
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

	/**
	 * Generate account number
	 * 
	 * @return account number
	 */
	private long generateAccNumber() {
		StringBuilder accNumber = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < 6; i++) {
			int randomNumber = rand.nextInt(8) + 1;
			accNumber.append(randomNumber);
		}
		return Long.parseLong(accNumber.toString());
	}

}
