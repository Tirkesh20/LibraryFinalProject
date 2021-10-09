package by.emaptc.LibraryProject.command.authCommand;

import by.emaptc.LibraryProject.command.Command;
import by.emaptc.LibraryProject.command.Page;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exceptions.ServiceException;
import by.emaptc.LibraryProject.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.emaptc.LibraryProject.command.Page.MAIN_PAGE_PATH;

public class LogOutCommand implements Command {

    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        userService.logout(user);
        session.invalidate();
        return new Page(MAIN_PAGE_PATH, true);
    }
}
