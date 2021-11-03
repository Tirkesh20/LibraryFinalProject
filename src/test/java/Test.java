import by.emaptc.LibraryProject.dao.implementation.LiteratureManagementDAOImpl;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.exception.DAOException;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureManagementService;
import by.emaptc.LibraryProject.service.LiteratureService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import java.util.List;

public class Test {

    LiteratureService literatureService= ServiceProvider.getInstance().getLiteratureService();
    LiteratureManagementService literatureManagementService= ServiceProvider.getInstance().getLiteratureManagementService();
    LiteratureManagementDAOImpl literatureManagementDAO=new LiteratureManagementDAOImpl();
    @org.junit.Test
    public void testFunction() throws ServiceException {
        List<Literature> literatureList= literatureService.returnUserLiteratures(1);
        for (Literature literature : literatureList) {
            System.out.println(literature);
        }
        System.out.println("1");
    }

    @org.junit.Test
    public  void test() throws ServiceException {
        List<Literature> literatureList=literatureService.readAll(0,5);
        for (Literature l: literatureList) {
            System.out.println(l);
        }
        System.out.println(literatureService.noOfRecord());
    }

    @org.junit.Test
    public void testUSERFUNC() throws ServiceException {
        literatureManagementService.issueABook(1,1,3);
    }

    @org.junit.Test
    public void testFunction3() throws  ServiceException {
        literatureManagementService.returnIssue(2,1);
        System.out.println(literatureManagementService.findIssues(8));
    }

}
