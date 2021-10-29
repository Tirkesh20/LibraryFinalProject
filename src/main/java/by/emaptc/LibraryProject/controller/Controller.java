package by.emaptc.LibraryProject.controller;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.CommandFactory;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.exception.ServiceException;
import by.emaptc.LibraryProject.dao.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "MainServlet" , value = "/controller")
public class Controller extends HttpServlet {
   private final CommandFactory commandFactory = new CommandFactory();
    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.closePool();
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            Command command = commandFactory.getCommand(req);
            Page page = command.execute(req);
            boolean isRedirect = page.isRedirect();
            if (isRedirect) {
                redirect(page, req, resp);
            } else {
                forward(page, req, resp);
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServletException(e.getMessage(), e);
        }


    }

    private void redirect(Page page, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = page.getUrl();
        response.sendRedirect(request.getContextPath() + url);
    }

    private void forward(Page page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = page.getUrl();
        String messageKey = page.getMessageKey();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }
}
