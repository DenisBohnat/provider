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
import by.epam.bohnat.provider.command.util.SuccessMessages;
import by.epam.bohnat.provider.service.ITariffService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.tariff.AddTariffServiceException;

/**
 * Class {@code AddTariff} is an implementation of {@code Command} for adding
 * new tariff into data source.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class AddTariff implements Command {

	private static final Logger logger = LogManager.getLogger(AddTariff.class.getName());

	/**
	 * Performs the command that reads new tariff parameters from the JSP and
	 * sends them to the relevant service class.
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
	 * @throws AddTariffServiceException
	 * @throws ServiceException
	 * @see ServiceFactory
	 * @see ITariffService
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute(Attributes.REGISTERED_USER) == null) {
			request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.ADD_TARIFF_POSSIBILITY);
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} else {
			if (Integer.valueOf(session.getAttribute(Attributes.ROLE).toString()) == 1) {
				request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessages.ADD_TARIFF_POSSIBILITY);
				// ???
				request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
			} else {
				int typeId = Integer.parseInt(request.getParameter(Attributes.TYPE_ID));
				String name = request.getParameter(Attributes.TARIFF_NAME);
				float recSpeed = Float.parseFloat(request.getParameter(Attributes.REC_SPEED));
				float transSpeed = Float.parseFloat(request.getParameter(Attributes.TRANS_SPEED));
				float subscription = Float.parseFloat(request.getParameter(Attributes.SUBSTRIPTION));
				int trafficVolume = Integer.parseInt(request.getParameter(Attributes.TRAFFIC_VOLUME));
				float overdraft = Float.parseFloat(request.getParameter(Attributes.OVERDRAFT));

				Tariff tariff = new Tariff(0, name, typeId, recSpeed, transSpeed, subscription, trafficVolume,
						overdraft);
				try {
					ServiceFactory f = ServiceFactory.getInstance();
					ITariffService tService = f.getTariffService();
					tService.addTariff(tariff);
					logger.debug(String.format(LogMessages.TARIFF_ADDED, tariff.getName()));
					request.setAttribute(Attributes.SUCCESS_MESSAGE, SuccessMessages.TARIFF_ADDED);
					request.getRequestDispatcher(JSPNames.START_PAGE).forward(request, response);
				} catch (AddTariffServiceException e) {
					logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
							this.getClass().getSimpleName()), e);
					request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
					request.getRequestDispatcher(JSPNames.ADMIN_ADDING_TARIFF_PAGE).forward(request, response);
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
