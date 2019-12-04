package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
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

@WebServlet("/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //已经用过滤器吧所有的编码都转换好了
        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("gbk");
        //用来封装返回结果
        ResultInfo resultInfo = new ResultInfo();

        /*
            校验验证码是否正确
         */
        HttpSession session = request.getSession();
        String checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//保证验证码只使用一次

        if (checkcode==null||!checkcode.equalsIgnoreCase(request.getParameter("check"))){
            //验证码输入不正确
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
        }else {
            //验证码输不正确

            //1、获取数据
            Map<String, String[]> userInfo = request.getParameterMap();
            //2、创建对象
            User user = new User();
            try {
                //3、将数据封装到user对象中
                BeanUtils.populate(user,userInfo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //4、调用service完成注册
            UserService service = new UserServiceImpl();
            boolean flag = service.register(user);
            if (flag){
                //返回真，则正常注册
                resultInfo.setFlag(true);
                resultInfo.setErrorMsg("注册成功！");
            }else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("用户名已存在");
            }
        }

        //将返回结果包装成json文件
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(resultInfo);
        response.getWriter().write(value);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
