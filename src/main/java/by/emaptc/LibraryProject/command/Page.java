package by.emaptc.LibraryProject.command;

public class Page  {
    private String url;
    private boolean redirect;
    private String messageKey;

    public Page(String url, boolean redirect) {
        this.url = url;
        this.redirect = redirect;
    }
    public Page( ) {
        
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
