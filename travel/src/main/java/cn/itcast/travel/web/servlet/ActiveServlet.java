package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeServlet")
public class ActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ActiveServlet.doPost");

        //获取状态码
        String code = request.getParameter("code");
        System.out.println("code = " + code);

        //在数据库中查询状态码，判断是否存在
        UserServiceImpl userService = new UserServiceImpl();
        boolean flag = userService.checkCode(code);
        if (flag){
            //重定向
            response.sendRedirect("login.html");
        }else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("请别乱玩！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
