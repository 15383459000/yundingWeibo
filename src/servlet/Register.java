package servlet;

import com.google.gson.Gson;
import entity.UserJson;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 注册用户（未通过验证）
 *
 * IN 用户对象的json
 *   password   密码
 *   email  电子邮箱
 *
 * IN 验证码json
 *  code    验证码
 *
 * IN 动作
 *  action  行为 register forgetPwd"
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
     * @param request servlet 参数
     * @param response servlet 参数
     * @throws IOException 缓冲区错误
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //        设置编码格式
        request.setCharacterEncoding ( "utf-8" );
        response.setCharacterEncoding ( "utf-8" );
        response.setContentType ( "text/html" );
//        获取输出（printWriter）
        PrintWriter out = response.getWriter ();

//          实例化用户工具
        UserUtil userUtil = new UserUtil ();

//        获取json
        String json = Json.getJson (request);

//        获取正确的验证码,清除原来的验证码
        String identify = request.getSession ().getAttribute("identify").toString ();

        Gson gson = new Gson ();
        UserJson userJson = gson.fromJson ( json, UserJson.class );
        String code = userJson.getCode ();
        String action = userJson.getAction ();

        if(!code.equals ( identify )){
//            验证码错误 -1
            out.print ( "-1" );
        }
        else{
//            作废已使用的验证码
            request.getSession ().removeAttribute ( "identify" );

//            验证成功，在数据库中添加用户
//            创建用户对象
            Users user = userJson.getUser ();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd" );
            user.setRegisterTime ( simpleDateFormat.format ( new Date (  ) ) );
            try {
//                action "register" 注册
//                修改密码
                final String  register = "register";
                if(action.equals ( register )){
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
