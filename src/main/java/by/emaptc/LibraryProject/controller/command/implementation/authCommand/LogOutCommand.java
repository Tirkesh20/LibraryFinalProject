package by.emaptc.LibraryProject.controller.command.implementation.authCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.implementation.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.emaptc.LibraryProject.controller.command.Page.LOGIN_PAGE_PATH;

public class LogOutCommand implements Command {
    /**
     *
     * {@code Command} realization of log out  action.
     *
     */
    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        userService.logout(user);
        session.invalidate();
        return new Page(LOGIN_PAGE_PATH, true);
    }
}
