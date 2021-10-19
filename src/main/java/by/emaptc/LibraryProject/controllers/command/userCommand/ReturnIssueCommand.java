package by.emaptc.LibraryProject.controllers.command.userCommand;

import by.emaptc.LibraryProject.controllers.command.Command;
import by.emaptc.LibraryProject.controllers.command.Page;
import by.emaptc.LibraryProject.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class ReturnIssueCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        return null;
    }
}
