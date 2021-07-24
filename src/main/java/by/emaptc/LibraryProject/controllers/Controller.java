package by.emaptc.LibraryProject.controllers;

import by.emaptc.LibraryProject.command.Command;
import by.emaptc.LibraryProject.command.Factory;
import by.emaptc.LibraryProject.command.Page;
import by.emaptc.LibraryProject.exceptions.ServiceException;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doWork(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doWork(req,resp);
    }

    private void doWork(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String commandType=req.getParameter("command");
        Factory factory=new Factory();
        Command command =factory.getCommand(commandType);
        try {
            Page page = command.execute(req);
            if (page.isRedirect()){
                doRedirect(req,resp,page.getUrl());
            }else {
                doForward(req,resp,page.getUrl());
            }
        }catch (ServiceException e){
            throw new ServletException(e);
        }
    }

    private void doRedirect(HttpServletRequest req,HttpServletResponse res,String url) throws IOException {
        res.sendRedirect(url);
    }

    private void doForward(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
