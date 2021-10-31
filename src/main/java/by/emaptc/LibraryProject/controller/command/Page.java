package by.emaptc.LibraryProject.controller.command;

public class Page  {
    /**
     * Common pages.
     */
    public static final String LOGIN_PAGE_PATH = "/login";
    public static final String MAIN_PAGE_PATH = "/index.jsp";
    public static final String REGISTER_PAGE_PATH = "/register";

    /**
     * User pages.
     */
    public static final String LITERATURE_LIST="/WEB-INF/jsp/common/literatureList.jsp";
    public static final String USER_LITERATURE_LIST="/userLiteratures";
    public static final String ISSUE_URL="/issue";
    public static final String WARN_PAGE="";
    /**
     *admin pages
     */
    public static final String EDIT_RATE_PAGE_PATH = "/WEB-INF/jsp/admin/editRate.jsp";
    public static final String RATE_HISTORY_PAGE_PATH = "/WEB-INF/jsp/admin/rateHistory.jsp";
    private String url;
    private boolean redirect;
    private String messageKey;

    public Page(String url, boolean redirect) {
        this.url = url;
        this.redirect = redirect;
    }

    public Page( ) {
        
    }

    public Page(String loginPagePath, boolean b, String loginErrorMessageKey) {
        this.url=loginPagePath;
        this.redirect=b;
        this.messageKey=loginErrorMessageKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
