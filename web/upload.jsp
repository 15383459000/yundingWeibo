<%--
  Created by IntelliJ IDEA.
  User: guohaodong
  Date: 3/7/19
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>uplooad</title>
</head>
<body>
<form action="servlet/test" method="post" enctype="multipart/form-data">
    <input type="file" name="file1">
    <input type="submit" value="test">
</form>
<hr>
<form action="servlet/test" enctype="multipart/form-data" method="post">
    上传用户：<input type="text" name="username"><br/>
    上传文件1：<input type="file" name="file1"><br/>
    上传文件2：<input type="file" name="file2"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
