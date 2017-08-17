package by.epam.bohnat.provider.dao;

import java.util.List;
import by.epam.bohnat.provider.bean.Tariff;
import by.epam.bohnat.provider.dao.exception.DAOException;

/**
 * Defines methods for implementing in the DAO layer for the tariff entity.
 * <p>
 * Methods of reading / writing data throw {@link DAOException}, indicating a
 * read / write error.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public interface ITariffDAO {

	/**
	 * This method is used to return all tariffs from the data source
	 * 
	 * @return list of all tariffs
	 * @throws DAOException
	 */
	List<Tariff> getAllTariffs() throws DAOException;

	/**
	 * This method is used to return all unlimited tariffs from the data source
	 * 
	 * @return list of unlimited tariffs
	 * @throws DAOException
	 */
	List<Tariff> getUnlimitedTariffs() throws DAOException;

	/**
	 * This method is used to return all limited tariffs from the data source
	 * 
	 * @return list of limited tariffs
	 * @throws DAOException
	 */
	List<Tariff> getLimitedTariffs() throws DAOException;

	/**
	 * This method is used to delete tariff from the data source by ID
	 * 
	 * @param id
	 *            tariff id
	 * @throws DAOException
	 */
	void deleteTariffById(int id) throws DAOException;

	/**
	 * This method is used to update tariff in the data source
	 * 
	 * @param tariff
	 *            object
	 * @return boolean variable that indicates that updating tariff entity in
	 *         the data source was successful
	 * @throws DAOException
	 */
	boolean updateTariff(Tariff tariff) throws DAOException;

	/**
	 * This method is used to add new tariff to the data source
	 * 
	 * @param tariff
	 *            object
	 * @return boolean variable that indicates that adding new tariff entity to
	 *         the data source was successful
	 * @throws DAOException
	 */
	boolean addTariff(Tariff tariff) throws DAOException;

	/**
	 * This method is used to search tariff in the data source by ID
	 * 
	 * @param id
	 *            tariff id
	 * @return found tariff or null if it was not found
	 * @throws DAOException
	 */
	Tariff getTariffById(int id) throws DAOException;

}
