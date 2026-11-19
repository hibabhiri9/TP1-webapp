
package lab1;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/* @WebFilter("/*") : l'utilisation de l'annotation au lieu du declaration dans le web.xml*/
public class LoggingFilter implements Filter {

    private String logPrefix;

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        logPrefix = filterConfig.getInitParameter("logPrefix");
        System.out.println(logPrefix + " LoggingFilter initialized");
    }


   @Override
    public void doFilter(ServletRequest request,
                        ServletResponse response,
                        FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        System.out.println(logPrefix + " 9balll chain.doFilter()");
        System.out.println(logPrefix + " Request URL: " + httpRequest.getRequestURL());

        // Record start time
        long startTime = System.nanoTime();

        chain.doFilter(request, response);

        // Record end time
        long endTime = System.nanoTime();

        // Calculate execution time in milliseconds
        long executionTime = (endTime - startTime) / 1_000_000;

        System.out.println(logPrefix + " baaaa3d chain.doFilter()");
        System.out.println(logPrefix + " Execution time: " + executionTime + " ms");
    }

    @Override
    public void destroy() {
        System.out.println("LoggingFilter destroyed");
    }
}
