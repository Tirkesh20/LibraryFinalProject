package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.AbstractDAO;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.entity.LiteratureManagement;
import by.emaptc.LibraryProject.entity.enums.Genre;
import by.emaptc.LibraryProject.exceptions.DAOException;

import java.util.Collections;
import java.util.List;

public class LiteratureManagementDAO  extends AbstractDAO<LiteratureManagement>{

    public int insertLiterature(LiteratureManagement management) throws DAOException {
        String fields = "insert into literatures (name, lastname ,username, password, email, status) values(?,?,?,?,?,?)";
        return insert(management, fields);
    }

    public void deleteLiteratureByID(int id) throws DAOException {
        String sqlQuery = "delete from literatures  where id = ?";
        List<String> params = Collections.singletonList(String.valueOf(id));
        executeQuery(sqlQuery, params);
    }

//    public Literature readByID(int id) throws DAOException {
//        List<String> params = Collections.singletonList(String.valueOf(id));
//        return getEntity(SQL_READ_BY_ID_QUERY,params);
//    }
//
//    public List<Literature> readByGenre(Genre genre) throws DAOException {
//        List<String> params = Collections.singletonList(String.valueOf(genre));
//        return getEntities(SQL_READ_ALL_QUERY,params);
//    }
//
//    public int deleteById(int id) throws DAOException {
//        List<String> params = Collections.singletonList(String.valueOf(id));
//        return executeQuery(SQL_DELETE_QUERY,params);
//    }

}
