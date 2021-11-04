package by.emaptc.LibraryProject.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.emaptc.LibraryProject.dao.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * Initializes and destroys connection pool
 *
 */

public class ContextListener implements ServletContextListener {
    private static final Logger LOG = LogManager.getLogger(ContextListener.class.getName());


    /**
     * Destroys connection pool
     */

    public void contextDestroyed(ServletContextEvent sce) {
        LOG.warn("Pool closed");
            ConnectionPool.retrieveConnectionPool().closePool();
    }

    /**
     * Initializes connection pool
     */
    public void contextInitialized(ServletContextEvent sce) {
        LOG.warn("Pool initialized");
        ConnectionPool.retrieveConnectionPool();
    }

}