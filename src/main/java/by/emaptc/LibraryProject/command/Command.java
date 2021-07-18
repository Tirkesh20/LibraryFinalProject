package by.emaptc.LibraryProject.command;

import by.emaptc.LibraryProject.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Page execute(HttpServletRequest request)throws ServiceException;
}
