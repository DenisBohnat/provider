package by.epam.bohnat.provider.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bohnat.provider.bean.Tariff;
import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.ErrorMessages;
import by.epam.bohnat.provider.command.util.JSPNames;
import by.epam.bohnat.provider.command.util.LogMessages;
import by.epam.bohnat.provider.service.ITariffService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.tariff.GetTariffServiceException;

/**
 * Class {@code OpenEditingTariffPage} is an implementation of {@code Command}
 * for opening page for editing tariff.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class OpenEditingTariffPage implements Command {

	private static final Logger logger = LogManager.getLogger(OpenEditingTariffPage.class.getName());

	/**
	 * Performs the command that gets tariff entity from the the service layer
	 * and passes it to the relevant JSP.
	 * <p>
	 * Checks the access rights of the user who is performing this action. Only
	 * administrators can use this command. Administrator can edit information
	 * about current tariff and update it in data source. If the client is not
	 * the system administrator, the request will be redirected to the main
	 * page.
	 * 
	 * @param request
	 *            request to the servlet, used to access query parameters and
	 *            request / session / application attributes
	 * @param response
	 *            response from the servlet to the HTTP request
	 * @throws GetTariffServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see ITariffService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.EDIT_TARIFF_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			if (Integer.valueOf(session.getAttribute(Attributes.ROLE).toString()) == 1) {
				request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.EDIT_TARIFF_POSSIBILITY);
				// ???
				request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
			} else {
				int tariffId = Integer.valueOf(request.getParameter(Attributes.TARIFF_ID));
				try {
					ServiceFactory f = ServiceFactory.getInstance();
					ITariffService tService = f.getTariffService();
					Tariff tariff = tService.getTariffById(tariffId);
					request.setAttribute(Attributes.CURRENT_TARIFF, tariff);
					request.getRequestDispatcher(JSPNames.ADMIN_EDITING_TARIFF_PAGE).forward(request, response);
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
