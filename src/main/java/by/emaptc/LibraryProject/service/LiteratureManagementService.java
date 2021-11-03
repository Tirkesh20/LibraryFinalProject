package by.emaptc.LibraryProject.service;

import by.emaptc.LibraryProject.entity.LiteratureManagement;
import by.emaptc.LibraryProject.entity.enumEntity.Status;
import by.emaptc.LibraryProject.exception.ServiceException;

import java.util.List;

public interface LiteratureManagementService {

    void issueABook(int userId,int bookId,int days)throws ServiceException;

    LiteratureManagement findByIssueID(int issue_id)throws ServiceException;

    List<LiteratureManagement> findIssues(int id) throws ServiceException;

    void changeIssueStatus(int id, Status status)throws ServiceException;

    void returnIssue(int userId, int literatureId)throws ServiceException;

    boolean userHasLimit(int userId) throws ServiceException;

    boolean userHasBook(int userId,int literature_id) throws ServiceException;

}
