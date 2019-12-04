import utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取 servletContext对象
        ServletContext servletContext = this.getServletContext();
        //获取文件名
        String filename = request.getParameter("filename");

        //设置响应头类型
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type",mimeType);

        String realPath = servletContext.getRealPath("/" + filename);

        //解决中文乱码
        String userAgent=request.getHeader("user-agent");
        filename = DownLoadUtils.getFileName(userAgent, filename);

        //设置响应头打开方式
        response.setHeader("content-disposition","attachment;filename="+filename);


        File file=new File(realPath);
        InputStream inputStream=new FileInputStream(file);
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] car=new byte[1024];
        int len=0;
        while (-1!=(len=inputStream.read(car))){
            outputStream.write(car,0,len);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
