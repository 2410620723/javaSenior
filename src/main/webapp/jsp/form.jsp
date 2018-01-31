<%--
  Created by IntelliJ IDEA.
  User: zxm
  Date: 2018-1-26
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
</head>
<body>
<form action="doFormServlet" method="post">
    <input type="hidden" name="token" value="${sessionScope.token}">
    用户名：<input name="username">
    <br>
    <input type="submit" value="提交">
</form>
</body>
</html>
