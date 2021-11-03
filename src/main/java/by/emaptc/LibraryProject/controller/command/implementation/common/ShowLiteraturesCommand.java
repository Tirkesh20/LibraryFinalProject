package by.emaptc.LibraryProject.controller.command.implementation.common;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureService;
import by.emaptc.LibraryProject.service.implementation.LiteratureServiceImpl;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 *
 * {@code Command} realization for displaying literature list.
 *
 */
public class ShowLiteraturesCommand implements Command {

    private static final Logger LOGGER= LogManager.getLogger(ShowLiteraturesCommand.class.getName());

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LiteratureService literatureService= ServiceProvider.getInstance().getLiteratureService();
        int page=1;
        int recordsPerPage=5;
        if (request.getParameter("page")!=null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        try {
            List<Literature> literatureList=literatureService.readAll((page-1)*recordsPerPage,recordsPerPage);
            int noOfRecord=literatureService.noOfRecord();
            int noOfPages=(int) Math.ceil(noOfRecord*1.0/recordsPerPage);
            request.setAttribute("literatures",literatureList);
            request.setAttribute("noOfPages",noOfPages);
            request.setAttribute("currentPage",page);
            return new Page(Page.LITERATURE_LIST,false);
        }catch (ServiceException e) {
            LOGGER.error(e.getMessage());
        }
        return new Page("/error",true,"500");
    }
}
