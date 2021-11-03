package by.emaptc.LibraryProject.controller.command.implementation.userCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureManagementService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * {@code Command} realization for performing returning issue action.
 *
 */
public class ReturnIssueCommand implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LiteratureManagementService literatureManagementService= ServiceProvider.getInstance().getLiteratureManagementService();
        User user=(User) request.getSession().getAttribute("user");
        int literatureId=Integer.parseInt(request.getParameter("literature_id"));
        int userId=user.getId();
        literatureManagementService.returnIssue(userId,literatureId);
        return new Page("/controller?command=User_literatures",true);
    }
}
