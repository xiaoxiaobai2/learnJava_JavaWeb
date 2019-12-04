package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *  filter 执行过程
 */
//@WebFilter("/*")
public class FilterDemo3 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //来的时候拦截，，，，此处增强request
        System.out.println("来时、、、、");

        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        //回的时候拦截，，，，此处增强response
        System.out.println("回时、、、、");

    }

    @Override
    public void destroy() {

    }
}
