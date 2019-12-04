package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *  按资源被访问的方式拦截
 *      直接请求的方式会被拦截
 *          转发的方式不会被拦截
 *              可以在后面设置多个值   dispatcherTypes = {DispatcherType.REQUEST，DispatcherType.FORWARD}
 */
//@WebFilter(value = "/index.jsp",dispatcherTypes = {DispatcherType.FORWARD,DispatcherType.REQUEST})//路径拦截
public class FilterDemo6 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterDemo6.doFilter--被触发");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * 服务器正常关闭时执行。filter被销毁
     */
    @Override
    public void destroy() {

    }
}
