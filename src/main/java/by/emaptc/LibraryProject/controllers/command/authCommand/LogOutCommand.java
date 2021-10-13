package by.emaptc.LibraryProject.controllers.command.authCommand;

import by.emaptc.LibraryProject.controllers.command.Command;
import by.emaptc.LibraryProject.controllers.command.Page;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.entity.transfer.UserLogin;
import by.emaptc.LibraryProject.exceptions.ServiceException;
import by.emaptc.LibraryProject.service.implementation.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.emaptc.LibraryProject.controllers.command.Page.LOGIN_PAGE_PATH;

public class LogOutCommand implements Command {

    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        UserLogin user = (UserLogin) session.getAttribute(USER_ATTRIBUTE);
        userService.logout(user);
        session.invalidate();
        return new Page(LOGIN_PAGE_PATH, true);
    }
}
