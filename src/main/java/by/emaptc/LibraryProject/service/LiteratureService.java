package by.emaptc.LibraryProject.service;

import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.exception.ServiceException;

import java.util.List;

public interface LiteratureService {

    public void add(Literature literature)throws ServiceException;

    public void update(Literature literature)throws ServiceException;

    public void delete(int id)throws ServiceException;

    public Literature read(int id)throws ServiceException;

    public List<Literature> readAll(int noOfSet,int noOfPages)throws ServiceException;

    int noOfRecord();

    List<Literature> returnUserLiteratures(int i) throws ServiceException;
}
