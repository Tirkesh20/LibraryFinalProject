import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureManagementService;
import by.emaptc.LibraryProject.service.LiteratureService;
import by.emaptc.LibraryProject.service.implementation.LiteratureManagementServiceImp;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import java.util.List;

public class Test {

    LiteratureService literatureService= ServiceProvider.getInstance().getLiteratureService();
    LiteratureManagementService literatureManagementService= ServiceProvider.getInstance().getLiteratureManagementService();

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

}
