package by.emaptc.LibraryProject.command.authCommand;

import by.emaptc.LibraryProject.command.Command;
import by.emaptc.LibraryProject.command.Page;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.entity.enums.Role;
import by.emaptc.LibraryProject.exceptions.ServiceException;
import by.emaptc.LibraryProject.service.UserServiceImpl;
import by.emaptc.LibraryProject.utils.PasswordEncoder;
import by.emaptc.LibraryProject.utils.UserDataValidator;

import javax.servlet.http.HttpServletRequest;

import java.util.Locale;

import static by.emaptc.LibraryProject.command.Page.LOGIN_PAGE_PATH;
import static by.emaptc.LibraryProject.command.Page.REGISTER_PAGE_PATH;
import static by.emaptc.LibraryProject.utils.MessageManager.SUCCESS_MESSAGE_KEY;
import static by.emaptc.LibraryProject.utils.MessageManager.UNSUCCESSFUL_MESSAGE_KEY;

public class RegisterCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        UserServiceImpl userService = new UserServiceImpl();
        User user = buildUser(request);
        if (user == null) {
            return new Page(REGISTER_PAGE_PATH, false, UNSUCCESSFUL_MESSAGE_KEY);
        }

        boolean loginIsUnique = userService.isLoginAvailable(user.getUsername());
        if (!loginIsUnique) {
            return new Page(REGISTER_PAGE_PATH, false, "user already exist");
        }

        boolean emailIsUnique = userService.isEmailAvailable(user.getEmail());
        if (!emailIsUnique) {
            return new Page(REGISTER_PAGE_PATH, false, "email already registered");
        }

        userService.registerUser(user);
        return new Page(LOGIN_PAGE_PATH, true, SUCCESS_MESSAGE_KEY);

    }

    private User buildUser(HttpServletRequest request) {
        User user = new User();
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        boolean isUserDataValid = isUserDataValid(firstName, lastName, login, password, email);
        if (!isUserDataValid) {
            return null;
        }
        user.setName(firstName);
        user.setLastName(lastName);
        user.setUsername(login);
        user.setPassword(PasswordEncoder.encode(password));
        user.setEmail(email);
        user.setRole(Role.valueOf(role.toUpperCase(Locale.ROOT)));
        user.setStatus("not active");
        return user;
    }

    private boolean isUserDataValid(String firstName, String lastName, String login, String password, String email) {
        UserDataValidator userDataValidator = new UserDataValidator();
        return userDataValidator.checkData(firstName, lastName, login, password, email);
    }
}
