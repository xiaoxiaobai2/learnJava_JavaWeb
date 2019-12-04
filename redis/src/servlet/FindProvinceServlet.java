package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Province;
import service.impl.ProvinceServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findProvinceServlet")
public class FindProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("html/text;charset=utf-8");
        ProvinceServiceImp provinceServiceImp = new ProvinceServiceImp();
        String value = provinceServiceImp.findAllByRedis();
        response.getWriter().write(value);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
