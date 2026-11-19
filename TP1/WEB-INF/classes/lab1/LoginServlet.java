package lab1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");

        // Create a new session or retrieve the existing one
        HttpSession session = request.getSession();

        // Store the username in the session
        SessionUser user = new SessionUser(username);

        session.setAttribute("user", user);

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Login Confirmation</title></head>");
        out.println("<body>");

        out.println("<h2>Login successful!</h2>");
        out.println("<p>Welcome, " + username + "!</p>");
        out.println("<a href='items.html'>Go to Items Page</a>");

        out.println("</body>");
        out.println("</html>");
    }
}
