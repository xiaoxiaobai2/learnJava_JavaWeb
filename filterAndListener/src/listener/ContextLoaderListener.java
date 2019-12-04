package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

@WebListener()
public class ContextLoaderListener implements ServletContextListener{
    /**
     * 服务器创建时进行初始化
     * 可以在此处加载全局资源文件
     * @param sce
     */
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象被创建了");
        ServletContext servletContext = sce.getServletContext();
        String realPath = servletContext.getRealPath("WEB-INF/classes/applicationContext.xml");
        try {
            BufferedReader bf = new BufferedReader(new FileReader(realPath));
            System.out.println(bf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        System.out.println("ServletContext对象被销毁了");
    }

}
