package by.epam.bohnat.provider.command.impl.general;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bohnat.provider.bean.Tariff;
import by.epam.bohnat.provider.command.Command;
import by.epam.bohnat.provider.command.util.Attributes;
import by.epam.bohnat.provider.command.util.JSPNames;
import by.epam.bohnat.provider.command.util.LogMessages;
import by.epam.bohnat.provider.service.ITariffService;
import by.epam.bohnat.provider.service.ServiceFactory;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.tariff.GetTariffServiceException;

/**
 * Class {@code OpenUnlimTariffsPage} is an implementation of {@code Command}
 * for opening page with all unlimited tariffs.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see Command
 */
public class OpenUnlimTariffsPage implements Command {

	private static final Logger logger = LogManager.getLogger(OpenUnlimTariffsPage.class.getName());

	/**
	 * Performs the command that gets list of all unlimited tariffs entities
	 * from the the service layer and passes it to the relevant JSP.
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
		try {
			ServiceFactory f = ServiceFactory.getInstance();
			ITariffService tService = f.getTariffService();
			List<Tariff> unlimTariffs = tService.getUnlimitedTariffs();
			request.setAttribute(Attributes.UNLIMITED_TARIFFS, unlimTariffs);
			request.getRequestDispatcher(JSPNames.UNLIM_TARIFFS_PAGE).forward(request, response);
		} catch (GetTariffServiceException e) {
			logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
					this.getClass().getSimpleName()), e);
			request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JSPNames.INDEX_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.error(String.format(LogMessages.EXCEPTION_IN_COMMAND, e.getClass().getSimpleName(),
					this.getClass().getSimpleName()), e);
			request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
			request.getRequestDispatcher(JSPNames.ERROR_PAGE).forward(request, response);
		}
	}
}
