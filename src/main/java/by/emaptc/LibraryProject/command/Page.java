package by.emaptc.LibraryProject.command;

public class Page  {
    /**
     * Common pages.
     */
    public static final String LOGIN_PAGE_PATH = "/pages/common/login.jsp";
    public static final String MAIN_PAGE_PATH = "/pages/common/main.jsp";
    public static final String REGISTER_PAGE_PATH = "/pages/common/register.jsp";

    /**
     *admin pages
     */
    public static final String EDIT_RATE_PAGE_PATH = "/pages/admin/editRate.jsp";
    public static final String RATE_HISTORY_PAGE_PATH = "/pages/admin/rateHistory.jsp";
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
