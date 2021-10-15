package by.emaptc.LibraryProject.service;

import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exceptions.ServiceException;

public interface UserService {

public void registerUser(User user)throws ServiceException;

public User login(String username,String password)throws ServiceException;

}
