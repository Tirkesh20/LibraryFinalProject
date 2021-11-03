package by.emaptc.LibraryProject.controller.command;

import by.emaptc.LibraryProject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
    String USER_LITERATURES="literatures";

    Page execute(HttpServletRequest request, HttpServletResponse response)throws ServiceException;

    String LOGIN_PARAMETER = "login";
}
