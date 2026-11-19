package lab1;

import jakarta.servlet.http.HttpSessionListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.ServletContext;

@WebListener
public class ApplicationListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Application started!");

        ServletContext context = event.getServletContext();
        context.setAttribute("appName", "ING1 Web Application");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("Application stopped!");
    }
}