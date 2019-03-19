package servlet;

import com.google.gson.Gson;
import entity.Users;
import util.Json;
import dao.UserUtil;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * 判断是否登录，登陆成功后将用户 id 存入session中
 * url  servlet/IsLogIn
 * 用户邮箱   email
 * 密码   password
 *
 * 返回 status:"value"
 * value = -1 没有此用户，0登录失败，1登陆成功
 *
 * @author guohaodong
 */
@WebServlet(name = "IsLogIn", urlPatterns = "/servlet/IsLogIn")
public class IsLogIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost ( req, resp );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
//        设置编码格式
        request.setCharacterEncoding ( "utf-8" );
        response.setCharacterEncoding ( "utf-8" );
        response.setContentType ( "text" );

//        实例化用户工具
        UserUtil userUtil = new UserUtil ();
//        获取json
        String json = Json.getString ( request );

//        解析json为users对象
        Gson gson = new Gson ();
        Users user = gson.fromJson ( json, Users.class );

//        创建json 表示log in状态
        StringBuilder result = new StringBuilder ( "{\"status\":" );
        try {
//            u 从数据库根据email查询到的user
            Users u = userUtil.getUsersByEmail ( user.getEmail () );

//             当数据库有此用户，判断是否登录成功
            if (userUtil.getUsersByEmail ( user.getEmail () ).getPassword ().equals ( u.getPassword () )) {
//                密码正确
                result.append ( "\"1\",\"userId\":\"" ).append ( u.getId () ).append ( "\"}" );
            } else {
//                密码错误
                result.append ( "\"0\"}" );
            }
        }catch (SQLException | ClassNotFoundException | NullPointerException ignore) {
//            内部错误
            result.append ( "\"-1\"}" );
        }
//        输出value
        PrintWriter out = response.getWriter ();
        out.print ( result.toString () );
        out.flush ();
        out.close ();
    }

}
