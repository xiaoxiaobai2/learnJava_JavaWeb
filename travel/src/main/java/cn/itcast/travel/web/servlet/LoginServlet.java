package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        UserServiceImpl userService = new UserServiceImpl();
        User u = userService.login(user);

        //封装返回信息
        ResultInfo resultInfo = new ResultInfo();
/*
            校验验证码是否正确
         */
        HttpSession session = request.getSession();
        String checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//保证验证码只使用一次
//        System.out.println("checkcode = " + checkcode);
//        System.out.println("check = " + request.getParameter("check"));
//        System.out.println("username = " + request.getParameter("username"));

        if (checkcode==null||!checkcode.equalsIgnoreCase(request.getParameter("check"))) {
            //验证码输入不正确
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
        }else {
            //判断用户名密码，验证码等信息是否合法
            if (u==null){
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("用户名或密码错误！");
            }else if (!u.getStatus().equals("Y")){
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("邮箱未激活！");
            }else {
                resultInfo.setFlag(true);
                //存储登录的用户
                session.setAttribute("user",u);
            }
        }

        //把数据封装成json格式 发送回去
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultInfo);
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
