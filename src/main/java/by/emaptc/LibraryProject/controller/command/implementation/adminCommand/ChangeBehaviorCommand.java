package by.emaptc.LibraryProject.controller.command.implementation.adminCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeBehaviorCommand implements Command {

    /**
     *
     * {@code Command} realization for changing behavior rating of user action.
     *
     */
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return null;
    }
}
