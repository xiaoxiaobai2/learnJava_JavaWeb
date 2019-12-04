package cn.itcast.travel.web.servlet;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class BaseServlet extends HttpServlet {
    /**
     * 完成方法的分发
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取url
        String uri = req.getRequestURI();
        System.out.println("uri = " + uri);
        //2、获取方法名
        String methodName = uri.substring(uri.lastIndexOf('/')+1);
        System.out.println("methodName = " + methodName);
        //3、通过反射获取方法
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //4、执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把对象封装成 json格式
     * @param o
     * @return
     * @throws JsonProcessingException
     */
    public String writeValueAsString(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(o);
        return json;
    }

    /**
     * 把数据封装成 json 格式 并输出到字节流
     * @param o
     * @param response
     * @throws IOException
     */
    public void writeValue(Object o,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),o);
    }
}
