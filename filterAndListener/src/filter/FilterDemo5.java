package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *  路径拦截
 */
//@WebFilter("/user/*")//路径拦截
public class FilterDemo5 implements Filter {
    /**
     * 服务器被创建时执行
     *      用于加载资源
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //服务器创建时执行
        System.out.println("FilterDemo5.init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("FilterDemo5.doFilter");
    }

    /**
     * 服务器正常关闭时执行。filter被销毁
     */
    @Override
    public void destroy() {
        System.out.println("FilterDemo4.destroy");
    }
}
