package by.emaptc.LibraryProject.service;

import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.ServiceException;

public interface UserService {

 int registerUser(User user)throws ServiceException;

 User login(String username,String password)throws ServiceException;

 void deleteUser(int id) throws ServiceException;
}
