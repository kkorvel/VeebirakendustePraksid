package exservlet.model;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("init meetod");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
