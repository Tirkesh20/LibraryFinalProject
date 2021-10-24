package by.emaptc.LibraryProject.controllers.command.userCommand;

import by.emaptc.LibraryProject.controllers.command.Command;
import by.emaptc.LibraryProject.controllers.command.Page;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.exceptions.ServiceException;
import by.emaptc.LibraryProject.service.implementation.LiteratureServiceImpl;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowLiteraturesCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request) throws ServiceException {
        LiteratureServiceImpl literatureService= ServiceProvider.getInstance().getLiteratureService();
        int page=1;
        int recordsPerPage=5;
        if (request.getParameter("page")!=null)
            page=Integer.parseInt(request.getParameter("page"));
        List<Literature> literatureList=literatureService.readAll((page-1)*recordsPerPage,recordsPerPage);
        int noOfRecord=literatureService.noOfRecord();
        int noOfPages=(int) Math.ceil(noOfRecord*1.0/recordsPerPage);
        request.setAttribute("literatures",literatureList);
        request.setAttribute("noOfPages",noOfPages);
        request.setAttribute("currentPage",page);
        return new Page(Page.LITERATURE_LIST,false);
    }
}
