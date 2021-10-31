package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.LiteratureDAO;
import by.emaptc.LibraryProject.dao.LiteratureManagementDAO;
import by.emaptc.LibraryProject.dao.UserDAO;

public class DaoProvider {
    private final UserDAO userDAO=new UserDAOImpl();
    private final LiteratureDAO literatureDAOImpl =new LiteratureDAOImpl();
    private final LiteratureManagementDAO literatureManagementDAOImpl =new LiteratureManagementDAOImpl();
    private final FeedbackDAO feedbackDAO=new FeedbackDAO();

    private DaoProvider(){

    }

    private static class SingletonProvider {
        private static final DaoProvider instance=new DaoProvider();
    }

    public static DaoProvider getInstance(){
        return SingletonProvider.instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public LiteratureDAO getLiteratureDAO() {
        return literatureDAOImpl;
    }

    public LiteratureManagementDAO getLiteratureManagementDAO() {
        return literatureManagementDAOImpl;
    }

    public FeedbackDAO getFeedbackDAO() {
        return feedbackDAO;
    }
}
