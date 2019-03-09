<%--
  Created by IntelliJ IDEA.
  User: guohaodong
  Date: 2/28/19
  Time: 11:12 PM
  To change this template use File | Settings | File Templates..
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            $("#Upload").click(function () {
                // var userEmail = $("#userEmail").val();
                // var password = $("#password").val();

                var testUrl = "servlet/BlogServlet";
                var json =
                    {"action": "addShare", "blog": {"u_id": "19", "id": "4"}};

                $.ajax({
                    "url": testUrl,
                    "type": "post",
                    "data": JSON.stringify(json),
                    "dataType": "json",
                    "success": function (date) {
                        var string = JSON.stringify(date);
                        // alert(string);
                        $("#out").text(string);

                    },
                    "error": function () {
                        alert("connection load failure");
                    }
                });
            })
        })
    </script>
</head>
<body>
<%--<form action="servlet/Upload" enctype="multipart/form-data" method="post">
    上传用户：<input type="text" name="username"><br/>
    上传文件1：<input type="file" name="file1"><br/>
    上传文件2：<input type="file" name="file2"><br/>
    <input type="submit" value="提交" >
</form>--%>
<p>
    <button type="submit" id="Upload">test</button>
    <br><span id="out"></span></p>

</body>
</html>
