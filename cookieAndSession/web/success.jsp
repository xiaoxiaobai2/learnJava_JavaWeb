<%--
  Created by IntelliJ IDEA.
  User: zhanghao
  Date: 2019/10/30
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆成功</title>
</head>
<body>
<%
    String value=null;
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("username")){
            value=cookie.getValue();
            break;
        }
    }
%>
<h1><%=value%>，欢迎您！</h1>
    <h1><%=session.getAttribute("username")%>，欢迎您！</h1>
</body>
</html>
