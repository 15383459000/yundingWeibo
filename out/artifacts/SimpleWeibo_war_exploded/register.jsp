<%--
  Created by IntelliJ IDEA.
  User: guohaodong
  Date: 2/27/19
  Time: 10:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script >
        $.fn.serializeObject = function () {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function () {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        }
        }
    </script>
</head>
<body>
<div>
        <form action="servlet/Register" method="post">
            <table>
                <tr>
                    <td>userName</td>
                    <td><input type="text" name="userName"/></td>
                </tr>
                <tr>
                    <td>password</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td>identify</td>
                    <td><input type="text" name="identify"/></td>
                </tr>
                <tr>
                    <td colspan>
                        <input type="submit" value="submit"/>
                    </td>
                </tr>
            </table>
        </form>
    <form action="servlet/GetIdentifyingCode" method="post">
        email:<input type="text" name="email"/>
        <input type="submit" value="getCode"/>
    </form>
</div>
</body>
</html>
