<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
        .add_btn{
            float: right;
            margin-left: 20px;
            margin-bottom: 20px;
        }
        .search_form{
            float: left;
        }
        /*显示页的字体大小*/
        .record{
            margin: 10px;
            text-align: center;
            font-size: 24px;
        }
    </style>
    <script>
        function deleteUser(id) {
            if (confirm("你确定要删除吗？")){
                location.href = "${pageContext.request.contextPath}/delUserServlet?id=" + id;
            }
        }
        function deleteSelected() {
            var uids = document.getElementsByName("uid");
            var flag=false;
            //判断是否有被选中的项
            for (var i = 0; i < uids.length; i++) {
                if (uids[i].checked==true){
                    flag=true;
                    break;
                }
            }

            if (flag){
                if (confirm("你确定要删除选中项吗？")){
                    document.getElementById("inf_form").submit();
                }
            } else {
                alert("没有任何项被选中！")
            }
        }
        function selected_all() {
            var flag = document.getElementById("all_checked").checked;
            var uids = document.getElementsByName("uid");
            for (var i = 0; i < uids.length; i++) {
                uids[i].checked = flag;
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
<%--    条件查询表单--%>
    <form class="form-inline search_form" action="${pageContext.request.contextPath}/findUserByPageServlet">
        <div class="form-group">
            <label for="name">姓名</label>
            <input type="text" class="form-control" id="name" name="name" value="${condition.name[0]}">
        </div>
        <div class="form-group">
            <label for="jiguan">籍贯</label>
            <input type="text" class="form-control" id="jiguan" name="address" value="${condition.address[0]}">
        </div>
        <div class="form-group">
            <label for="email">邮箱</label>
            <input type="text" class="form-control" id="email" name="email" value="${condition.email[0]}">
        </div>
        <button type="submit" class="btn btn-default">搜索</button>
    </form>

    <a class="btn btn-primary add_btn" href="add.jsp">添加联系人</a>
    <a class="btn btn-primary add_btn" onclick="deleteSelected()">删除联系人</a>


    <form id="inf_form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" onclick="selected_all()" id="all_checked"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" id="checkbox" name="uid" value="${user.id}"></td>
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}" title="${user.id}">修改</a>&nbsp
                        <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">删除</a></td>
                </tr>

            </c:forEach>
        </table>
    </form>
    <nav aria-label="Page navigation">
<%--        显示上一页--%>
        <ul class="pagination pagination-lg">
            <c:if test="${1 == pb.currentPage}">
            <li class="disabled">
            </c:if>
            <c:if test="${1 != pb.currentPage}">
            <li>
            </c:if>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage-1}&name=${condition.name[0]}&address${condition.address[0]}&email${condition.email[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
            </li>

<%--                显示 1 2 3 4 5 --%>
            <c:forEach begin="1" step="1" end="${pb.totalPage}" var="i">
                <c:if test="${pb.currentPage == i}">
                    <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&name=${condition.name[0]}&address${condition.address[0]}&email${condition.email[0]}">${i}</a></li>
                </c:if>
                <c:if test="${pb.currentPage != i}">
                    <li ><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&name=${condition.name[0]}&address${condition.address[0]}&email${condition.email[0]}">${i}</a></li>
                </c:if>
            </c:forEach>

<%--                显示下一页--%>
            <c:if test="${pb.totalPage == pb.currentPage}">
            <li class="disabled">
            </c:if>
            <c:if test="${pb.totalPage != pb.currentPage}">
            <li>
            </c:if>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage+1}&name=${condition.name[0]}&address${condition.address[0]}&email${condition.email[0]}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <span class="record">
                共${pb.totalCount}条记录，${pb.totalPage}页
            </span>
        </ul>
    </nav>
</div>

</body>
</html>
