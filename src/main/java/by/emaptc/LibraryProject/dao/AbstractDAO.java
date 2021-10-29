package by.emaptc.LibraryProject.dao;

import by.emaptc.LibraryProject.exception.DAOException;
import by.emaptc.LibraryProject.dao.pool.ConnectionManager;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @param <T>
 *  Abstract class for performing data manipulation from database
 */
public class AbstractDAO<T> {

    private static final ThreadLocal<ConnectionManager> threadLocal = new ThreadLocal<>();

    public void startTransaction() {
        ConnectionManager connectionManager = new ConnectionManager();
        threadLocal.set(connectionManager);
    }

    public void rollbackTransaction() {
        ConnectionManager connectionManager = threadLocal.get();
        connectionManager.rollbackTransaction();
    }

    public void close() {
        ConnectionManager connectionManager = threadLocal.get();
        connectionManager.close();
        threadLocal.remove();
    }

    /** @param result {@code ResultSet}
     * @return {@code T} creates and returns entity with initialized fields
     * @throws DAOException
     * */
    protected T buildEntity(ResultSet result) throws DAOException {
        return null;
    }

    /**
     *
     * @param sqlQuery {@code String} sql query for given task
     * @param parameters {@code List<String> list of parameters which will use in SQL QUERY execution
     * @throws DAOException
     */
    protected void executeQuery(String sqlQuery, List<String> parameters) throws DAOException {
        try (Connection connection=getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            buildStatement(parameters,preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
    }

    /**
     *
     * @param sqlQuery {@code String} sql query for given task
     * @param entity {@code T} entity which will be manipulated
     * @return {@code int } id of last inserted entity
     * @throws DAOException
     */
    protected int insertExecuteQuery(String sqlQuery, T entity) throws DAOException {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            fetchSet(preparedStatement,entity);
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    throw new DAOException("no generated keys found");
                }
            }
        }catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
    }

    /**
     *
     * @param sqlQuery {@code String} sql query for given task
     * @param params {@code List<String> list of parameters which will use in SQL QUERY execution
     * @return {@code T} returns entity with unique id
     * @throws DAOException
     */
    protected T getEntity (String sqlQuery, List < String > params) throws DAOException {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            buildStatement(params,preparedStatement);
            try(ResultSet   resultSet = preparedStatement.executeQuery();) {
                return resultSet.next() ? buildEntity(resultSet) : null;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    /**
     *
     * @param sqlQuery {@code String} sql query for given task
     * @param params {@code List<String> list of parameters which will be used in SQL QUERY execution
     * @return {@code List<T>} returns List of entities with unique id
     * @throws DAOException
     */
    protected List<T> getEntities (String sqlQuery, List < String > params) throws DAOException {
        List <T> list=new LinkedList<>();
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            buildStatement(params,preparedStatement);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                while(resultSet.next()){
                    list.add(buildEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return list;
    }

    protected Connection getConnection () {
        ConnectionManager cm = threadLocal.get();
        if (cm != null) {
            return cm.getConnection();
        }
        startTransaction();
        return threadLocal.get().getConnection();
    }

    protected List<String> getEntityParameters(T entity) {
        return null;
    }

    protected void fetchSet(PreparedStatement stmt, T entity) throws SQLException {}

    /**
     *Initialize statement from given list of parameters
     *
     * @param parameters {@code List<String>} list of parameters which will use in SQL QUERY execution
     * @param preparedStatement {@code PreparedStatement}
     * @throws DAOException
     */
    protected void buildStatement ( List < String > parameters, PreparedStatement preparedStatement) throws DAOException {
        try {
            if (parameters != null) {
                int parameterIndex = 1;
                for (String parameter : parameters) {
                    preparedStatement.setObject(parameterIndex++, parameter);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
}
