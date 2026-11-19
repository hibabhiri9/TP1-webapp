package lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class VisitCounter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int visits = 0;

        // 1. Read existing cookies
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("visits")) {
                    visits = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }

        // 2. Increment the counter
        visits++;

        // 3. Create the updated cookie
        Cookie visitCookie =
                new Cookie("visits", String.valueOf(visits));

        visitCookie.setMaxAge(24 * 60 * 60);
        visitCookie.setPath(request.getContextPath());

        // 4. Send the cookie to the browser
        response.addCookie(visitCookie);

        // 5. Display the number of visits
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Visit Counter</title></head>");
        out.println("<body>");

        out.println("<h2>Visit Counter</h2>");
        out.println("<p>Number of visits: " + visits + "</p>");


        out.println("</body>");
        out.println("</html>");
    }
}