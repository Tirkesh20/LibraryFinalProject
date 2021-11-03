package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.UserDAO;
import by.emaptc.LibraryProject.dao.pool.ConnectionManager;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.entity.enumEntity.Role;
import by.emaptc.LibraryProject.exception.DAOException;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class UserDAOImpl implements UserDAO {
    private static final ThreadLocal<ConnectionManager> threadLocal = new ThreadLocal<>();
    private static final String LOGIN_QUERY="SELECT users.u_id,users.name,users.lastname,"+"users.email,users.username,users.password,users.status," +
            "roles.usertype from users"+
            " inner join roles on users.role_id = roles.r_id where" +
            "(username=? AND password=?)";
    private  static final String SQL_INSERT="INSERT INTO users (name, lastname ,username, password, email, status) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE_STATUS = "UPDATE users SET status=? WHERE u_id=?";


    /**
     *
     * @param login {@code String} unique name of user which will be checked on logging into system
     * @param password {@code String} password of user encrypted by BCrypt
     * @return user {@code User} entity of login or null
     * @throws DAOException custom exception for catching sql exception
     */
    public User login(String login, String password) throws DAOException {
        startTransaction();
        List<String> params = Arrays.asList(login, password);
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY)){
            buildStatement(params, preparedStatement);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    return buildEntity(resultSet);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }finally {
            close();
        }
    }

    /**
     *
     * @param userID {@code int} unique number of element which will be updated
     * @param status {@code String} parameter on which will be based updating
     * @throws DAOException custom exception for catching sql exception
     */
    public void updateStatus(int userID, String status) throws DAOException {
        startTransaction();
        String id=String.valueOf(userID);
        List<String> params = Arrays.asList(status, id);
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection
                     .prepareStatement(SQL_UPDATE_STATUS)) {
            buildStatement(params,preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException exception) {
            rollbackTransaction();
            throw new DAOException(exception.getMessage(), exception);
        }finally {
            close();
        }
    }

    /**
     *
     * @param user {@code User} entity which will be added
     * @return int key of last inserted element
     * @throws DAOException custom exception for catching sql exception
     */
    public int register(User user) throws DAOException {
        startTransaction();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            fetchSet(preparedStatement,user);
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    throw new DAOException("no generated keys found");
                }
            }
        }catch (SQLException exception) {
            rollbackTransaction();
            throw new DAOException(exception.getMessage(), exception);
        }finally {
            close();
        }
    }

    /**
     *
     * @param login {@code String} unique name of user
     * @return boolean
     * @throws DAOException custom exception for catching sql exception
     */
    public boolean containsLogin(String login) throws DAOException {
        String sqlQuery = "SELECT * FROM users WHERE username=?";
        List<String> params = Collections.singletonList(login);
        return getEntity(sqlQuery, params) == null;
    }

    /**
     *
     * @param email {@code String} password of user encrypted by BCrypt
     * @return boolean
     * @throws DAOException custom exception for catching sql exception
     */
    public boolean containsEmail(String email) throws DAOException {
        String sqlQuery = "SELECT * FROM users WHERE email=?";
        List<String> params = Collections.singletonList(email);
        return getEntity(sqlQuery, params) == null;
    }

    private void fetchSet(PreparedStatement stmt, User entity) throws SQLException {
        stmt.setString(1,entity.getName());
        stmt.setString(2,entity.getLastName());
        stmt.setString(3,entity.getEmail());
        stmt.setString(4,entity.getUsername());
        stmt.setString(5,entity.getPassword());
        stmt.setString(6,entity.getStatus());
    }

    /**
     *Initialize statement from given list of parameters
     *
     * @param parameters {@code List<String>} list of parameters which will use in SQL QUERY execution
     * @param preparedStatement {@code PreparedStatement}
     * @throws DAOException custom exception for catching sql exception
     */
    private void buildStatement ( List < String > parameters, PreparedStatement preparedStatement) throws DAOException {
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



    private User getEntity (String sqlQuery, List < String > params) throws DAOException{
        startTransaction();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            buildStatement(params,preparedStatement);
            try(ResultSet   resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? buildEntity(resultSet) : null;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }finally {
            close();
        }
    }

    /** @param resultSet {@code ResultSet}
     * @return {@code User} creates and returns entity with initialized fields
     * @throws DAOException custom exception for catching sql exception
     * */

    private User buildEntity(ResultSet resultSet) throws  DAOException {
        try {
            User user = new User();
            user.setId(resultSet.getInt("u_id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastname"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(Role.valueOf(resultSet.getString("usertype").toUpperCase(Locale.ROOT)));
            return user;
        }catch (SQLException e){
            throw new DAOException(e.getMessage());

        }}

    private void startTransaction() {
        ConnectionManager connectionManager = new ConnectionManager();
        threadLocal.set(connectionManager);
    }

    private void rollbackTransaction() {
        ConnectionManager connectionManager = threadLocal.get();
        connectionManager.rollbackTransaction();
    }

    private void close() {
        ConnectionManager connectionManager = threadLocal.get();
        connectionManager.close();
        threadLocal.remove();
    }

    private Connection getConnection() {
        ConnectionManager cm = threadLocal.get();
        if (cm != null) {
            return cm.getConnection();
        }
        startTransaction();
        return threadLocal.get().getConnection();
    }
}
