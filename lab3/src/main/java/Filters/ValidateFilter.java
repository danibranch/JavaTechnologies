package Filters;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ValidateFilter", urlPatterns = {"/result"})
public class ValidateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getMethod().equals("POST")) {
            String language = request.getParameter("language");
            String word = request.getParameter("word");
            String definition = request.getParameter("definition");

            if (language == null || language.isEmpty()) {
                request.setAttribute("errorMessage", "Language is missing");
                return;
            }
            if (word == null || word.isEmpty()) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/input");
                return;
            }
            if (definition == null || definition.isEmpty()) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/input");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
