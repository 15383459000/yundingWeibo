package servlet;

import com.google.gson.Gson;
import util.Json;
import util.Mailet;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guohaodong
 */
@WebServlet(name = "GetIdentifyingCode",urlPatterns = "/servlet/GetIdentifyingCode")
public class GetIdentifyingCode extends HttpServlet {
    /**
     * 获取email发送验证码并将验证码返回
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        设置编码格式
        request.setCharacterEncoding ( "utf-8" );
        response.setContentType ( "json" );
        response.setCharacterEncoding ( "utf-8" );
//        获取用户邮箱

        String json = Json.getString ( request );
        Gson gson = new Gson ();
        Map map = new HashMap<> ( 1 );
        map = gson.fromJson ( json, map.getClass ());

        String email = map.get ( "email" ).toString ();
//        发送验证码
        Mailet mailet = new Mailet();
        String identify = null;
        try {
            identify = mailet.sendIdentifyingCode ( email );
        }catch (MessagingException e) {
            e.printStackTrace ();
        }finally {
//            将验证码传输到前端
            ServletContext servletContext = request.getServletContext();
            servletContext.setAttribute("identify", identify);
        }

    }

}
