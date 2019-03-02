<%--
  Created by IntelliJ IDEA.
  User: guohaodong
  Date: 2/28/19
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            $("#test").on(function () {
                var userEmail = $("#userEmail").val();
                var password = $("#password").val();
                var json = {'email':userEmail,"password":password,user:{name:"f",password:"goo"}};
                $.ajax({
                    "url": "servlet/IsLogIn",
                    "type":"post",
                    "data" : JSON.stringify(json),
                    "dataType":"json",
                    "success":function(isRegister){
                        alert("connection to server success");
                        alert(isRegister);
                        var data = JSON.parse(isRegister);
                        alert(data);
                    },
                    "error":function () {
                        alert("connection load failure");
                    }
                });
            })
        })
    </script>
</head>
<body>

    <button type="button" id="test">test</button>


</body>
</html>
