package by.emaptc.LibraryProject.controller.command.implementation.authCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.implementation.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.emaptc.LibraryProject.controller.command.Page.LOGIN_PAGE_PATH;

public class LogOutCommand implements Command {
    /**
     *
     * {@code Command} realization of log out  action.
     *
     */
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        userService.logout(user);
        session.invalidate();
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        return new Page(LOGIN_PAGE_PATH, true);
    }
}
