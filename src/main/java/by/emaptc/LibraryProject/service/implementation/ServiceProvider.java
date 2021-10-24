package by.emaptc.LibraryProject.service.implementation;

public class ServiceProvider {
    private UserServiceImpl userService=new UserServiceImpl();
    private LiteratureServiceImpl literatureServiceImpl =new LiteratureServiceImpl();
    private LiteratureManagementService literatureManagementService=new LiteratureManagementService();

    private ServiceProvider(){

    }

    private static class SingletonProvider{
        private static final ServiceProvider INSTANCE=new ServiceProvider();

    }

    public static  ServiceProvider getInstance(){
        return SingletonProvider.INSTANCE;
    }
    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public LiteratureServiceImpl getLiteratureService() {
        return literatureServiceImpl;
    }

    public void setLiteratureService(LiteratureServiceImpl literatureServiceImpl) {
        this.literatureServiceImpl = literatureServiceImpl;
    }

    public LiteratureManagementService getLiteratureManagementService() {
        return literatureManagementService;
    }

    public void setLiteratureManagementService(LiteratureManagementService literatureManagementService) {
        this.literatureManagementService = literatureManagementService;
    }
}
