<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.User" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
</head>
<style>
    .ji{
        color: aqua;
    }
    .ou{
        color: burlywood;
    }
</style>
<body>
    <%
        ArrayList<Object> list = new ArrayList<>();
        list.add(new User("张三",23,new Date()));
        list.add(new User("李四",24,new Date()));
        list.add(new User("王五",25,new Date()));

        request.setAttribute("list",list);
    %>
    <table align="center" border="1px">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>生日</th>
        </tr>
        <%--数据行--%>
        <c:forEach items="${list}" var="user" varStatus="s">

            <c:if test="${s.count % 2 != 0}">

                <tr class="ji">
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.birday}</td>
                </tr>
            </c:if>

            <c:if test="${s.count % 2 == 0}">

                <tr  class="ou">
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.birday}</td>
                </tr>
            </c:if>
        </c:forEach>

    </table>
</body>
</html>

CREATE TABLE `user` (
`id` int(10) NOT NULL AUTO_INCREMENT,
`name` varchar(255) DEFAULT NULL,
`gender` varchar(255) DEFAULT NULL,
`address` varchar(255) DEFAULT NULL,
`qq` varchar(255) DEFAULT NULL,
`email` varchar(255) DEFAULT NULL,
`username` varchar(255) DEFAULT NULL,
`password` varchar(255) DEFAULT NULL,
`age` int(10) DEFAULT NULL,
PRIMARY KEY (`id`)
)
