package sensistive;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态代理的方式增强request
 */
@WebFilter("/loginServlet")
public class SensitiveFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("SensitiveFilter.doFilter");
        //创建动态代理
        ServletRequest proxyInstance = (ServletRequest)Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter()方法
                if (method.getName().equals("getParameter")) {
                    String value = (String) method.invoke(req, args);
                    System.out.println(value);
                    //判断value 是否包含敏感词汇，包含则替换。
                    if (value!=null){
                        for (String s : list) {
                            value=value.replaceAll(s, "***");
                        }
                    }
                    return value;
                }

                //增强getParameterMap()方法
                if (method.getName().equals("getParameterMap")) {
                    //获取参数列表
                    Map<String,String[]> valueMap = (Map)method.invoke(req, args);
                    //替换已有的参数列表（因为parameterMap返回的对象不可以修改）
                    Map<String,String[]> newValueMap = new HashMap<>();
                    //遍历map,判断value是否包含敏感词汇，包含则替换
                    for (String key : valueMap.keySet()) {
                        String[] values = valueMap.get(key);
                        for (String s : list) {
                            //替换敏感词汇
                            values[0]=values[0].replaceAll(s,"***");
                        }
                        //放入新的map
                        newValueMap.put(key,values);
                    }
                    //返回修改后的map
                    return newValueMap;
                }
                //非getParameter或getParameterMap方法，正常执行。
                return method.invoke(req,args);
            }
        });
        //放行增强后的request
        chain.doFilter(proxyInstance, resp);
    }

    private ArrayList<String> list = new ArrayList<>();//用来存储敏感词汇

    //初始化时加载文件，避免多次加载占用内存
    public void init(FilterConfig config) throws ServletException {
        //获取敏感词汇文件的真是路径
        ServletContext servletContext = config.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new InputStreamReader(new FileInputStream(realPath), StandardCharsets.UTF_8));
            //将敏感词汇存进list
            String line = null;
            while ((line=bf.readLine())!=null) {
                String s = line;
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(realPath);
    }
}
