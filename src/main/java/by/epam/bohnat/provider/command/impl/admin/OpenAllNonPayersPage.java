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

import by.epam.bohnat.provider.bean.Account;
import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.ErrorMessages;
import by.epam.bohnat.provider.command.util.JSPNames;
import by.epam.bohnat.provider.command.util.LogMessages;
import by.epam.bohnat.provider.service.IAccountService;
import by.epam.bohnat.provider.service.IUserService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.account.GetAccountServiceException;
import by.epam.bohnat.provider.service.exception.user.GetUserServiceException;

/**
 * Class {@code OpenAllNonPayersPage} is an implementation of {@code Command}
 * for opening page with all non-payers.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class OpenAllNonPayersPage implements Command {

	private static final Logger logger = LogManager.getLogger(OpenAllNonPayersPage.class.getName());

	/**
	 * Performs the command that gets list of all non-payers (account entities)
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
	 * @throws GetAccountServiceException
	 * @throws GetUserServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see IAccountService
	 * @see IUserService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.CHANGE_NON_PAYERS_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			if (Integer.valueOf(session.getAttribute(Attributes.ROLE).toString()) == 1) {
				request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.CHANGE_NON_PAYERS_POSSIBILITY);
				// ???
				request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
			} else {

				int pageNumber = Integer.parseInt(request.getParameter(Attributes.PAGE_NUMBER));

				try {
					ServiceFactory f = ServiceFactory.getInstance();
					IAccountService aService = f.getAccountService();
					IUserService uService = f.getUserService();

					int amountPage = aService.getNumberOfNonPayersPages();
					List<Account> accList = aService.getNonPayersOnCurrentPage(pageNumber);

					List<String> names = new LinkedList<String>();
					List<String> surnames = new LinkedList<String>();

					for (Account acc : accList) {
						names.add(uService.getNameById(acc.getUserId()));
						surnames.add(uService.getSurnameById(acc.getUserId()));
					}
					request.setAttribute(Attributes.DEFAULTERS_ACCOUNTS, accList);
					request.setAttribute(Attributes.USER_NAMES, names);
					request.setAttribute(Attributes.USER_SURNAMES, surnames);
					request.setAttribute(Attributes.CURRENT_PAGE, pageNumber);
					request.setAttribute(Attributes.PAGE_AMOUNT, amountPage);
					request.getRequestDispatcher(JSPNames.ADMIN_NON_PAYERS).forward(request, response);
				} catch (GetAccountServiceException e) {
					logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
							this.getClass().getSimpleName()), e);
					request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
					request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
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
