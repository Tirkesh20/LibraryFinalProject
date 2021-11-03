package by.emaptc.LibraryProject.controller.command.implementation.userCommand;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.Feedback;
import by.emaptc.LibraryProject.entity.User;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.FeedbackService;
import by.emaptc.LibraryProject.service.implementation.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddFeedbackCommand implements Command {

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        FeedbackService feedbackService= ServiceProvider.getInstance().getFeedbackService();
        User user=(User) request.getSession().getAttribute(USER_ATTRIBUTE);
        if (user==null){
            return new Page(Page.LOGIN_PAGE_PATH,true);
        }
        int literature_id=Integer.parseInt(request.getParameter("literature_id"));
        Feedback feedback=new Feedback();
        feedback.setUserId(user.getId());
        feedback.setBookId(literature_id);
        feedback.setComment("");
        feedback.setMark(0);
        feedbackService.add(feedback);
        return new Page();
    }
}
