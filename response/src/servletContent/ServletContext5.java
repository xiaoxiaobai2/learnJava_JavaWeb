package servletContent;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取真实路径，方便读取资源文件，可存储在
 *          src  ，  WEB-INF   , web  下
 */
@WebServlet("/servletContext5")
public class ServletContext5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ServletContext
        ServletContext servletContext = this.getServletContext();
        String path1=servletContext.getRealPath("/a.txt");//web下
        String path2=servletContext.getRealPath("/WEB-INF/b.txt");//WEB-INF下
        String path3=servletContext.getRealPath("/WEB-INF/classes/c.txt");//src下
        System.out.println(path1);
        System.out.println(path2);
        System.out.println(path3);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
