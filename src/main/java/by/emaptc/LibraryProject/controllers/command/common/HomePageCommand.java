package by.emaptc.LibraryProject.controllers.command.common;

import by.emaptc.LibraryProject.controllers.command.Command;
import by.emaptc.LibraryProject.controllers.command.Page;

import javax.servlet.http.HttpServletRequest;

public class HomePageCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request)  {
        return new Page(Page.MAIN_PAGE_PATH, false);
    }
}
