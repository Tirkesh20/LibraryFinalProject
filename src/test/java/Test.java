import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.entity.LiteratureManagement;
import by.emaptc.LibraryProject.entity.enums.Status;
import by.emaptc.LibraryProject.exceptions.ServiceException;
import by.emaptc.LibraryProject.service.implementation.LiteratureManagementService;
import by.emaptc.LibraryProject.service.implementation.LiteratureServiceImpl;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Test {

    LiteratureServiceImpl literatureService= ServiceProvider.getInstance().getLiteratureService();
    LiteratureManagementService literatureManagementService= ServiceProvider.getInstance().getLiteratureManagementService();

    @org.junit.Test
    public void testFunction() throws ServiceException {
        Set<Literature> literatureList= (Set<Literature>) literatureService.returnUserLiteratures(1);
        for (Literature literature : literatureList) {
            System.out.println(literature);
        }
        System.out.println("1");
    }

    @org.junit.Test
    public void testUSERFUNC() throws ServiceException {
        LiteratureManagement literatureManagement=new LiteratureManagement();
        literatureManagement.setLiterature_id(1);
        literatureManagement.setUser_id(1);
        Calendar calendar=Calendar.getInstance();
         Date dateTime=calendar.getTime();
        Timestamp timestamp=new Timestamp(dateTime.getTime());
        literatureManagement.setDateOfGive(timestamp);
        literatureManagement.setDateToReturn(timestamp);
        literatureManagement.setStatus(Status.ISSUED);
        literatureManagementService.issueABook(literatureManagement);
    }

}
