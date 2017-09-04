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
import by.epam.bohnat.provider.bean.Payment;
import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.ErrorMessages;
import by.epam.bohnat.provider.command.util.JSPNames;
import by.epam.bohnat.provider.command.util.LogMessages;
import by.epam.bohnat.provider.service.IAccountService;
import by.epam.bohnat.provider.service.IPaymentService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.payment.GetPaymentServiceException;

/**
 * Class {@code OpenUserPaymentPage} is an implementation of {@code Command} for
 * opening user payment page.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class OpenUserPaymentPage implements Command {

	private static final Logger logger = LogManager.getLogger(OpenUserPaymentPage.class.getName());

	/**
	 * Performs the command that reads user payment parameters from relevant
	 * service class and sends them the JSP.
	 * <p>
	 * The method accesses the service {@code IPaymentService}.
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
	 * @throws GetPaymentServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IAccountService
	 * @see IPaymentService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.WORK_WITH_PAYMENT_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {

			int userId = Integer.valueOf(session.getAttribute(Attributes.USER_ID).toString());
			int pageNumber = Integer.parseInt(request.getParameter(Attributes.PAGE_NUMBER));

			try {
				ServiceFactory f = ServiceFactory.getInstance();
				IAccountService aService = f.getAccountService();
				IPaymentService pService = f.getPaymentService();
				Account account = aService.getAccountByUserId(userId);

				int amountPage = pService.getNumberOfPayments(account.getId());
				List<Payment> pList = pService.getPaymentsOnCurrentPage(pageNumber, account.getId());

				request.setAttribute(Attributes.USER_PAYMENTS, pList);
				request.setAttribute(Attributes.CURRENT_PAGE, pageNumber);
				request.setAttribute(Attributes.PAGE_AMOUNT, amountPage);
				request.getRequestDispatcher(JSPNames.USER_PAYMENT_PAGE).forward(request, response);
			} catch (GetPaymentServiceException e) {
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
