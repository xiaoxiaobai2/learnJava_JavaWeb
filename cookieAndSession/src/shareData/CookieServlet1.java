package shareData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CookieServlet1")
public class CookieServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、创建一个cookie
        Cookie msg = new Cookie("msg", "你好啊！");
        //2、发送cookie
        response.addCookie(msg);

        //设置最大存活时间（保存在硬盘）
        msg.setMaxAge(3000);//

        //实现同一台服务器内共享
        msg.setPath("/");

        //不同服务器之间共享数据
        msg.setDomain(".baidu.com");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
