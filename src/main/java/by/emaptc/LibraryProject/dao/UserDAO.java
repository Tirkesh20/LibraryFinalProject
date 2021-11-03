package by.emaptc.LibraryProject.dao;

import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.DAOException;

public interface UserDAO {

    boolean containsEmail(String email) throws DAOException;

    int register(User user) throws DAOException;

    boolean containsLogin(String login) throws DAOException;

    void updateStatus(int userID, String status) throws DAOException;

    User login(String login, String password) throws DAOException;

}
