package by.emaptc.LibraryProject.service.implementation;

import by.emaptc.LibraryProject.dao.implementation.DaoProvider;
import by.emaptc.LibraryProject.dao.implementation.FeedbackDAO;
import by.emaptc.LibraryProject.entity.Feedback;
import by.emaptc.LibraryProject.exception.DAOException;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.service.FeedbackService;

import java.util.List;

public class FeedbackServiceImp implements FeedbackService {
  private final FeedbackDAO feedbackDAO= DaoProvider.getInstance().getFeedbackDAO();

    @Override
    public List<Feedback> mostLiked() throws ServiceException {
        try {
            feedbackDAO.startTransaction();
           return feedbackDAO.mostLiked();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }finally {
            feedbackDAO.close();
        }
    }

    @Override
    public int insert(Feedback feedback) throws ServiceException {
        try {
            feedbackDAO.startTransaction();
            return feedbackDAO.insert(feedback);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }finally {
            feedbackDAO.close();
        }
    }
}
