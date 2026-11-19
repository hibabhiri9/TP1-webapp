package lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ReadCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Read Cookies</title></head>");
        out.println("<body>");
        out.println("<h2>Read Cookies</h2>");

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {

            out.println("<table border='1'>");
            out.println("<tr><th>Name</th><th>Value</th></tr>");

            String[] names = {
                "firstName", "LastName", "birthDay", "Sex"
            };

            for (String name : names) {
                for (Cookie cookie : cookies) {

                    if (cookie.getName().equals(name)) {
                        out.println("<tr>");
                        out.println("<td>" + name + "</td>");
                        out.println("<td>" + cookie.getValue() + "</td>");
                        out.println("</tr>");
                        break;
                    }
                }
            }

            out.println("</table>");

        } else {
            out.println("<p>No cookies found. Submit the form first.</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}