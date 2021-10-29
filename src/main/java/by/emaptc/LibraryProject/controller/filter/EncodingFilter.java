package by.emaptc.LibraryProject.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final String ENCODING_PARAMETER_NAME = "encoding";
    private String encoding;
    /**
     * Destroys the encoding string.
     */
    public void destroy() {
        encoding = null;
    }

    /**
     * Sets encoding to the request and response.
     */

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
    }

    /**
     * Initialize the encoding type. The encoding is specified in web.xml.
     */
    public void init(FilterConfig fConfig) throws ServletException {
        encoding = fConfig.getInitParameter(ENCODING_PARAMETER_NAME);
    }

}