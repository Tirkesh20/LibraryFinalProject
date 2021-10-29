package by.emaptc.LibraryProject.service.implementation;

import by.emaptc.LibraryProject.service.FeedbackService;
import by.emaptc.LibraryProject.service.LiteratureManagementService;
import by.emaptc.LibraryProject.service.LiteratureService;
import by.emaptc.LibraryProject.service.UserService;

public class ServiceProvider {
    private final UserService userService=new UserServiceImpl();
    private final LiteratureService literatureService =new LiteratureServiceImpl();
    private final LiteratureManagementService literatureManagementService=new LiteratureManagementServiceImp();
    private final FeedbackService feedbackService=new FeedbackServiceImp();

    private ServiceProvider(){

    }

    private static class SingletonProvider{
        private static final ServiceProvider INSTANCE=new ServiceProvider();

    }

    public static  ServiceProvider getInstance(){
        return SingletonProvider.INSTANCE;
    }

    public UserService getUserService() {
        return userService;
    }


    public LiteratureService getLiteratureService() {
        return literatureService;
    }



    public LiteratureManagementService getLiteratureManagementService() {
        return literatureManagementService;
    }


    public FeedbackService getFeedbackService() {
        return feedbackService;
    }
}
