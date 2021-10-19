package by.emaptc.LibraryProject.dao.implementation;

public class DaoProvider {
   private final UserDAOImpl userDAO=new UserDAOImpl();
    private final LiteratureDAO literatureDAO=new LiteratureDAO();
    private final LiteratureManagementDAO literatureManagementDAO=new LiteratureManagementDAO();

    private DaoProvider(){


    }

    private static class SingletonProvider {
        private static final DaoProvider instance=new DaoProvider();
    }

    public static DaoProvider getInstance(){
        return SingletonProvider.instance;
    }

    private UserDAOImpl getUserDao(){
        return userDAO;
    }

    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

    public LiteratureDAO getLiteratureDAO() {
        return literatureDAO;
    }

    public LiteratureManagementDAO getLiteratureManagementDAO() {
        return literatureManagementDAO;
    }

}
