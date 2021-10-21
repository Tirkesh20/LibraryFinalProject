package by.emaptc.LibraryProject.dao;

import by.emaptc.LibraryProject.entity.LiteratureManagement;
import by.emaptc.LibraryProject.exceptions.DAOException;
import by.emaptc.LibraryProject.dao.pool.ConnectionManager;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AbstractDAO<T> {
    protected static final String ID_COLUMN_LABEL = "id";
    private static final ThreadLocal<ConnectionManager> threadLocal = new ThreadLocal<>();
    protected Connection connection=null;
    protected ResultSet resultSet=null;
    protected PreparedStatement preparedStatement=null;



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

    protected List<String> getEntityParameters(T entity) {
        return null;
    }

    protected T buildEntity(ResultSet result) throws DAOException {
        return null;
    }


    protected void executeQuery(String sqlQuery, List<String> parameters) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            buildStatement(parameters,preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }finally {
            closeConnection(connection,preparedStatement,resultSet);
        }
    }

    protected int insertExecuteQuery(String sqlQuery, T entity) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            fetchSet(preparedStatement,entity);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    throw new DAOException("no generated keys found");
                }
            }
        }catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }finally {
            closeConnection(connection,preparedStatement,resultSet);
        }
    }

    protected void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws DAOException {
        try {
            if (resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        closeConnection(connection,preparedStatement);
    }
    protected void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws DAOException {
        try {
            if (resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        closeConnection(connection,statement);
    }

    protected void closeConnection(Connection connection, PreparedStatement preparedStatement) throws DAOException {
        try {
            if (preparedStatement!=null){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        closeConnection(connection);
    }
    protected void closeConnection(Connection connection, Statement statement) throws DAOException {
        try {
            if (statement!=null){
                statement.close();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        closeConnection(connection);
    }

    protected void closeConnection(Connection connection) throws DAOException {
        try {if (connection!=null){
            connection.close();
        }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    protected T getEntity (String sqlQuery, List < String > params) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            buildStatement(params,preparedStatement);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? buildEntity(resultSet) : null;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }finally {
            closeConnection(connection,preparedStatement,resultSet);
        }
    }

    protected List<T> getEntities (String sqlQuery, List < String > params) throws DAOException {
        List <T> list=new LinkedList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            buildStatement(params,preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(buildEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }finally {
            closeConnection(connection,preparedStatement,resultSet);
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


    protected void fetchSet(PreparedStatement stmt, T entity) throws SQLException {}

    public void buildStatement ( List < String > parameters, PreparedStatement preparedStatement) throws DAOException {
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
