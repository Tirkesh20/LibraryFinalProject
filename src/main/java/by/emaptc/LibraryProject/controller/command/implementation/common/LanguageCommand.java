package by.emaptc.LibraryProject.controller.command.implementation.common;

import by.emaptc.LibraryProject.controller.command.Command;
import by.emaptc.LibraryProject.controller.command.Page;
import by.emaptc.LibraryProject.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;


import static by.emaptc.LibraryProject.util.MessageManager.DEFAULT_LOCALE;

/**
 *
 * {@code Command} realization for performing changing of language action.
 *
 */
public class LanguageCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String localeValue = request.getParameter("locale");
        Locale locale = switch (localeValue) {
            case "ru" -> new Locale("ru", "RU");
            case "en" -> new Locale("en", "US");
            default -> DEFAULT_LOCALE;
        };
        Config.set(request.getSession(), Config.FMT_LOCALE, locale);
        MessageManager.changeLocale(locale);
        return new Page(Page.MAIN_PAGE_PATH, true);
    }
}
