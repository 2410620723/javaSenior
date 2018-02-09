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
<p>Part 图片上传</p>
<img src="${imgSrc}">
<form id="partServlet" action="/partServlet" method="post" enctype="multipart/form-data">
    <input type="file" name="partFile" onchange="checkPicture(this.name)"><br>
    <input type="submit" value="提交">
</form>
<hr>
<div>
    <form id="fileUploadServlet" action="/fileUploadServlet" method="post" enctype="multipart/form-data">
        <input type="file" name="fileUpload" onchange="checkPicture(this.name)"><br>
        <input type="submit" value="提交">
        <img src="" id="fileUploadImg">
    </form>
</div>
<hr>
<div>
    <p>spring mvc multipartFile 上传图片</p>
    <form id="springUploadServlet" action="/springUpload" method="post" enctype="multipart/form-data" target="springUploadFrame">
        <input type="file" name="springUpload" onchange="checkPicture(this.name)"><br>
        <input type="submit" value="提交">
        <iframe name="springUploadFrame" style="display: none;"></iframe>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script>
    function checkPicture(name) {
        var filename = $("input[name='" + name + "']").val();
        if (filename) {
            var index = filename.lastIndexOf(".");
            var suffix = filename.substring(index+1).toUpperCase();
            if (suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF") {
                alert("不支持该图片格式，请重新选择");
                $("input[name='" + name + "']").val("");
            }
            console.log(suffix);
        } else {
            alert("图片不存在");
            return false;
        }
    }
</script>
</body>
</html>
