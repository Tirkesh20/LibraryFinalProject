package by.emaptc.LibraryProject.service.implementation;

import by.emaptc.LibraryProject.dao.LiteratureManagementDAO;
import by.emaptc.LibraryProject.dao.implementation.DaoProvider;
import by.emaptc.LibraryProject.entity.LiteratureManagement;
import by.emaptc.LibraryProject.entity.enumEntity.Status;
import by.emaptc.LibraryProject.exception.DAOException;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureManagementService;

import java.time.OffsetDateTime;
import java.util.List;

public class LiteratureManagementServiceImp implements LiteratureManagementService {
    private final LiteratureManagementDAO dao= DaoProvider.getInstance().getLiteratureManagementDAO();

    public void issueABook(int userId,int bookId,int days)throws ServiceException{
        LiteratureManagement management=new LiteratureManagement();
        management.setUser_id(userId);
        management.setLiterature_id(bookId);
        OffsetDateTime current=currentDate();
        OffsetDateTime futureDate=futureDate(current,days);
        management.setDateOfGive(current);
        management.setDateToReturn(futureDate);
        management.setStatus(Status.ISSUED);
        try {
            dao.insert(management);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<LiteratureManagement> findIssues(int id) throws ServiceException {
        try {
            return dao.readByUserId(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void changeIssueStatus(int id,Status status)throws ServiceException{
        String s=status.toString();
        try {
            dao.updateIssueStatus(id,s);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public int userHasLimit(int userId) throws ServiceException {
        try {
            return dao.countUserIssues(userId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean userHasBook(int userId,int literature_id) {
        return true;
    }
    private OffsetDateTime currentDate(){
        return OffsetDateTime.now();
    }

    private OffsetDateTime futureDate(OffsetDateTime currentDate,int days){
        return currentDate.plusDays(days);
    }


}
