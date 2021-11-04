package by.emaptc.LibraryProject.controller.command.implementation.userCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IssueCommand implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LiteratureService literatureService= ServiceProvider.getInstance().getLiteratureService();
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute(USER_ATTRIBUTE);
        if (user==null)
            return new Page(Page.LOGIN_PAGE_PATH,true);

        int literature_id= Integer.parseInt(request.getParameter("literature_id"));
        Literature literature=literatureService.read(literature_id);
        session.setAttribute("literature",literature);
        return new Page(Page.ISSUE_URL,false);
    }
}
