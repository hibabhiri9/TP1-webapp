package lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.MonthDay;
import java.time.format.DateTimeParseException;

public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Count visits
        Integer visits = (Integer) session.getAttribute("visits");

        if (visits == null) {
            visits = 1;
        } else {
            visits++;
        }

        session.setAttribute("visits", visits);

        //number of active sessions
        ServletContext context = getServletContext();

        Integer activeSessions =(Integer) context.getAttribute("activeSessions");

        if (activeSessions == null) {
            activeSessions = 0;
        }

        String fullName = (String) session.getAttribute("fullName");
        String dob = (String) session.getAttribute("dob");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Show Session</title>");
        out.println("</head><body>");

        if (fullName == null || dob == null) {

            out.println("<h2>Welcome to my site!</h2>");
            out.println("<p>Please enter your information.</p>");

            out.println("<form method='post' action='ShowSession'>");

            out.println("<label>Full name:</label>");
            out.println("<input type='text' name='fullName' required>");
            out.println("<br><br>");

            out.println("<label>Date of birth:</label>");
            out.println("<input type='date' name='dob' required>");
            out.println("<br><br>");

            out.println("<button type='submit'>Submit</button>");
            out.println("</form>");

        } else {

            out.println("<h2>Welcome back!</h2>");

            // Calculate days until the next birthday
            LocalDate today = LocalDate.now();
            LocalDate birthDate = LocalDate.parse(dob);

            LocalDate nextBirthday =
                    birthDate.withYear(today.getYear());

            if (!nextBirthday.isAfter(today)) {
                nextBirthday = birthDate.withYear(today.getYear() + 1);
            }

            long days = Period.between(
                    today, nextBirthday).getDays();

            // Use total days between the dates
            days = java.time.temporal.ChronoUnit.DAYS.between(
                    today, nextBirthday);

            out.println("<h3>Hi, " + escapeHtml(fullName)
                    + ". There are " + days
                    + " days to your birthday.</h3>");
        }

        out.println("<p>Number of visits: " + visits + "</p>");
        out.println("<p>Number of active sessions: " + activeSessions + "</p>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String fullName = request.getParameter("fullName");
        String dob = request.getParameter("dob");

        try {
            LocalDate birthDate = LocalDate.parse(dob);

            if (fullName == null || fullName.trim().isEmpty()
                    || birthDate.isAfter(LocalDate.now())) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Please enter a valid name and date of birth.");
                return;
            }

            HttpSession session = request.getSession();

            session.setAttribute("fullName", fullName.trim());
            session.setAttribute("dob", dob);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html><html><body>");
            out.println("<h2>Your information has been saved!</h2>");
            out.println("<p>Return to the site to see your birthday message.</p>");
            out.println("<a href='ShowSession'>Continue</a>");
            out.println("</body></html>");

        } catch (DateTimeParseException | NullPointerException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid date of birth.");
        }
    }

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}