package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.AbstractDAO;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.entity.enums.Role;
import by.emaptc.LibraryProject.exceptions.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User> {
final String LOGIN_QUERY="SELECT id ,roles.name from users"+
        " inner join roles on users.role_id = roles.id where" +
        "(username=? AND password=?) or (email=? and password=?";

    public User login(String login, String password) throws DAOException {
        List<String> params = Arrays.asList(login, password);
        return getEntity(LOGIN_QUERY, params);
    }


    public void updateStatus(User user) throws DAOException {
        String sqlQuery = "UPDATE user SET status=? WHERE id=?";
        String status = user.getStatus();
        String id = String.valueOf(user.getId());
        List<String> params = Arrays.asList(status, id);
        executeQuery(sqlQuery, params);
    }

    public boolean containsLogin(String login) throws DAOException {
        String sqlQuery = "SELECT * FROM users WHERE login=?";
        List<String> params = Collections.singletonList(login);
        return getEntity(sqlQuery, params) == null;
    }

    public boolean containsEmail(String email) throws DAOException {
        String sqlQuery = "SELECT * FROM user WHERE email=?";
        List<String> params = Collections.singletonList(email);
        return getEntity(sqlQuery, params) == null;
    }


    public void insertUser(User user) throws DAOException {
        String fields = "insert into user (name, lastname ,username, password, email,role, status) values(?,?,?,?,?,?,?)";
        insert(user, fields);
    }


    public void deleteUserById(int id) throws DAOException {
        String sqlQuery = "delete from user  where id = ?";
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
        String role = entity.getRole().toString();
        String status = entity.getStatus();
        return Arrays.asList(firstName, lastName, username, password, email,role, status);
    }

    @Override
    protected User buildEntity(ResultSet result) throws DAOException {
        try {
            User user = new User();
            int id = result.getInt(ID_COLUMN_LABEL);
            user.setId(id);
            user.setName(result.getString("name"));
            user.setLastName(result.getString("lastname"));
            user.setUsername(result.getString("username"));
            user.setPassword(result.getString("password"));
            user.setEmail(result.getString("email"));
            user.setStatus(result.getString("status"));
            return user;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
}
