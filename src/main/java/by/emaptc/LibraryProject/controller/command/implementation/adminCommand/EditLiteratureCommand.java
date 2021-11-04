package by.emaptc.LibraryProject.controller.command.implementation.adminCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditLiteratureCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LiteratureService literatureService= ServiceProvider.getInstance().getLiteratureService();
        int literatureId=Integer.parseInt(request.getParameter("literature_id"));
        Literature literature=literatureService.read(literatureId);
        request.setAttribute("literature",literature);
        return new Page()

    }
}
