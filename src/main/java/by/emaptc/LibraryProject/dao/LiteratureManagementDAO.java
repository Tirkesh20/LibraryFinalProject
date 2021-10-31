package by.emaptc.LibraryProject.dao;

import by.emaptc.LibraryProject.entity.LiteratureManagement;
import by.emaptc.LibraryProject.exception.DAOException;

import java.util.List;

public interface LiteratureManagementDAO {
    LiteratureManagement readByID(int id) throws DAOException;
    List<LiteratureManagement> readByUserId(int id) throws DAOException;
    void updateIssueStatus(int issue_id,String status)throws DAOException;
    void deleteById(int id) throws DAOException;
    int insert(LiteratureManagement literatureManagement) throws DAOException;
    int countUserIssues(int userId) throws DAOException;

}
