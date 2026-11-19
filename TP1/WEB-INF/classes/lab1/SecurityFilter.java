package lab1;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*@WebFilter("/ListItems") using annotation*/
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                        ServletResponse response,
                        FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("SecurityFilter: 9balll chain.doFilter()");

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("user") != null) {

            System.out.println("SecurityFilter: User authorized");

            chain.doFilter(request, response);

        } else {

            System.out.println("SecurityFilter: User refused");
            res.sendRedirect(req.getContextPath() + "/login.html");
        }

        System.out.println("SecurityFilter: baaa3d chain.doFilter()");
    }
}