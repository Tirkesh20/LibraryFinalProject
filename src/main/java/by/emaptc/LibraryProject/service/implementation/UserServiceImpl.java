package by.emaptc.LibraryProject.service.implementation;

import by.emaptc.LibraryProject.dao.UserDAO;
import by.emaptc.LibraryProject.dao.implementation.DaoProvider;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.DAOException;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = DaoProvider.getInstance().getUserDAO();


    public User login(String login, String password) throws ServiceException {
        try {
            User user = userDAO.login(login, password);
            if (user == null) {
                return null;
            }
            user.setStatus("active");
            return user;
        } catch (DAOException e) {

            throw new ServiceException("Exception during user login operation login = [" + login + "]", e);
        }
    }

    public void logout(User user) throws ServiceException {
        try {
            user.setStatus("not active");
            userDAO.updateStatus(user.getId(),user.getStatus());
        } catch (DAOException e) {
            throw new ServiceException("Exception during logout operation login =[" + user + "]", e);
        }
    }

    public boolean isLoginAvailable(String login) throws ServiceException {
        try {
            return userDAO.containsLogin(login);
        } catch (DAOException e) {
            throw new ServiceException("Exception during check user login for unique operation login =[" + login + "]", e);
        }
    }

    public boolean isEmailAvailable(String email) throws ServiceException {
        try {
            return userDAO.containsEmail(email);
        } catch (DAOException e) {
            throw new ServiceException("Exception during check user email for unique operation email =[" + email + "]", e);
        }
    }


    public int registerUser(User user) throws ServiceException {
        try {
            return   userDAO.register(user);
        } catch (DAOException e) {
            throw new ServiceException("Exception during user register operation user = [" + user.toString() + "]", e);
        }
    }

    public void deleteUser(int id) throws ServiceException {
        try {
            userDAO.updateStatus(id,"DELETED");
        } catch (DAOException e) {
            throw new ServiceException("Exception during taxi delete according his id operation id =[" + id + "]", e);
        }
    }
}
