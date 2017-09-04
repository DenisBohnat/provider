package by.epam.bohnat.provider.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.ErrorMessages;
import by.epam.bohnat.provider.command.util.JSPNames;
import by.epam.bohnat.provider.command.util.LogMessages;
import by.epam.bohnat.provider.command.util.SuccessMessages;
import by.epam.bohnat.provider.service.ITariffService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Class {@code DeleteTariff} is an implementation of {@code Command} for
 * deleting tariff from data source.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class DeleteTariff implements Command {

	private static final Logger logger = LogManager.getLogger(DeleteTariff.class.getName());

	/**
	 * Identifier that indicates that the user is not an administrator
	 */
	private static final int NOT_ADMIN = 1;
	
	/**
	 * Performs the command that reads tariff ID parameter from the JSP and
	 * sends them to the relevant service class for deleting from the data
	 * source.
	 * <p>
	 * The method accesses the service {@code ITariffService}.
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
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see ITariffService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.DELETE_TARIFF_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			if (Integer.valueOf(session.getAttribute(Attributes.ROLE).toString()) == NOT_ADMIN) {
				request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.DELETE_TARIFF_POSSIBILITY);
				request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
			} else {

				int tariffId = Integer.valueOf(request.getParameter(Attributes.TARIFF_ID));

				try {
					ServiceFactory f = ServiceFactory.getInstance();
					ITariffService tService = f.getTariffService();
					tService.deleteTariff(tariffId);
					logger.debug(String.format(LogMessages.TARIFF_DELETED, tariffId));
					request.setAttribute(Attributes.SUCCESS_MESSAGE, SuccessMessages.TARIFF_DELETED);
					request.getRequestDispatcher("/Controller?command=show_tariffs").forward(request, response);
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
