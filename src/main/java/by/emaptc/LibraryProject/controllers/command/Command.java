package by.emaptc.LibraryProject.controllers.command;

import by.emaptc.LibraryProject.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;


public interface Command {
    /**
 * Parameters.
 */

String PASSWORD_PARAMETER = "password";

    /**
     * Attributes.
     */
     String USER_ATTRIBUTE ="user" ;
    String MESSAGE_ATTRIBUTE = "message";
    String LIST_ATTRIBUTE ="list";
    String MY_LITERATURES_URL="";
    String USER_LITERATURES="user_literatures";
    Page execute(HttpServletRequest request)throws ServiceException;

    String LOGIN_PARAMETER = "login";
}
