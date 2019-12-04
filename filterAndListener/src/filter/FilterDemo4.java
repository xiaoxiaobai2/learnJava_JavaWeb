package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *  filter 生命周期
 */
//@WebFilter("/*")
public class FilterDemo4 implements Filter {
    /**
     * 服务器被创建时执行
     *      用于加载资源
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //服务器创建时执行
        System.out.println("FilterDemo4.init");
    }

    /**
     *  访问被拦截资源时执行 ，可以执行多次
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("FilterDemo4.doFilter");
    }

    /**
     * 服务器正常关闭时执行。filter被销毁
     */
    @Override
    public void destroy() {
        System.out.println("FilterDemo4.destroy");
    }
}
