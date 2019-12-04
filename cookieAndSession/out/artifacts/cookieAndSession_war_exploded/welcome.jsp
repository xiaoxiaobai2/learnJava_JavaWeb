<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: zhanghao
  Date: 2019/10/30
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <%
        response.setContentType("text/html;charset=utf-8");
        //注意tomcat cookie不支持空格
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss");
        String time = dateFormat.format(new Date(System.currentTimeMillis()));
        time = URLEncoder.encode(time, "utf-8");
        System.out.println(time);
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        if (cookies != null&&cookies.length!=0) {
            System.out.println("非空");
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("welcome")) {
                    flag = true;
                    System.out.println("找到了！");
                    String deTime=URLDecoder.decode(cookie.getValue(),"utf-8");
    %>
    <h1>欢迎回来，您上次访问时间为：<%=deTime%></h1>
    <%
                    cookie.setValue(time);
                    cookie.setMaxAge(60 * 60);
                    //注意修改值之后，需要重新添加
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        if ((cookies==null)||(cookies.length==0)||!flag){
            System.out.println("第一次来");
            Cookie welcome = new Cookie("welcome", time);
            welcome.setMaxAge(60 * 60);
            response.addCookie(welcome);
    %>
            <h1>您好，欢迎您首次访问！</h1>
    <%
        }
    %>
</body>
</html>
