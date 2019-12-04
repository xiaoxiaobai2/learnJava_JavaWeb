package doLogin;

import domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Im here");
        User loginUser=new User();
//        String name=request.getParameter("username");
//        String password=request.getParameter("password");
//        loginUser.setName(name);
//        loginUser.setPassword(password);
        //一次吧所有的参数都封装到bean对象
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(loginUser,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        User user=DoUser.doUser(loginUser);
        User user=DoUser2.doUser(loginUser);

        System.out.println(user);
        if (user==null){
            System.out.println("fail");
            request.getRequestDispatcher("/FailServlet").forward(request,response);
        }else {
            System.out.println("success");
            request.setAttribute("user",user);
            request.getRequestDispatcher("/SuccessServlet").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
