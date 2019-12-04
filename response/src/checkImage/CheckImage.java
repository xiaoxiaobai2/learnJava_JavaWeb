package checkImage;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkImage")
public class CheckImage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("??????");
        int width = 1000;
        int height = 500;
        //1、生成一张图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //2、美化图片
        //2.1 获取画笔
        Graphics graphics = image.getGraphics();

        //设置背景色
        graphics.setColor(Color.pink);
        graphics.fillRect(0,0,width,height);

        //设置边框
        graphics.setColor(Color.blue);
        graphics.drawRect(0,0,width-1,height-1);

        //写字
        String s="ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        for (int i = 1 ; i <=4; i++) {
            int index=random.nextInt(s.length());
            graphics.drawString(s.charAt(index)+"",i*width/5,height/2);
        }

        //画线
        graphics.setColor(Color.green);
        for (int i = 0; i < 10; i++) {
            int x1=random.nextInt(width);
            int x2=random.nextInt(width);
            int y1=random.nextInt(height);
            int y2=random.nextInt(height);
            graphics.drawLine(x1,y1,x2,y2);
        }


        //3、展示图片
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
