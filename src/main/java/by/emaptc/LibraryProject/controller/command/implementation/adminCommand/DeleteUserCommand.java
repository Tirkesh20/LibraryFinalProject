package by.emaptc.LibraryProject.controller.command.implementation.adminCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.UserService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;
import by.emaptc.LibraryProject.service.implementation.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {

    /**
     *
     * {@code Command} realization for performing baning of user action.
     *
     */
    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        UserService userService=ServiceProvider.getInstance().getUserService();
        User user=(User) request.getSession().getAttribute("user");
        String role=user.getRole().toString();
        if (!role.equals("ADMIN")){

        }
        int id= Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(id);
        return new Page();
    }
}
