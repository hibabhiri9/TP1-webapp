
package lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import java.io.IOException;
import java.io.PrintWriter;

public class firstservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String sex = request.getParameter("sex");

        Cookie c1 = new Cookie("firstName", firstName);
        Cookie c2 = new Cookie("LastName", lastName);
        Cookie c3 = new Cookie("birthDay", birthDate);
        Cookie c4 = new Cookie("Sex", sex);

        response.addCookie(c1);
        response.addCookie(c2);
        response.addCookie(c3);
        response.addCookie(c4);

        // Create the response
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Your Information</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h2>Person Information</h2>");

        out.println("<table border='1'>");

        // Table header
        out.println("<tr>");
        out.println("<th>First Name</th>");
        out.println("<th>Last Name</th>");
        out.println("<th>Birth Date</th>");
        out.println("<th>Sex</th>");
        out.println("</tr>");

        // Table data
        out.println("<tr>");
        out.println("<td>" + firstName + "</td>");
        out.println("<td>" + lastName + "</td>");
        out.println("<td>" + birthDate + "</td>");
        out.println("<td>" + sex + "</td>");
        out.println("</tr>");

        out.println("</table>");

        out.println("<br><br>");
        out.println("<a href='ReadCookie'>Read saved cookies</a>");
        out.println("<br>");
        out.println("<a href='VisitCounter'>Go to Visit Counter</a>");
        out.println("<br>");
        out.println("<a href='ShowSession'>Go to Show Session</a>");
        out.println("<br>");
        out.println("<a href='items.html'>Go to My Items</a>");

        out.println("</body>");
        out.println("</html>");
    }
}
