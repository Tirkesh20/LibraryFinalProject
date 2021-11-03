package by.emaptc.LibraryProject.controller.command.implementation.adminCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.entity.enumEntity.Genre;
import by.emaptc.LibraryProject.entity.enumEntity.LiteratureType;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * {@code Command} realization for performing add literature entity action.
 *
 */

public class AddLiteratureCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LiteratureService literatureService= ServiceProvider.getInstance().getLiteratureService();
        String literatureName=request.getParameter("literature_name");
        String author=request.getParameter("author");
        String genre=request.getParameter("genre");
        String type=request.getParameter("type");
        int pages=Integer.parseInt(request.getParameter("pages"));
        String publisher=request.getParameter("publisher");
        Literature literature=new Literature();
        literature.setPublisher(publisher);
        literature.setLiteratureType(LiteratureType.valueOf(type));
        literature.setGenre(Genre.valueOf(genre));
        literature.setAuthor(author);
        literature.setBookPages(pages);
        literature.setLiteratureName(literatureName);
        literatureService.add(literature);
        return new Page(Page.MAIN_PAGE_PATH,true);
    }
}
