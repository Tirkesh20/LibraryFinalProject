package by.emaptc.LibraryProject.controller.command.implementation.userCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.enumEntity.Status;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureManagementService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
/**
 *
 * {@code Command} realization for performing returning issue action.
 *
 */
public class ReturnIssueCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        LiteratureManagementService literatureManagementService= ServiceProvider.getInstance().getLiteratureManagementService();
        int issue_id=Integer.parseInt(request.getParameter("issue_id"));
        literatureManagementService.changeIssueStatus(issue_id, Status.CLOSED);
        return new Page(Page.MAIN_PAGE_PATH,true);
    }
}
