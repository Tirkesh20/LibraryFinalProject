import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.exceptions.ServiceException;
import by.emaptc.LibraryProject.service.implementation.LiteratureServiceImpl;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import java.util.List;

public class Test {

    LiteratureServiceImpl literatureService= ServiceProvider.getInstance().getLiteratureService();


    @org.junit.Test
    public void testFunction() throws ServiceException {
        List<Literature> literatureList=literatureService.readAll();
        for (Literature literature : literatureList) {
            System.out.println(literature);
        }
    }

}
