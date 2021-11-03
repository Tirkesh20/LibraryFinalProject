package by.emaptc.LibraryProject.controller.command.implementation.authCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.UserService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;
import by.emaptc.LibraryProject.service.implementation.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.emaptc.LibraryProject.util.MessageManager.LOGIN_ERROR_MESSAGE_KEY;

public class LoginCommand implements Command {

    /**
     *
     * {@code Command} realization for authentication action.
     *
     */
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserService userService= ServiceProvider.getInstance().getUserService();
        HttpSession currentSession = request.getSession();
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = (request.getParameter(PASSWORD_PARAMETER));
        User user = userService.login(login, password);
        if (user == null) {
            return new Page(Page.LOGIN_PAGE_PATH, false, LOGIN_ERROR_MESSAGE_KEY);
        }
        currentSession.setAttribute(USER_ATTRIBUTE, user);
        return new Page(Page.MAIN_PAGE_PATH, false);

    }
}
