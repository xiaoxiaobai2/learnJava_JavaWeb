<%--
  Created by IntelliJ IDEA.
  User: zhanghao
  Date: 2019/10/30
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<script>
    window.onload=function () {
        document.getElementById("checkImg").onclick = function () {

            this.src = "/cookie/checkImage?date=" + new Date().getTime();
        }
    }
</script>
<style>
    p{
        color: red;
    }
</style>
<body>
    <form action="/cookie/loginServlet" method="get">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td><input type="text" name="checkCode"></td>
            </tr>
            <tr>
                <td colspan="2"><img src="/cookie/checkImage" id="checkImg"></td>
            </tr>
            <tr>
                <td><input type="submit" name="submit" value="登陆"></td>
            </tr>
        </table>
        <p>
            <%if (request.getAttribute("username_error")!=null){
            out.print(request.getAttribute("username_error"));
        }%>
        </p>

        <p>
            <%if (request.getAttribute("cc_error")!=null){
            out.print(request.getAttribute("cc_error"));
        }%>
        </p>
    </form>
</body>
</html>
