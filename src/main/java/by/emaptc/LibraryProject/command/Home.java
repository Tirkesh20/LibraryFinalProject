package by.emaptc.LibraryProject.command;

import by.emaptc.LibraryProject.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class Home implements Command {
    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        Page page = new Page();
        homeCommand();
        page.setRedirect(true);
        page.setUrl("main.jsp");
        return page;
    }

    public void homeCommand() {
        System.out.println("Default");
    }
}
