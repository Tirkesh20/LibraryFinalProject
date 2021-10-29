package by.emaptc.LibraryProject.controller.command.implementation.common;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.Feedback;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.FeedbackService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HomePageCommand implements Command {
    private final FeedbackService feedbackService;
    private  static final Logger LOGGER=LogManager.getLogger(HomePageCommand.class.getName());
    public HomePageCommand() {
        feedbackService= ServiceProvider.getInstance().getFeedbackService();
    }

    @Override
    public Page execute(HttpServletRequest request){
        try {
            List<Feedback> feedbacks= feedbackService.mostLiked();
            request.setAttribute("feedbacks",feedbacks);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            return new Page("/error",true,"500");
        }
        return new Page(Page.MAIN_PAGE_PATH, false);
    }
}
