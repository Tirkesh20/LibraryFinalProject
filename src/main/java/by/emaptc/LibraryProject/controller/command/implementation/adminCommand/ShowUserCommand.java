package by.emaptc.LibraryProject.controller.command.implementation.adminCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class ShowUserCommand implements Command {
    /**
     *
     * {@code Command} realization for displaying list of users.
     *
     */
    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        return null;
    }
}
