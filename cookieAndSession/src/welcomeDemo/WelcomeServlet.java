package welcomeDemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //注意tomcat cookie不支持空格
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss");
        String time = dateFormat.format(new Date(System.currentTimeMillis()));
        time = URLEncoder.encode(time, "utf-8");
        System.out.println(time);
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        if (cookies != null&&cookies.length!=0) {
            System.out.println("非空");
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("welcome")) {
                    flag = true;
                    System.out.println("找到了！");
                    response.getWriter().write("欢迎回来，您上次访问时间为：" + URLDecoder.decode(cookie.getValue(),"utf-8"));
                    cookie.setValue(time);
                    cookie.setMaxAge(60 * 60);
                    //注意修改值之后，需要重新添加
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        if ((cookies==null)||(cookies.length==0)||!flag){
            System.out.println("第一次来");
            Cookie welcome = new Cookie("welcome", time);
            welcome.setMaxAge(60 * 60);
            response.addCookie(welcome);
            response.getWriter().write("您好，欢迎您首次访问！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
