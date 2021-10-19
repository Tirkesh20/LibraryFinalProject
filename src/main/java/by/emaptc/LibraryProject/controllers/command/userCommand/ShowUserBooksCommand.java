package by.emaptc.LibraryProject.controllers.command.userCommand;

import by.emaptc.LibraryProject.controllers.command.Command;
import by.emaptc.LibraryProject.controllers.command.Page;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exceptions.ServiceException;
import by.emaptc.LibraryProject.service.implementation.LiteratureServiceImpl;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowUserBooksCommand implements Command {


    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        LiteratureServiceImpl literatureServiceImpl = ServiceProvider.getInstance().getLiteratureService();
        User user = (User) session.getAttribute("user");
        List<Literature> literatureList = literatureServiceImpl.returnUserLiteratures(user.getId());
        if (literatureList.size() > 0) {
            request.setAttribute(USER_LITERATURES, literatureList);
        }
            return new Page(MY_LITERATURES_URL,true);

    }
}
