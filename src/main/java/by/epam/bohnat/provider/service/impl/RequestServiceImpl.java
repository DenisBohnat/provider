package by.epam.bohnat.provider.service.impl;

import java.util.LinkedList;
import java.util.List;

import by.epam.bohnat.provider.bean.Request;
import by.epam.bohnat.provider.dao.DAOFactory;
import by.epam.bohnat.provider.dao.IRequestDAO;
import by.epam.bohnat.provider.dao.exception.DAOException;
import by.epam.bohnat.provider.service.IRequestService;
import by.epam.bohnat.provider.service.exception.ServiceException;
import by.epam.bohnat.provider.service.exception.request.AddRequestServiceException;
import by.epam.bohnat.provider.service.exception.request.GetRequestServiceException;
import by.epam.bohnat.provider.service.util.ExceptionMessages;
import by.epam.bohnat.provider.service.util.Validator;

/**
 * {@code RequestServiceImpl} is a {@code IRequestService} interface
 * implementation that works with {@code IRequestDAO} implementation.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see IRequestService
 */
public class RequestServiceImpl implements IRequestService {

	/**
	 * Number of requests per page
	 */
	private static final int NUMBER_OF_REQUESTS_ON_PAGE = 6;

	/**
	 * This method is used to get user request by user ID and validate input
	 * data
	 * 
	 * @param userId
	 *            user id
	 * @return found user request
	 * @throws GetRequestServiceException
	 *             if request entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IRequestDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public Request getRequestByUser(int userId) throws ServiceException {

		if (!Validator.validateId(userId)) {
			throw new GetRequestServiceException(ExceptionMessages.INVALID_ID);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IRequestDAO requestDAO = factory.getRequestDAO();
			Request request = requestDAO.getRequestByUser(userId);
			if (request == null) {
				throw new GetRequestServiceException(ExceptionMessages.NO_CURRENT_REQUEST_IN_DB);
			}
			return request;
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to check if user has a request
	 * 
	 * @param userId
	 *            user id
	 * @return boolean variable that indicates that the user has a request
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IRequestDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public boolean isUserGetRequest(int userId) throws ServiceException {

		if (!Validator.validateId(userId)) {
			throw new ServiceException(ExceptionMessages.INVALID_ID);
		}

		boolean flag = false;
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IRequestDAO requestDAO = factory.getRequestDAO();
			Request request = requestDAO.getRequestByUser(userId);
			if (request != null) {
				flag = true;
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return flag;
	}

	/**
	 * This method is used to delete request by ID and validate input data
	 * 
	 * @param requestId
	 *            request id
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IRequestDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public void deleteRequestById(int requestId) throws ServiceException {

		if (!Validator.validateId(requestId)) {
			throw new ServiceException(ExceptionMessages.INVALID_ID);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IRequestDAO requestDAO = factory.getRequestDAO();
			requestDAO.deleteRequestById(requestId);
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to get user request by ID and validate input data
	 * 
	 * @param id
	 *            request id
	 * @return found user request
	 * @throws GetRequestServiceException
	 *             if request entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IRequestDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public Request getRequestById(int id) throws ServiceException {

		if (!Validator.validateId(id)) {
			throw new GetRequestServiceException(ExceptionMessages.INVALID_ID);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IRequestDAO requestDAO = factory.getRequestDAO();
			Request request = requestDAO.getRequestById(id);
			if (request == null) {
				throw new GetRequestServiceException(ExceptionMessages.NO_CURRENT_REQUEST_IN_DB);
			}
			return request;
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to add new request and validate input data
	 * 
	 * @param request
	 *            object
	 * @throws AddRequestServiceException
	 *             if request entity not added
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IRequestDAO
	 * @see Validator
	 * @see ExceptionMessages
	 */
	@Override
	public void sendRequest(Request request) throws ServiceException {

		if (!Validator.validateObject(request)) {
			throw new AddRequestServiceException(ExceptionMessages.INVALID_OBJECT);
		}

		if (!Validator.validateId(request.getUserId())) {
			throw new AddRequestServiceException(ExceptionMessages.INVALID_ID);
		}

		if (!Validator.validateId(request.getTariffId())) {
			throw new AddRequestServiceException(ExceptionMessages.INVALID_ID);
		}

		if (!Validator.validateString(request.getDescription())) {
			throw new AddRequestServiceException(ExceptionMessages.INVALID_STRING);
		}

		try {
			DAOFactory factory = DAOFactory.getInstance();
			IRequestDAO requestDAO = factory.getRequestDAO();
			boolean flag = requestDAO.sendRequest(request);
			if (flag == false) {
				throw new AddRequestServiceException(ExceptionMessages.REQUEST_NOT_ADDED);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
	}

	/**
	 * This method is used to get all requests
	 * 
	 * @return list of requests
	 * @throws GetRequestServiceException
	 *             if requests entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IRequestDAO
	 * @see ExceptionMessages
	 */
	@Override
	public List<Request> getAllRequests() throws ServiceException {
		List<Request> requestList = new LinkedList<Request>();
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IRequestDAO requestDAO = factory.getRequestDAO();
			requestList = requestDAO.getAllRequests();
			if (requestList.isEmpty()) {
				throw new GetRequestServiceException(ExceptionMessages.NO_CURRENT_REQUEST_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return requestList;
	}

	/**
	 * This method is used to get counts of pages needed to locate all requests
	 * 
	 * @return total pages amount
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IRequestDAO
	 * @see ExceptionMessages
	 */
	@Override
	public int getNumberOfRequestsPages() throws ServiceException {
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IRequestDAO requestDAO = factory.getRequestDAO();
			int numberOfRequests = requestDAO.getNumberOfRequests();
			if (numberOfRequests % NUMBER_OF_REQUESTS_ON_PAGE == 0) {
				return numberOfRequests / NUMBER_OF_REQUESTS_ON_PAGE;
			} else {
				return numberOfRequests / NUMBER_OF_REQUESTS_ON_PAGE + 1;
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}

	}

	/**
	 * This method is used to return necessary part of all requests and validate
	 * input data
	 * 
	 * @param pageNumber
	 *            page number
	 * @return list of requests
	 * @throws GetRequestServiceException
	 *             if request entity not found in data source
	 * @throws ServiceException
	 *             if any error occurred while processing method
	 * @see DAOFactory
	 * @see IRequestDAO
	 * @see ExceptionMessages
	 * @see Validator
	 */
	@Override
	public List<Request> getRequestsOnCurrentPage(int pageNumber) throws ServiceException {
		if (!Validator.validateInt(pageNumber)) {
			throw new GetRequestServiceException(ExceptionMessages.INVALID_INT);
		}
		List<Request> requestList = new LinkedList<Request>();
		int start = (pageNumber - 1) * NUMBER_OF_REQUESTS_ON_PAGE;
		try {
			DAOFactory factory = DAOFactory.getInstance();
			IRequestDAO requestDAO = factory.getRequestDAO();
			requestList = requestDAO.getRequestPart(start, NUMBER_OF_REQUESTS_ON_PAGE);
			if (requestList.isEmpty()) {
				throw new GetRequestServiceException(ExceptionMessages.NO_REQUESRS_IN_DB);
			}
		} catch (DAOException e) {
			throw new ServiceException(ExceptionMessages.SOURCE_ERROR, e);
		}
		return requestList;
	}

}
