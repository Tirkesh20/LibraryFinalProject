package by.emaptc.LibraryProject.controllers.command.userCommand;

import by.emaptc.LibraryProject.controllers.command.Command;
import by.emaptc.LibraryProject.controllers.command.Page;
import by.emaptc.LibraryProject.entity.LiteratureManagement;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exceptions.ServiceException;
import by.emaptc.LibraryProject.service.implementation.LiteratureManagementService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;
import by.emaptc.LibraryProject.service.implementation.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class IssueCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        LiteratureManagementService literatureManagementService= ServiceProvider.getInstance().getLiteratureManagementService();
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute(USER_ATTRIBUTE);
        int userId=user.getId();
        int bookId= Integer.parseInt(request.getParameter("book_id"));
        int returnDay=Integer.parseInt(request.getParameter("return_date"));
        if (literatureManagementService.userHasBook(userId,bookId)){
            return new Page();
        }
        if (literatureManagementService.userHasLimit(userId)>MAX_BOOK_COUNT){
            return new Page();
        }
        literatureManagementService.issueABook(userId,bookId,returnDay);
        return null;
    }

}
