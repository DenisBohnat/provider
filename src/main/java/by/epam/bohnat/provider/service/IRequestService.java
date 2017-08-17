package by.epam.bohnat.provider.service;

import java.util.List;

import by.epam.bohnat.provider.bean.Request;
import by.epam.bohnat.provider.service.exception.ServiceException;

/**
 * Defines methods that receive parameters from Command implementations, verify
 * them, construct necessary entities if needed and then pass them to the DAO
 * layer, possibly getting some objects or primitive values back and passing
 * them further back to the commands.
 * <p>
 * Represents an interface of a service for request-related actions.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public interface IRequestService {

	/**
	 * Verifies the input parameter and passes it to the DAO layer, receives the
	 * request entity, returns it back to the Controller layer or throws an
	 * exception
	 * 
	 * @param userId
	 *            user id
	 * @return found request entity
	 * @throws ServiceException
	 */
	Request getRequestByUser(int userId) throws ServiceException;

	/**
	 * Verifies the input parameter and passes it to the DAO layer, receives the
	 * request entity, returns it back to the Controller layer or throws an
	 * exception
	 * 
	 * @param id
	 *            request id
	 * @return found request entity
	 * @throws ServiceException
	 */
	Request getRequestById(int id) throws ServiceException;

	/**
	 * Verifies the input parameter and passes it to the DAO layer, receives the
	 * request entity, returns boolean variable
	 * 
	 * @param userId
	 *            user id
	 * @return boolean variable that indicates that the user has a request
	 * @throws ServiceException
	 */
	boolean isUserGetRequest(int userId) throws ServiceException;

	/**
	 * Verifies parameters and either passes to the DAO layer or throws an
	 * exception
	 * 
	 * @param requestId
	 *            request id
	 * @throws ServiceException
	 */
	void deleteRequestById(int requestId) throws ServiceException;

	/**
	 * Constructs a new request entity based on input parameters received from
	 * the Controller layer, verifies them and either passes to the DAO layer or
	 * throws an exception
	 * 
	 * @param request
	 *            object
	 * @throws ServiceException
	 */
	void sendRequest(Request request) throws ServiceException;

	/**
	 * Receives a list of all requests from the DAO layer and passes it back to
	 * the Controller
	 * 
	 * @return list of all requests
	 * @throws ServiceException
	 */
	List<Request> getAllRequests() throws ServiceException;

	/**
	 * Counts the number of pages needed to locate all requests within the
	 * pagination
	 * 
	 * @return number of pages
	 * @throws ServiceException
	 */
	int getNumberOfRequestsPages() throws ServiceException;

	/**
	 * Receives a particular list of all requests from the DAO layer depending
	 * on the current page and passes it back to the Controller layer or throws
	 * an exception
	 * 
	 * @param pageNumber
	 * 
	 * @return list of requests
	 * @throws ServiceException
	 */
	List<Request> getRequestsOnCurrentPage(int pageNumber) throws ServiceException;
}
