<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhanghao
  Date: 2019/10/30
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>container</title>
</head>
<body>
    <%
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(1);
        integers.add(1);
        integers.add(1);
        integers.add(1);
        integers.add(1);
        integers.add(1);
        request.setAttribute("list",integers);
    %>
    <c:forEach begin="0" end="10" var="i" step="1">
        ${i}
    </c:forEach>
    <h1>便利list</h1>
    <c:forEach items="${list}" var="list" step="2" varStatus="s">
        ${list}   ${s.count }
    </c:forEach>
</body>
</html>
