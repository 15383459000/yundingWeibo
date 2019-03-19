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

                var testUrl = "servlet/Comment";
                var json =
                    {
                        action: "addComment",
                        comment: {
                            "u_id": "3",       //添加评论: 用户的id:u_id , 用户的姓名:userName , 微博的id:id , 想要添加的评论:comment .
                            "userName": "顺风车",
                            "id": "2",
                            "comment": "双方三四次"
                        }
                        // "id":"1"      //根据微博名查询评论: 微博表的id
                    };
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
