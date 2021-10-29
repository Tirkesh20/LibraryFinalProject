package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.AbstractDAO;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.entity.enumEntity.Role;
import by.emaptc.LibraryProject.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class UserDAOImpl extends AbstractDAO<User> {


    private static final String LOGIN_QUERY="SELECT users.u_id,users.name,users.lastname,"+"users.email,users.username,users.password,users.status," +
            "roles.usertype from users"+
            " inner join roles on users.role_id = roles.r_id where" +
            "(username=? AND password=?)";
    private  static final String SQL_INSERT="INSERT INTO users (name, lastname ,username, password, email, status) VALUES(?,?,?,?,?,?)";


    public User login(String login, String password) throws DAOException {
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
        }
    }


    public void updateStatus(int userID, String status) throws DAOException {
        String sqlQuery = "UPDATE users SET status=? WHERE u_id=?";
        String id=String.valueOf(userID);
        List<String> params = Arrays.asList(status, id);
        executeQuery(sqlQuery, params);
    }

    public boolean containsLogin(String login) throws DAOException {
        String sqlQuery = "SELECT * FROM users WHERE username=?";
        List<String> params = Collections.singletonList(login);
        return getEntity(sqlQuery, params) == null;
    }


    public boolean containsEmail(String email) throws DAOException {
        String sqlQuery = "SELECT * FROM users WHERE email=?";
        List<String> params = Collections.singletonList(email);
        return getEntity(sqlQuery, params) == null;
    }


    public int insertUser(User user) throws DAOException {
        return insertExecuteQuery(SQL_INSERT, user);
    }


    public void deleteUserById(int id) throws DAOException {
        String sqlQuery = "update  users  set status=? where u_id = ?";
        List<String> params = Collections.singletonList(String.valueOf(id));
        executeQuery(sqlQuery, params);
    }

    @Override
    protected List<String> getEntityParameters(User entity) {
        String firstName = entity.getName();
        String lastName = entity.getLastName();
        String username = entity.getUsername();
        String password = entity.getPassword();
        String email = entity.getEmail();
        String status = entity.getStatus();
        return Arrays.asList(firstName, lastName, username, password, email, status);
    }

    @Override
    public User buildEntity(ResultSet resultSet) throws  DAOException {
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

    @Override
    protected void fetchSet(PreparedStatement stmt, User entity) throws SQLException {
        stmt.setString(1,entity.getName());
        stmt.setString(2,entity.getLastName());
        stmt.setString(3,entity.getEmail());
        stmt.setString(4,entity.getUsername());
        stmt.setString(5,entity.getPassword());
        stmt.setString(6,entity.getStatus());
    }
}
