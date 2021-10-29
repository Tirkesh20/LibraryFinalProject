package by.emaptc.LibraryProject.service;

import by.emaptc.LibraryProject.entity.Feedback;
import by.emaptc.LibraryProject.exception.ServiceException;

import java.util.List;

public interface FeedbackService {

    public List<Feedback> mostLiked()throws ServiceException;

    int insert(Feedback feedback) throws ServiceException;
}
