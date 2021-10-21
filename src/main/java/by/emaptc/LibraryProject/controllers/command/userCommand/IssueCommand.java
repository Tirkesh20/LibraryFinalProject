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
        if (literatureManagementService.userHasBook(userId,bookId)){
            return new Page();
        }
        if (literatureManagementService.userHasLimit(userId)>MAX_BOOK_COUNT){
            return new Page();
        }
        LiteratureManagement literatureManagement=new LiteratureManagement();
        literatureManagement.setUser_id(userId);
        literatureManagement.setLiterature_id(bookId);
        literatureManagement.setDateOfGive(currentDate());
        long date=Date.parse(request.getParameter("date"));
        literatureManagement.setDateToReturn(new Timestamp(date));
        literatureManagementService.issueABook(literatureManagement);
        return null;
    }

    private Timestamp currentDate(){
        Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();
        return new Timestamp(date.getTime());

    }
}
