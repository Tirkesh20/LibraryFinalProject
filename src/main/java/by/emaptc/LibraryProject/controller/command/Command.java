package by.emaptc.LibraryProject.controller.command;

import by.emaptc.LibraryProject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;


public interface Command {
    /**
 * Parameters.
 */

String PASSWORD_PARAMETER = "password";

    /**
     * Attributes.
     */
    int MAX_BOOK_COUNT=5;
     String USER_ATTRIBUTE ="user" ;
    String MESSAGE_ATTRIBUTE = "message";
    String LIST_ATTRIBUTE ="list";
    String USER_LITERATURES="user_literatures";
    Page execute(HttpServletRequest request)throws ServiceException;

    String LOGIN_PARAMETER = "login";
}
