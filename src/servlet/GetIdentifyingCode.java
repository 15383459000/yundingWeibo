package servlet;

import util.Mailet;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "GetIdentifyingCode",urlPatterns = "/servlet/GetIdentifyingCode")
public class GetIdentifyingCode extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        设置编码格式
        request.setCharacterEncoding ( "utf-8" );
        response.setContentType ( "html/text" );
        response.setCharacterEncoding ( "utf-8" );
//        获取用户邮箱

//        test Code
        /*BufferedReader br = new BufferedReader(new InputStreamReader (request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }*/

        String email = request.getParameter ( "email" );
//        假数据
//        String email = "1079407476@qq.com";
//        发送验证码
        Mailet mailet = new Mailet();
        String identify = null;
        try {
            identify = mailet.sendIdentifyingCode ( email );
        }catch (MessagingException e) {
            e.printStackTrace ();
        }finally {
//            将验证码存储到session对象中
            HttpSession session = request.getSession ();
            session.setAttribute ( "identify", identify );
        }

    }

}
