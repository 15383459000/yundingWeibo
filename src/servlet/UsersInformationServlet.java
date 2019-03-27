package servlet;

import com.google.gson.Gson;
import dao.UsersInformation;
import entity.Users;
import util.Json;

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

        //男性女性的默认照片
        String MALE = "/SimpleWeibo_war_exploded/image/male.jpeg";
        String FEMALE = "/SimpleWeibo_war_exploded/image/female.jpeg";
        String DEFAULT = "/SimpleWeibo_war_exploded/image/default.jpg";
        //设置编码格式
        response.setContentType ( "text/json;charset=UTF-8" );
        response.setCharacterEncoding ( "UTF-8" );
        PrintWriter out = response.getWriter ();

        //获取json
        String usersinformationJson = Json.getString(request);

        //解析json为users对象
        Gson gson = new Gson ();
        Users u = gson.fromJson ( usersinformationJson, Users.class );
        u = usersinformationDao.get(String.valueOf(u.getId()));
        if (u.getSex() != null) {
            switch (u.getSex()) {
                case "男": {
                    u.setImage(MALE);
                    break;
                }
                case "女": {
                    u.setImage(FEMALE);
                    break;
                }
                default: {
                    u.setImage(DEFAULT);
                }
            }
        }
        String json = gson.toJson(u);
        System.out.println ( json );
        out.println ( json );
        out.flush ();
        out.close ();

    }

}
