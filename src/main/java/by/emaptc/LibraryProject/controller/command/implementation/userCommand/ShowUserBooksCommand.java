package by.emaptc.LibraryProject.controller.command.implementation.userCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * {@code Command} realization for displaying list of user literatures.
 *
 */
public class ShowUserBooksCommand implements Command {

    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        LiteratureService literatureServiceImpl = ServiceProvider.getInstance().getLiteratureService();
        User user = (User) session.getAttribute("user");
        List<Literature> literatureList = literatureServiceImpl.returnUserLiteratures(user.getId());
        if (literatureList.size() > 0) {
            request.setAttribute(USER_LITERATURES, literatureList);
        }
            return new Page(Page.USER_LITERATURE_LIST,false);
    }
}
