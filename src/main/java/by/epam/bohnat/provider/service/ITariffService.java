package by.epam.bohnat.provider.service;

import java.util.List;

import by.epam.bohnat.provider.bean.Tariff;
import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Defines methods that receive parameters from Command implementations, verify
 * them, construct necessary entities if needed and then pass them to the DAO
 * layer, possibly getting some objects or primitive values back and passing
 * them further back to the commands.
 * <p>
 * Represents an interface of a service for tariff-related actions.
 * 
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public interface ITariffService {

	/**
	 * Receives a particular list of all tariffs from the DAO layer depending on
	 * the current page and passes it back to the Controller layer or throws an
	 * exception
	 * 
	 * @return list of tariffs
	 * @throws ServiceException
	 */
	List<Tariff> getAllTariffs() throws ServiceException;

	/**
	 * Receives a particular list of all unlimited tariffs from the DAO layer
	 * depending on the current page and passes it back to the Controller layer
	 * or throws an exception
	 * 
	 * @return list of tariffs
	 * @throws ServiceException
	 */
	List<Tariff> getUnlimitedTariffs() throws ServiceException;

	/**
	 * Receives a particular list of all limited tariffs from the DAO layer
	 * depending on the current page and passes it back to the Controller layer
	 * or throws an exception
	 * 
	 * @return list of tariffs
	 * @throws ServiceException
	 */
	List<Tariff> getLimitedTariffs() throws ServiceException;

	/**
	 * Verifies parameters and either passes to the DAO layer or throws an
	 * exception
	 * 
	 * @param id
	 *            tariff id
	 * @throws ServiceException
	 */
	void deleteTariff(int id) throws ServiceException;

	/**
	 * Constructs a new tariff entity based on input parameters received from
	 * the Controller layer, verifies them and either passes to the DAO layer or
	 * throws an exception
	 * 
	 * @param tariff
	 *            object
	 * @throws ServiceException
	 */
	void addTariff(Tariff tariff) throws ServiceException;

	/**
	 * Verifies the input parameter and passes it to the DAO layer, receives the
	 * request entity, returns it back to the Controller layer or throws an
	 * exception
	 * 
	 * @param id
	 *            tariff id
	 * @return found tariff entity
	 * @throws ServiceException
	 */
	Tariff getTariffById(int id) throws ServiceException;

	/**
	 * Constructs an updated tariff entity based on input parameters received
	 * from the Controller layer, verifies them and either passes to the DAO
	 * layer or throws an exception
	 * 
	 * @param tariff
	 *            object
	 * @throws ServiceException
	 */
	void updateTariff(Tariff tariff) throws ServiceException;

	/**
	 * Verifies parameters and either passes to the DAO layer or throws an
	 * exception
	 * 
	 * @param id
	 *            tariff id
	 * @return tariff name
	 * @throws ServiceException
	 */
	String getTariffNameById(int id) throws ServiceException;

}
