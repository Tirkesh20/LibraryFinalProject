package by.emaptc.LibraryProject.controller.filter;

import by.emaptc.LibraryProject.controller.command.CommandType;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;

import static by.emaptc.LibraryProject.controller.command.Command.USER_ATTRIBUTE;

public class CommandFilter implements Filter {

    private final EnumSet<CommandType> adminCommand = EnumSet.range(CommandType.SHOW_User,CommandType.DELETE_USER);
    private final EnumSet<CommandType> commonCommand = EnumSet.range(CommandType.LOGIN,CommandType.COMMON_CHANGE_LANGUAGE );
    private final EnumSet<CommandType> clientCommand = EnumSet.range(CommandType.CONFIRM_ISSUE_LITERATURE,CommandType.COMMON_RETURN_ISSUE);

    @Override
    public void init(FilterConfig fConfig) {

    }



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        if (isAuthorized(req)) {
            chain.doFilter(request, response);
        }else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(req.getContextPath() + Page.MAIN_PAGE_PATH);
        }
    }

    private boolean isAuthorized(HttpServletRequest req) {
        String command = req.getParameter("command");
        if (command == null){
            return true;
        }
        String commandTypeValue = command.toUpperCase();
        CommandType currentType = CommandType.getCommand(commandTypeValue);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        if (commonCommand.contains(currentType)) {
            return true;
        }
        if (user==null){
            return false;
        }
        String role = user.getRole().toString();
        if ("ADMIN".equals(role) && adminCommand.contains(currentType)) {
            return true;
        }
        return "USER".equals(role) && clientCommand.contains(currentType);

    }
    @Override
    public void destroy() {

    }
}
