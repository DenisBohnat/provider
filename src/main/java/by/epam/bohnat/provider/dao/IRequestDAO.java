package by.epam.bohnat.provider.dao;

import java.util.List;

import by.epam.bohnat.provider.bean.Request;
import by.epam.bohnat.provider.dao.exception.DAOException;

/**
 * Defines methods for implementing in the DAO layer for the user request
 * entity.
 * <p>
 * Methods of reading / writing data throw {@link DAOException}, indicating a
 * read / write error.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public interface IRequestDAO {

	/**
	 * This method is used to search request in the data source by user ID
	 * 
	 * @param userId
	 *            user id
	 * @return found user request or null if it was not found
	 * @throws DAOException
	 */
	Request getRequestByUser(int userId) throws DAOException;

	/**
	 * This method is used to search request in the data source by ID
	 * 
	 * @param id
	 *            request id
	 * @return found user request or null if it was not found
	 * @throws DAOException
	 */
	Request getRequestById(int id) throws DAOException;

	/**
	 * This method is used to return all requests from the data source
	 * 
	 * @return list of all requests
	 * @throws DAOException
	 */
	List<Request> getAllRequests() throws DAOException;

	/**
	 * This method is used to delete user request from the data source by ID
	 * 
	 * @param requestId
	 *            request id
	 * @throws DAOException
	 */
	void deleteRequestById(int requestId) throws DAOException;

	/**
	 * This method is used to add new user request to the data source
	 * 
	 * @param request
	 *            object
	 * @return boolean variable that indicates that adding new request entity to
	 *         the data source was successful
	 * @throws DAOException
	 */
	boolean sendRequest(Request request) throws DAOException;

	/**
	 * This method is used to get counts of all requests in the data source
	 * 
	 * @return total requests amount
	 * @throws DAOException
	 */
	int getNumberOfRequests() throws DAOException;

	/**
	 * This method is used to return necessary part of all requests from the
	 * data source
	 * 
	 * @param start
	 *            start index of requests part
	 * @param amount
	 *            amount of requests to be returned
	 * @return list of requests
	 * @throws DAOException
	 */
	List<Request> getRequestPart(int start, int amount) throws DAOException;
}
