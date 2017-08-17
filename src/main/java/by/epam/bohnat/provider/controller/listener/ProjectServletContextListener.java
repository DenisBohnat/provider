package by.epam.bohnat.provider.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bohnat.provider.dao.pool.ConnectionPool;
import by.epam.bohnat.provider.dao.pool.exception.ConnectionPoolException;
import by.epam.bohnat.provider.dao.util.ExceptionMessages;

/**
 * {@code ProjectServletContextListener} is a {@code ServletContextListener}
 * interface implementation and is a listener for initializing and destroying
 * the servlet context.
 * <p>
 * The purpose of the {@code ProjectServletContextListener} class is is to
 * control the initialization and destruction of the connection pool.
 * <p>
 * When the servlet context is initialized, the connection pool with the
 * database is also initialized. If there was an error during the initialization
 * process, the listener sets the connection error flag.
 * <p>
 * When the servlet context is destroyed, the connection pool is also destroyed.
 * 
 * @author Denis Bohnat
 * @version 1.0
 * @see ConnectionPool
 */
public class ProjectServletContextListener implements ServletContextListener {

	private final static Logger logger = LogManager.getLogger(ProjectServletContextListener.class.getName());

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			pool.destroyConnectionPool();
			logger.debug("Connection Pool has been destroyed");
		} catch (ConnectionPoolException e) {
			logger.error(ExceptionMessages.POOL_NOT_DESTROY, e);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			pool.initConnectionPool();
			sce.getServletContext().setAttribute("databaseError", false);
			logger.debug("Connection Pool has been initialized");
		} catch (ConnectionPoolException e) {
			logger.error(ExceptionMessages.POOL_NOT_INIT, e);
			sce.getServletContext().setAttribute("databaseError", true);
		}
	}

}
