package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import entity.Users;
import util.UserUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 注册用户（未通过验证）
 *
 * IN 用户对象的json
 *   userName   用户名
 *   password   密码
 *   email  电子邮箱
 *
 * IN 验证码json
 *  identify    验证码
 *
 * IN 动作
 *  action  行为
 *
 * OUT 状态码
 *   -1 验证码错误
 *   0  内部错误
 *   1  成功
 *
 */
@WebServlet(name = "Register",urlPatterns = "/servlet/Register")
public class Register extends HttpServlet {
    /**
     * 1. 设置编码格式
     * 2. 获取输出对象
     * 3. 实例化 UserUtil
     * 4. 获取数据
     *  4.1 获取用户
     *  4.2 获取验证码
     *  4.3 获取动作
     * 5. 验证验证码
     *  5.1 成功 将用户写入数据库
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        设置编码格式
        request.setCharacterEncoding ( "utf-8" );
        response.setCharacterEncoding ( "utf-8" );
        response.setContentType ( "text/html" );
//        获取输出（printWriter）
        PrintWriter out = response.getWriter ();

//          实例化用户工具
        UserUtil userUtil = new UserUtil ();

//        假数据
        String json = "{userName:\"guohaodong\",password:\"wenzhu27\",email:\"1234567@qq.com\"}";
        String jsonArray = "[code:\"000000\" , action:\"d\"]";

//        获取正确的验证码
        String identify = request.getSession ().getAttribute("identify").toString ();

//        code 待确认的验证码
        String code = request.getParameter ( "identify" );
//        获取行为
        String action = "";

        Type dataType = new TypeToken<List<String>>(){}.getType ();
        Gson gson = new Gson();
//        创建json解析器
        JsonParser jsonParser = new JsonParser ();
//        创建json数组
        JsonArray jsonElements = jsonParser.parse ( jsonArray ).getAsJsonArray ();


//        TODO  获取前端json

//        TODO code 数据

//
        if(!code.equals ( identify )){
//            验证失败 -1
            out.print ( "-1" );
        }
        else{
//            验证成功，在数据库中添加用户
            Users user = gson.fromJson ( json,Users.class );
            String test = gson.toJson ( user );
//        user = gson.fromJson ( json,Users.class );
            try {
//                action "register" 注册
//                修改密码
                String R = "register";
                if(action.equals ( R )){
                    userUtil.addUser ( user );
                }
                else{
                    userUtil.modifyUser ( user );
                }
            }catch (SQLException | ClassNotFoundException ignore) {
//                出现错误返回 0
                out.print ( "0" );
            }
            request.getSession ().removeAttribute ( "identify" );
//            成功返回 1
            out.print ( "1" );
        }

    }

}
