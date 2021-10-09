package by.emaptc.LibraryProject.command.adminCommand;

import by.emaptc.LibraryProject.command.Command;
import by.emaptc.LibraryProject.command.Page;
import by.emaptc.LibraryProject.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        return null;
    }
}
