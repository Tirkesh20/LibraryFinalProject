package by.emaptc.LibraryProject.pool;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
    private final Connection connection;
    private final ConnectionPool connectionPool;

    public ConnectionManager() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    public void rollbackTransaction() {
        try {
            connection.rollback();
        } catch (SQLException exception) {
            LOGGER.error("Transaction rollback failed. ", exception);
        }
    }

    public Connection getConnection() {
        return connection;
    }


    public void close() {
        connectionPool.returnConnection(connection);
    }
}
