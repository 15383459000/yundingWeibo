package servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonString;
import entity.Users;
import util.DButil;
import util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * name : IsRegister
 * param : userEmail
 * submissionMode : past
 * return : if the username have been registered "flag:true" else "flag:false"
 */
@WebServlet(name = "IsRegister",urlPatterns = "/servlet/IsRegister")
public class IsRegister extends HttpServlet {
    /**
     * 用户名
     */
    private static final String EMAIL = "email";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding ( "utf-8" );
        response.setContentType ( "text/html" );
        response.setCharacterEncoding ( "utf-8" );
//        获取表单中的数据
//        useName 用户名
        String email = request.getParameter ( "userEmail" );
//        todo
        String test = request.getParameter ( "test" );
        String flag = "false";
//         在数据库中查找
//        有此用户返回true，否则返回false
        UserUtil userUtil = new UserUtil ();
        try {
            if(userUtil.getUsersByEmail ( email )!=null){
                flag = "true";
            }
        }catch (SQLException | ClassNotFoundException ignored) {
        }

        //        输出到ajax
        PrintWriter out = response.getWriter ();
        out.print("flag"+":"+"\""+flag+"\"");
        out.flush ();
        out.close ();
    }


}
