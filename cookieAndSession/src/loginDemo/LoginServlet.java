package loginDemo;

import loginDemo.doLogin.DoUser2;
import loginDemo.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Im here");
        User loginUser=new User();
        //一次吧所有的参数都封装到bean对象
        Map<String, String[]> parameterMap = request.getParameterMap();
        String checkCode = parameterMap.get("checkCode")[0];
        HttpSession checkCodeSession = request.getSession();
        String checkCode_session = (String) checkCodeSession.getAttribute("checkCode_session");
        if (checkCode.equalsIgnoreCase(checkCode_session)){
            try {
                BeanUtils.populate(loginUser,parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
//        User user=DoUser.doUser(loginUser);
            User user= DoUser2.doUser(loginUser);

            System.out.println(user);
            if (user==null){
                //验证码错误
                /**
                 * 转发，不需要写虚拟路径
                 */
                request.setAttribute("username_error","用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else {
                //验证成功，重定向
                System.out.println("success");

                /**
                 * 重定向，两次请求，需要写虚拟路径，且不可以用 request.setAttribute
                 */
                Cookie cookie = new Cookie("username", parameterMap.get("username")[0]);
                response.addCookie(cookie);

//                checkCodeSession.setAttribute("username",parameterMap.get("username")[0]);
                response.sendRedirect(request.getContextPath()+"/success.jsp");
            }
        }else {
            //验证码错误
            request.setAttribute("cc_error","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
