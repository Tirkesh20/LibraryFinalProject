package by.emaptc.LibraryProject.dao;

import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.entity.enumEntity.Genre;
import by.emaptc.LibraryProject.exception.DAOException;

import java.util.List;

public interface LiteratureDAO {

    int add(Literature literature)throws DAOException;
    void deleteLiteratureByID(int id) throws DAOException;
    int getNoOfRecords();
    Literature readByID(int id) throws DAOException;
    List<Literature> returnUserLiteratures(int id) throws DAOException;
    List<Literature> readByGenre(Genre genre) throws DAOException;
    void update(Literature literature) throws DAOException;
    List<Literature> readAll(int offset,int noOfPages) throws DAOException;
}
