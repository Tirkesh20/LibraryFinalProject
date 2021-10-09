package by.emaptc.LibraryProject.command.authCommand;

import by.emaptc.LibraryProject.command.Command;
import by.emaptc.LibraryProject.command.Page;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exceptions.ServiceException;
import by.emaptc.LibraryProject.service.UserServiceImpl;
import by.emaptc.LibraryProject.utils.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.emaptc.LibraryProject.utils.MessageManager.LOGIN_ERROR_MESSAGE_KEY;

public class LoginCommand implements Command {

    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession currentSession = request.getSession();
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = PasswordEncoder.encode(request.getParameter(PASSWORD_PARAMETER));
        User user = userService.login(login, password);
        if (user == null) {
            return new Page(Page.LOGIN_PAGE_PATH, false, LOGIN_ERROR_MESSAGE_KEY);
        }
        currentSession.setAttribute(USER_ATTRIBUTE, user);
        return new Page(Page.MAIN_PAGE_PATH, true);

    }
}
