package by.emaptc.LibraryProject.service;

import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.exceptions.ServiceException;

public interface LiteratureService {

    public void add(Literature literature)throws ServiceException;

    public void update(Literature literature)throws ServiceException;

    public void delete(int id)throws ServiceException;

    public Literature read(int id)throws ServiceException;

}
