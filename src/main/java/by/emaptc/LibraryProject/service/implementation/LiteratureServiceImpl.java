package by.emaptc.LibraryProject.service.implementation;


import by.emaptc.LibraryProject.dao.LiteratureDAO;
import by.emaptc.LibraryProject.dao.implementation.DaoProvider;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.exception.DAOException;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureService;

import java.util.List;

public class LiteratureServiceImpl implements LiteratureService {
    private final LiteratureDAO dao= DaoProvider.getInstance().getLiteratureDAO();


    @Override
    public void add(Literature literature) throws ServiceException {
        try {
            dao.add(literature);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public void update(Literature literature) throws ServiceException {
        try {
            dao.update(literature);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            dao.deleteLiteratureByID(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }



    @Override
    public Literature read(int id) throws ServiceException {
        try {
            return dao.readByID(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Literature> readAll(int noOfSet, int noOfPages) throws ServiceException {
        try {
            return dao.readAll(noOfSet,noOfPages);
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public int noOfRecord(){
        return dao.getNoOfRecords();
    }

    public List<Literature> returnUserLiteratures(int id) throws ServiceException {
        try {
            return dao.returnUserLiteratures(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
