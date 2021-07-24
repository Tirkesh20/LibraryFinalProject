package by.emaptc.LibraryProject.command;

import by.emaptc.LibraryProject.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class Factory implements Command{

    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        return null;
    }

    public Command getCommand(String command){
        switch (command){
            case "book":return null;
            default:return new Home();
        }
    }
}
