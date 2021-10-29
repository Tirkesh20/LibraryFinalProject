package by.emaptc.LibraryProject.service.implementation;

import by.emaptc.LibraryProject.dao.implementation.DaoProvider;
import by.emaptc.LibraryProject.dao.implementation.UserDAOImpl;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.DAOException;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDAOImpl userDAO = DaoProvider.getInstance().getUserDAO();


    public User login(String login, String password) throws ServiceException {
        try {
            userDAO.startTransaction();
            User user = userDAO.login(login, password);
            if (user == null) {
                return null;
            }
            user.setStatus("active");
            return user;
        } catch (DAOException e) {
            userDAO.rollbackTransaction();
            throw new ServiceException("Exception during user login operation login = [" + login + "]", e);
        } finally {
            userDAO.close();
        }
    }

    public void logout(User user) throws ServiceException {
        try {
            userDAO.startTransaction();
            user.setStatus("not active");
            userDAO.updateStatus(user.getId(),user.getStatus());
        } catch (DAOException e) {
            userDAO.rollbackTransaction();
            throw new ServiceException("Exception during logout operation login =[" + user + "]", e);
        } finally {
            userDAO.close();
        }
    }

    public boolean isLoginAvailable(String login) throws ServiceException {
        try {
            return userDAO.containsLogin(login);
        } catch (DAOException e) {
            throw new ServiceException("Exception during check user login for unique operation login =[" + login + "]", e);
        } finally {
            userDAO.close();
        }
    }

    public boolean isEmailAvailable(String email) throws ServiceException {
        try {
            return userDAO.containsEmail(email);
        } catch (DAOException e) {
            throw new ServiceException("Exception during check user email for unique operation email =[" + email + "]", e);
        } finally {
            userDAO.close();
        }
    }


    public int registerUser(User user) throws ServiceException {
        try {
            userDAO.startTransaction();
            return   userDAO.insertUser(user);
        } catch (DAOException e) {
            throw new ServiceException("Exception during user register operation user = [" + user.toString() + "]", e);
        } finally {
            userDAO.close();
        }
    }

    public void deleteUser(int id) throws ServiceException {
        try {
            userDAO.deleteUserById(id);
        } catch (DAOException e) {
            throw new ServiceException("Exception during taxi delete according his id operation id =[" + id + "]", e);
        } finally {
            userDAO.close();
        }
    }
}
