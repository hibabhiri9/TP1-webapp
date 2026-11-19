package lab1;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;


public class ActiveSessionListener implements HttpSessionListener {

    private static final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        int active = counter.incrementAndGet();

        ServletContext context = event.getSession().getServletContext();
        context.setAttribute("activeSessions", active);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        int active = counter.decrementAndGet();

        ServletContext context = event.getSession().getServletContext();
        context.setAttribute("activeSessions", active);
    }
}