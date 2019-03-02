<%--
  Created by IntelliJ IDEA.
  User: guohaodong
  Date: 2/19/19
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>LogIn</title>
    <%--导入js文件--%>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <%--为文本框绑定事件--%>
    <%--<script>--%>
      <%--$(function(){--%>
        <%--//焦点失去事件--%>
        <%--$("#userEmail").blur(function () {--%>

          <%--var userEmail = $(this).val();--%>
          <%--var test = "test"--%>
            <%--$("#userShow").text("");--%>
          <%--if(userEmail==null || ""=== userEmail){--%>
            <%--$("#userShow").text("username can\'t be blank");--%>
            <%--$("#userShow").css("background-color","green");--%>
          <%--}else{--%>
          <%--//  使用ajax jQuery--%>
            <%--$.ajax({--%>
              <%--"url": "servlet/IsRegister",--%>
              <%--"type":"post",--%>
              <%--"data" : "test="+test,--%>
              <%--"dataType":"text",--%>
              <%--"success":function(isRegister){--%>
                  <%--alert("connection to server success");--%>
                  <%--alert(isRegister);--%>
                  <%--var data = JSON.parse(isRegister);--%>
                  <%--alert(data);--%>
              <%--},--%>
              <%--"error":function () {--%>
                <%--alert("connection load failure");--%>
              <%--}--%>
            <%--});--%>
          <%--}--%>
        <%--});--%>
      <%--});--%>
    <%--</script>--%>
    <script>
      $(function () {
      $("#userEmail").blur(function () {
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
  <div>
  </div>
  <form action="servlet/IsLogIn" method="post">
    <table>
      <tr>
        <td>userEmail:</td>
        <td><input type="text" name="userEmail" id="userEmail"/></td>
        <td><span id="userShow"></span></td>
      </tr><tr>
        <td>password:</td>
        <td><input type="password" name="password" id="password"/></td>
      </tr>
      <tr>
        <td colspan="" >
          <input type="submit" value="submit" id="submit"/>
        </td>
      </tr>
    </table>
  </form>
  <br>
  <a href="register.jsp">register</a>
  <%--<%response.sendRedirect("register.jsp");%>--%>
  </body>
</html>
