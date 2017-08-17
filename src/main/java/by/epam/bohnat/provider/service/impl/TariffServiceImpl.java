package by.epam.bohnat.provider.service.impl;

import java.util.LinkedList;
import java.util.List;

import by.epam.bohnat.provider.bean.Tariff;
import by.epam.bohnat.provider.dao.DAOFactory;
import by.epam.bohnat.provider.dao.ITariffDAO;
import by.epam.bohnat.provider.dao.exception.DAOException;
import by.epam.bohnat.provider.service.ITariffService;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.tariff.AddTariffServiceException;
import by.epam.bohnat.provider.service.exception.tariff.EditTariffServiceException;
import by.epam.bohnat.provider.service.exception.tariff.GetTariffServiceException;
import by.epam.bohnat.provider.service.util.ExceptionMessages;
import by.epam.bohnat.provider.service.util.Validator;

/**
 * {@code TariffServiceImpl} is a {@code ITariffService} interface
 * implementation that works with {@code ITariffDAO} implementation.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ITariffService
 */
public class TariffServiceImpl implements ITariffService {

	/**
	 * This method is used to get all tariffs
	 * 
	 * @return list of all tariffs
	 * @throws GetTariffServiceException
	 *             if tariff entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see ITariffDAO
	 */
	@Override
	public List<Tariff> getAllTariffs() throws ServiceException {
		List<Tariff> tariffList = new LinkedList<Tariff>();
		try {
			DAOFactory factory = DAOFactory.getInstance();
			ITariffDAO tariffDAO = factory.getTariffDAO();
			tariffList = tariffDAO.getAllTariffs();
			if (tariffList.isEmpty()) {
				throw new GetTariffServiceException(ExceptionMessages.NO_TARIFFS_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return tariffList;
	}

	/**
	 * This method is used to get all unlimited tariffs
	 * 
	 * @return list of unlimited tariffs
	 * @throws GetTariffServiceException
	 *             if tariff entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see ITariffDAO
	 */
	@Override
	public List<Tariff> getUnlimitedTariffs() throws ServiceException {
		List<Tariff> unlinTariffList = new LinkedList<Tariff>();
		try {
			DAOFactory factory = DAOFactory.getInstance();
			ITariffDAO tariffDAO = factory.getTariffDAO();
			unlinTariffList = tariffDAO.getUnlimitedTariffs();
			if (unlinTariffList.isEmpty()) {
				throw new GetTariffServiceException(ExceptionMessages.NO_TARIFFS_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return unlinTariffList;
	}

	/**
	 * This method is used to get all limited tariffs
	 * 
	 * @return list of limited tariffs
	 * @throws GetTariffServiceException
	 *             if tariff entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see ITariffDAO
	 */
	@Override
	public List<Tariff> getLimitedTariffs() throws ServiceException {
		List<Tariff> linTariffList = new LinkedList<Tariff>();
		try {
			DAOFactory factory = DAOFactory.getInstance();
			ITariffDAO tariffDAO = factory.getTariffDAO();
			linTariffList = tariffDAO.getLimitedTariffs();
			if (linTariffList.isEmpty()) {
				throw new GetTariffServiceException(ExceptionMessages.NO_TARIFFS_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return linTariffList;
	}

	/**
	 * This method is used to delete tariff by ID and validate input data
	 * 
	 * @param id
	 *            tariff id
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see ITariffDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public void deleteTariff(int id) throws ServiceException {

		if (!Validator.validateId(id)) {
			throw new ServiceException(ExceptionMessages.INVALID_ID);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			ITariffDAO tariffDAO = factory.getTariffDAO();
			tariffDAO.deleteTariffById(id);
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to add new tariff and validate input data
	 * 
	 * @param tariff
	 *            object
	 * @throws AddTariffServiceException
	 *             if tariff entity not added
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see ITariffDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public void addTariff(Tariff tariff) throws ServiceException {

		if (!Validator.validateObject(tariff)) {
			throw new AddTariffServiceException(ExceptionMessages.INVALID_OBJECT);
		}

		if (!Validator.validateString(tariff.getName())) {
			throw new AddTariffServiceException(ExceptionMessages.INVALID_TARIFF_NAME);
		}

		if (!Validator.validateId(tariff.getType())) {
			throw new AddTariffServiceException(ExceptionMessages.INVALID_TARIFF_TYPE);
		}

		if (tariff.getRecSpeed() < 0) {
			throw new AddTariffServiceException(ExceptionMessages.INVALID_TARIFF_REC_SPEED);
		}

		if (tariff.getTransSpeed() < 0) {
			throw new AddTariffServiceException(ExceptionMessages.INVALID_TARIFF_TRANS_SPEED);
		}

		if (tariff.getSubscriptionFee() < 0) {
			throw new AddTariffServiceException(ExceptionMessages.INVALID_TARIFF_SUBSTRIPTION_FEE);
		}

		if (!Validator.validateInt(tariff.getTrafficVolume())) {
			throw new AddTariffServiceException(ExceptionMessages.INVALID_TARIFF_TRAFFIC_VOLUME);
		}

		if (tariff.getOverdraftAmount() < 0) {
			throw new AddTariffServiceException(ExceptionMessages.INVALID_TARIFF_OVERDRAFT_AMOUNT);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			ITariffDAO tariffDAO = factory.getTariffDAO();
			boolean flag = tariffDAO.addTariff(tariff);
			if (flag == false) {
				throw new AddTariffServiceException(ExceptionMessages.TARIFF_NOT_ADDED);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to get tariff by ID and validate input data
	 * 
	 * @param id
	 *            tariff id
	 * @return found tariff or null if it was not found
	 * @throws GetTariffServiceException
	 *             if tariff entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see ITariffDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public Tariff getTariffById(int id) throws ServiceException {

		if (!Validator.validateId(id)) {
			throw new GetTariffServiceException(ExceptionMessages.INVALID_ID);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			ITariffDAO tariffDAO = factory.getTariffDAO();
			Tariff tariff = tariffDAO.getTariffById(id);
			if (tariff == null) {
				throw new GetTariffServiceException(ExceptionMessages.NO_TARIFFS_IN_DB);
			}
			return tariff;
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to update tariff and validate input data
	 * 
	 * @param tariff
	 *            object
	 * @throws EditTariffServiceException
	 *             if tariff entity not updated
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see ITariffDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public void updateTariff(Tariff tariff) throws ServiceException {

		if (!Validator.validateObject(tariff)) {
			throw new EditTariffServiceException(ExceptionMessages.INVALID_OBJECT);
		}

		if (!Validator.validateId(tariff.getId())) {
			throw new EditTariffServiceException(ExceptionMessages.INVALID_ID);
		}

		if (!Validator.validateString(tariff.getName())) {
			throw new EditTariffServiceException(ExceptionMessages.INVALID_TARIFF_NAME);
		}

		if (!Validator.validateId(tariff.getType())) {
			throw new EditTariffServiceException(ExceptionMessages.INVALID_TARIFF_TYPE);
		}

		if (tariff.getRecSpeed() < 0) {
			throw new EditTariffServiceException(ExceptionMessages.INVALID_TARIFF_REC_SPEED);
		}

		if (tariff.getTransSpeed() < 0) {
			throw new EditTariffServiceException(ExceptionMessages.INVALID_TARIFF_TRANS_SPEED);
		}

		if (tariff.getSubscriptionFee() < 0) {
			throw new EditTariffServiceException(ExceptionMessages.INVALID_TARIFF_SUBSTRIPTION_FEE);
		}

		if (!Validator.validateInt(tariff.getTrafficVolume())) {
			throw new EditTariffServiceException(ExceptionMessages.INVALID_TARIFF_TRAFFIC_VOLUME);
		}

		if (tariff.getOverdraftAmount() < 0) {
			throw new EditTariffServiceException(ExceptionMessages.INVALID_TARIFF_OVERDRAFT_AMOUNT);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			ITariffDAO tariffDAO = factory.getTariffDAO();
			boolean flag = tariffDAO.updateTariff(tariff);
			if (flag == false) {
				throw new EditTariffServiceException(ExceptionMessages.TARIFF_NOT_UPDATED);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to get tariff name and validate input data
	 * 
	 * @param id
	 *            tariff id
	 * @return tariff name
	 * @throws GetTariffServiceException
	 *             if tariff entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see ITariffDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public String getTariffNameById(int id) throws ServiceException {

		if (!Validator.validateId(id)) {
			throw new GetTariffServiceException(ExceptionMessages.INVALID_ID);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			ITariffDAO tariffDAO = factory.getTariffDAO();
			Tariff tariff = tariffDAO.getTariffById(id);
			if (tariff == null) {
				throw new GetTariffServiceException(ExceptionMessages.NO_TARIFFS_IN_DB);
			}
			return tariff.getName();

		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

}
