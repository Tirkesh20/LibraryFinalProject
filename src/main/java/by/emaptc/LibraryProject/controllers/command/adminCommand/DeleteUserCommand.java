package by.emaptc.LibraryProject.controllers.command.adminCommand;

import by.emaptc.LibraryProject.controllers.command.Command;
import by.emaptc.LibraryProject.controllers.command.Page;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exceptions.ServiceException;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;
import by.emaptc.LibraryProject.service.implementation.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {


    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        UserServiceImpl userService=ServiceProvider.getInstance().getUserService();
        User user=(User) request.getSession().getAttribute("user");
        String role=user.getRole().toString();
        if (!role.equals("ADMIN")){

        }
        int id= Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(id);
        return new Page();
    }
}
