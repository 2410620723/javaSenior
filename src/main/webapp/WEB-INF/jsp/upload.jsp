<%--
  Created by IntelliJ IDEA.
  User: zxm
  Date: 2018-2-7
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片上传</title>
</head>
<body>
<div>
    <p>spring mvc multipartFile 组件方式上传图片</p>
    <form method="post" action="/hello" enctype="multipart/form-data">
        <input type="file" name="file"><input type="submit">
    </form>
</div>
<hr>
<div>
    <p>spring mvc multipartFile 流的方式上传图片</p>
    <form method="post" action="/springIoUpload" enctype="multipart/form-data">
        <input type="file" name="ioFile"><input type="submit">
    </form>
</div>
</body>
</html>
