package by.emaptc.LibraryProject.service.implementation;

import by.emaptc.LibraryProject.dao.implementation.DaoProvider;
import by.emaptc.LibraryProject.dao.implementation.LiteratureManagementDAO;
import by.emaptc.LibraryProject.entity.LiteratureManagement;
import by.emaptc.LibraryProject.entity.enums.Status;
import by.emaptc.LibraryProject.exceptions.DAOException;
import by.emaptc.LibraryProject.exceptions.ServiceException;

import java.time.OffsetDateTime;
import java.util.List;

public class LiteratureManagementService {
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
            dao.startTransaction();
            dao.insert(management);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }finally {
            dao.close();
        }
    }

    public List<LiteratureManagement> findIssues(int id) throws ServiceException {
        try {
            dao.startTransaction();
            return dao.readByUserId(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }finally {
            dao.close();
        }
    }

    public void changeIssueStatus(int id,String status)throws ServiceException{
        try {
            dao.startTransaction();
            dao.updateIssueStatus(id,status);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }finally {
            dao.close();
        }
    }

    public int userHasLimit(int userId) throws ServiceException {
        try {
            dao.startTransaction();
            return dao.countUserIssues(userId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        } finally {
            dao.close();
        }
    }

    public boolean userHasBook(int userId,int bookId) {
        return true;
    }
    private OffsetDateTime currentDate(){
        return OffsetDateTime.now();
    }

    private OffsetDateTime futureDate(OffsetDateTime currentDate,int days){
        return currentDate.plusDays(days);
    }


}
