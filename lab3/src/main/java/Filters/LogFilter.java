package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@WebFilter(filterName = "LoggingFilter", urlPatterns = {"/input", "/result"})
public class LogFilter implements Filter {
    private Logger logger;

    @Override
    public void init(FilterConfig filterConfig) {
        logger = Logger.getLogger(getClass().getName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Http method " + request.getAttribute("method") + " used");
        logger.info("IP address of the client is " + request.getRemoteAddr());
        logger.info("User language is " + request.getLocale().toString());

        StringBuilder parameterMap = new StringBuilder();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            parameterMap.append(entry.getKey()).append(" : [");
            for (String value : entry.getValue()) {
                parameterMap.append(value).append(", ");
            }
            parameterMap.delete(parameterMap.length() - 2, parameterMap.length());
            parameterMap.append("]\n");
        }
        logger.info("Parameters of the requests are " + parameterMap.toString());

        chain.doFilter(request, response);
    }
}
