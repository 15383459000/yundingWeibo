package servlet;

import com.google.gson.Gson;
import dao.UsersInformation;
import entity.Users;
import javax.servlet.ServletException;
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

/**
 * 通过用户id获取用户信息
 */
@WebServlet(name = "UsersInformationServlet", urlPatterns = "/servlet/UserInformationServlet")
public class UsersInformationServlet extends HttpServlet {

    UsersInformation usersinformationDao = new UsersInformation ();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet ( request, response );

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码格式
        response.setContentType ( "text/json;charset=UTF-8" );
        response.setCharacterEncoding ( "UTF-8" );
        PrintWriter out = response.getWriter ();

        //获取json
        String usersinformationJson = getJson ( request );

        //解析json为users对象
        Gson gson = new Gson ();
        Users u = gson.fromJson ( usersinformationJson, Users.class );

        String json = gson.toJson ( usersinformationDao.get ( String.valueOf ( u.getId () ) ) );
        System.out.println ( json );
        out.println ( json );
        out.flush ();
        out.close ();

    }

    private static String getJson(HttpServletRequest request) throws IOException {
        //        获取json
        BufferedReader br = new BufferedReader ( new InputStreamReader ( request.getInputStream (), StandardCharsets.UTF_8 ) );
        StringBuilder sb = new StringBuilder ();
        String temp;
        while ((temp = br.readLine ()) != null) {
            sb.append ( temp );
        }
        br.close ();
        return URLDecoder.decode ( sb.toString (), StandardCharsets.UTF_8 );
    }
}
