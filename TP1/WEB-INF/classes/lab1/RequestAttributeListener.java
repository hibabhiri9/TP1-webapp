
package lab1;

import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class RequestAttributeListener
        implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
        System.out.println("Request attribute added: "
                + event.getName() + " = " + event.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
        System.out.println("Request attribute replaced: "
                + event.getName() + " = " + event.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
        System.out.println("Request attribute removed: "
                + event.getName() + " = " + event.getValue());
    }
}
