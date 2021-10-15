package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.AbstractDAO;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.entity.enums.Role;
import by.emaptc.LibraryProject.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User> {
    private Connection connection=null;
   private PreparedStatement preparedStatement = null;
   private ResultSet resultSet = null;

    private String LOGIN_QUERY="SELECT users.id,users.name,users.lastname," +
                                            "users.email,users.username,users.password,users.status," +
                                                "roles.usertype from users"+
                                                  " inner join roles on users.role_id = roles.id where" +
                                                      "(username=? AND password=?)";


    public User login(String login, String password) throws DAOException {
        List<String> params = Arrays.asList(login, password);
            try {
                connection = getConnection();
                preparedStatement = connection.prepareStatement(LOGIN_QUERY);
                buildStatement(params, preparedStatement);
                resultSet = preparedStatement.executeQuery();
                buildEntity(resultSet);
            } catch (SQLException e) {
                throw new DAOException(e.getMessage(), e);
            }finally {
            closeConnection(connection,preparedStatement,resultSet);
        }
        return null;
    }


    public void updateStatus(int userID, String status) throws DAOException {
        String sqlQuery = "UPDATE users SET status=? WHERE id=?";
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
        String fields = "insert into users (name, lastname ,username, password, email, status) values(?,?,?,?,?,?)";
       return insert(user, fields);
    }


    public void deleteUserById(int id) throws DAOException {
        String sqlQuery = "delete from users  where id = ?";
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
    public User buildEntity(ResultSet resultSet)throws SQLException{
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("lastname"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setRole(Role.valueOf(resultSet.getString("usertype")));
        return user;
    }

}
