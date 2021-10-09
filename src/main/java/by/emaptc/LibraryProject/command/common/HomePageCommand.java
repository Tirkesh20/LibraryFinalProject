package by.emaptc.LibraryProject.command.common;

import by.emaptc.LibraryProject.command.Command;
import by.emaptc.LibraryProject.command.Page;
import by.emaptc.LibraryProject.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class HomePageCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request)  {
        return new Page(Page.MAIN_PAGE_PATH, false);
    }
}
