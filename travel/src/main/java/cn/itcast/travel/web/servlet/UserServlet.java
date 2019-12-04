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

/**
 *  优化servlet，减少servlet的数量
 *
 * 有关用户的servlet的集合
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    //userService 对象
    private UserService service = new UserServiceImpl();
    /**
     * 注册方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * 登陆方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        User u = service.login(user);

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

    /**
     * 退出登陆
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("1--------user = " + session.getAttribute("user"));
//        session.invalidate();
        //移除存在session中的user
        session.removeAttribute("user");
        System.out.println("user = " + session.getAttribute("user"));
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    /**
     * 查找当前登陆的用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserServlet.findLogin");
        HttpSession session = request.getSession();
        //获取当前登录的用户
        User user = (User)session.getAttribute("user");
        System.out.println(user);
        ObjectMapper mapper = new ObjectMapper();
        if (user!=null) {
            System.out.println(user.toString());
        }
        //封装到字节流
        mapper.writeValue(response.getOutputStream(),user);

    }

    /**
     * 激活
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ActiveServlet.doPost");

        //获取状态码
        String code = request.getParameter("code");
        System.out.println("code = " + code);

        //在数据库中查询状态码，判断是否存在
        boolean flag = service.checkCode(code);
        if (flag){
            //重定向
            System.out.println(request.getContextPath() +"/login.html");
            response.sendRedirect(request.getContextPath() +"/login.html");
        }else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("请别乱玩！");
        }
    }
}
