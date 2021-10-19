package by.emaptc.LibraryProject.service;

import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exceptions.ServiceException;

public interface UserService {

 int registerUser(User user)throws ServiceException;

 User login(String username,String password)throws ServiceException;

}
