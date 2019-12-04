package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.CategroyService;
import cn.itcast.travel.service.impl.CategroyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategroyService categroyService = new CategroyServiceImpl();
    /**
     * 查找所有目录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取目录
        response.setContentType("text/html;charset=utf-8");
        List categroyList = categroyService.findAll();
        //封装目录
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(categroyList);
        //返回目录
        response.getWriter().write(json);
    }
}
