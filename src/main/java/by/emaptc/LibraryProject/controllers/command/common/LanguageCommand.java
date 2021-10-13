package by.emaptc.LibraryProject.controllers.command.common;

import by.emaptc.LibraryProject.controllers.command.Command;
import by.emaptc.LibraryProject.controllers.command.Page;
import by.emaptc.LibraryProject.utils.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;


import static by.emaptc.LibraryProject.utils.MessageManager.DEFAULT_LOCALE;

public class LanguageCommand implements Command {
    @Override
    public Page execute(HttpServletRequest request) {

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
