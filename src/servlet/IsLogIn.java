package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.mysql.cj.xdevapi.Client;
import entity.Users;
import util.DButil;
import util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

/**判断用户名和密码有一项为空跳转到注册页面，否则跳转到登录失败或登录成功页面
 *用户邮箱   userEmail
 * 密码   password
 *
 * 返回 status:"value"
 * value = -1 没有此用户，0登录失败，1登陆成功
 */
@WebServlet(name = "IsLogIn" ,urlPatterns="/servlet/IsLogIn")
public class IsLogIn extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        设置编码格式
        request.setCharacterEncoding ( "utf-8" );
        response.setCharacterEncoding ( "utf-8" );
        response.setContentType ( "text" );

//        实例化用户工具
        UserUtil userUtil = new UserUtil ();

//        假数据
//        String json = "{userName:\"guohaodong\",password:\"wenzhu27\",email:\"1234567@qq.com\"}";
//        TODO 获取前端json

        //        test Code

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    request.getInputStream(), StandardCharsets.UTF_8 ));

            StringBuffer sb = new StringBuffer();
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();

        String json = URLDecoder.decode ( sb.toString (),"utf-8" );

//        解析json为users对象
        Gson gson = new Gson();
//        json = gson.toJson ( json );
//        String json = request.getParameter ( "email" );
//        String json2 = request.getParameter ( "user" );

        Users user = gson.fromJson ( json,Users.class );

//        创建json 表示状态
        StringBuilder result = new StringBuilder("status:");
        try {
//            u 从数据库根据email查询到的user
            Users u = userUtil.getUsersByEmail ( user.getEmail () );

//             当数据库有此用户，判断是否登录成功
            if(userUtil.getUsersByEmail ( user.getEmail () ).getPassword ().equals(u.getPassword ())){
//                密码正确
                result.append ( "\"1\"" );
                request.getSession().setAttribute ( "user",u );
            }
            else{
//                密码错误
                result.append ( "\"0\"" );
            }
        }catch (SQLException | ClassNotFoundException ignore) {

        }catch (NullPointerException ignore){
//            user = null 用户不存在时 输出 -1
            result.append ( "\"-1\"" );
        }

//        输出value
        PrintWriter out = response.getWriter ();
        out.print(result.toString ());
        out.flush ();
        out.close ();
    }

}
