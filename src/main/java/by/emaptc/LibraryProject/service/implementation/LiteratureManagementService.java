package by.emaptc.LibraryProject.service.implementation;

import by.emaptc.LibraryProject.dao.implementation.DaoProvider;
import by.emaptc.LibraryProject.dao.implementation.LiteratureManagementDAO;
import by.emaptc.LibraryProject.entity.LiteratureManagement;
import by.emaptc.LibraryProject.exceptions.DAOException;
import by.emaptc.LibraryProject.exceptions.ServiceException;

import java.util.List;

public class LiteratureManagementService {
    private final LiteratureManagementDAO dao= DaoProvider.getInstance().getLiteratureManagementDAO();

    public void issueABook(LiteratureManagement management)throws ServiceException{
        try {
            dao.startTransaction();
            dao.insertLiterature(management);
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
}
