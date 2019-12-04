package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1、进行强转
        HttpServletRequest request = (HttpServletRequest) req;

        //2、判断是否是登陆相关资源。摆阔样式、验证码、等。
        System.out.println("LoginFilter.doFilter");
        String uri = request.getRequestURI();
        if (uri.contains("login.jsp") || uri.contains("loginServlet") || uri.contains("/css") || uri.contains("/js") || uri.contains("/fonts") || uri.contains("/checkCodeServlet"))
            chain.doFilter(req, resp);
            //3、如果不是，判断是否含有登录的用户，有放行，否则跳转登录

        else if (request.getSession().getAttribute("user") != null) {
            //含有user，放行
            chain.doFilter(req, resp);
        } else {
            request.setAttribute("login_msg", "您还没有登录，请先登录！");
            request.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
