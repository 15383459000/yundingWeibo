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
import java.io.IOException;
import java.io.PrintWriter;
/**
 * 修改个人信息
 *
 * @author SongYuChao
 */
@WebServlet(name = "UsersModify", urlPatterns = "/servlet/UserModify")
public class UsersModify extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet ( request, response );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //设置编码格式
        response.setContentType ( "text/json;charset=UTF-8" );
        response.setCharacterEncoding ( "UTF-8" );
        PrintWriter out = response.getWriter ();

        //获取json
        String usersinformationJson = Json.getString ( request );

        //解析json为users对象
        Gson gson = new Gson ();
        Users u = gson.fromJson ( usersinformationJson, Users.class );
        try {
            u.setImage(((String[]) request.getServletContext().getAttribute("imageList"))[0]);
            request.getServletContext().removeAttribute("imageList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //实例化用户工具
        UsersInformation usersinformationDao = new UsersInformation ();


        usersinformationDao.updateUsersInformation ( u );

    }

}
