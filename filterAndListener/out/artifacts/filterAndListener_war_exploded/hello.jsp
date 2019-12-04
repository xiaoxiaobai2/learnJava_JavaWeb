<%--
  Created by IntelliJ IDEA.
  User: zhanghao
  Date: 2019/11/5
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/loginServlet">
        <input type="text" name="username">
        <input type="text" name="id">
        <input type="text" name="hh">
        <input type="password" name="password">

        <input type="submit" value="提交">
    </form>
</body>
</html>
