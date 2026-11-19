package lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListItems extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String item = request.getParameter("newItem");

        HttpSession session = request.getSession();

        // Get the existing list from the session
        ArrayList<String> items =
                (ArrayList<String>) session.getAttribute("items");

        // If the list does not exist, create it
        if (items == null) {
            items = new ArrayList<>();
        }

        // Add the new item
        if (item != null && !item.trim().isEmpty()) {
            items.add(item.trim());
        }

        // Save the list in the session
        session.setAttribute("items", items);

        // Count how many times each item was entered
        Map<String, Integer> counts =
                (Map<String, Integer>) session.getAttribute("counts");

        if (counts == null) {
            counts = new HashMap<>();
        }

        if (item != null && !item.trim().isEmpty()) {
            String cleanItem = item.trim();
            counts.put(cleanItem,
                    counts.getOrDefault(cleanItem, 0) + 1);
        }

        session.setAttribute("counts", counts);

        // Display the list
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>My Items</title></head><body>");

        out.println("<h2>My Items</h2>");
        out.println("<ul>");

        for (String currentItem : items) {
            out.println("<li>" + escapeHtml(currentItem) + "</li>");
        }

        out.println("</ul>");

        out.println("<h2>Number of times each item was entered</h2>");
        out.println("<ul>");

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            out.println("<li>" + escapeHtml(entry.getKey())
                    + " : " + entry.getValue() + " time(s)</li>");
        }

        out.println("</ul>");

        out.println("<a href='items.html'>Add another item</a>");
        out.println("</body></html>");
    }

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
