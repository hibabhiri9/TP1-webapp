
package lab.mvc.controller;

import lab.mvc.model.Person;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Read form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDay = request.getParameter("birthDay");
        String sex = request.getParameter("sex");

        // 2. Create Person object
        Person person = new Person(
                firstName, lastName, birthDay, sex
        );

        // 3. Store bean in request
        request.setAttribute("person", person);

        // 4. Choose the appropriate JSP
        int year = Integer.parseInt(birthDay.substring(0, 4));

        String page;

        if (year < 1990) {
            page = "/old.jsp";
        } else {
            page = "/young.jsp";
        }

        // 5. Forward request to JSP
        request.getRequestDispatcher(page)
                .forward(request, response);
    }
}
