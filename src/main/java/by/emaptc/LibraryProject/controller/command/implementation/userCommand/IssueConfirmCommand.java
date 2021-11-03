package by.emaptc.LibraryProject.controller.command.implementation.userCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureManagementService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * {@code Command} realization for performing issue literature action.
 *
 */
public class IssueConfirmCommand implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LiteratureManagementService literatureManagementService= ServiceProvider.getInstance().getLiteratureManagementService();
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute(USER_ATTRIBUTE);
        if (user==null)
            return new Page(Page.LOGIN_PAGE_PATH,false);

        int userId=user.getId();
        int bookId= Integer.parseInt(request.getParameter("literature_id"));
        int returnDay=Integer.parseInt(request.getParameter("return_date"));

        if (!literatureManagementService.userHasBook(userId,bookId)
                &&(!literatureManagementService.userHasLimit(userId))){
            literatureManagementService.issueABook(userId,bookId,returnDay);
            return new Page("/controller?command=USER_LITERATURE",false);
        }

        return new Page( "/controller?command=COMMON_LITERATURES",true);
    }

}
