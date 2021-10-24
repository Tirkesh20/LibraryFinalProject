package by.emaptc.LibraryProject.controllers.command.adminCommand;

import by.emaptc.LibraryProject.controllers.command.Command;
import by.emaptc.LibraryProject.controllers.command.Page;
import by.emaptc.LibraryProject.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class AddLiteratureCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        return new Page();
    }
}
