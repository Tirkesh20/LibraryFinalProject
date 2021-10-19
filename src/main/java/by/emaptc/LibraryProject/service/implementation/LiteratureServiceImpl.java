package by.emaptc.LibraryProject.service.implementation;


import by.emaptc.LibraryProject.dao.implementation.DaoProvider;
import by.emaptc.LibraryProject.dao.implementation.LiteratureDAO;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.exceptions.DAOException;
import by.emaptc.LibraryProject.exceptions.ServiceException;
import by.emaptc.LibraryProject.service.LiteratureService;

import java.util.List;

public class LiteratureServiceImpl implements LiteratureService {

private final LiteratureDAO dao= DaoProvider.getInstance().getLiteratureDAO();
    @Override
    public void add(Literature literature) throws ServiceException {
        try {
            dao.startTransaction();
            dao.insertLiterature(literature);
        } catch (DAOException e) {
            e.printStackTrace();
        }finally {
            dao.close();
        }

    }

    @Override
    public void update(Literature literature) throws ServiceException {
        try {
            dao.startTransaction();
            dao.update(literature);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }finally {
            dao.close();
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
    try {
        dao.startTransaction();
        dao.deleteById(id);
    } catch (DAOException e) {
        throw new ServiceException(e.getMessage());
    }finally {
        dao.close();
    }
    }

    @Override
    public Literature read(int id) throws ServiceException {
        try {
        dao.startTransaction();
         return dao.readByID(id);
    } catch (DAOException e) {
        e.printStackTrace();
    }finally {
            dao.close();
        }
        return null;
    }

    @Override
    public List<Literature> readAll() throws ServiceException {
        try {
            dao.startTransaction();
           return dao.readAll();
        }catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }finally {
            dao.close();
        }
    }

    public List<Literature> returnUserLiteratures(int id) throws ServiceException {
        try {
            dao.startTransaction();
            return dao.returnUserLiteratures(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }finally {
            dao.close();
        }
    }
}
