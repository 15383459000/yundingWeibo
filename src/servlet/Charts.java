package servlet;

import com.google.gson.Gson;
import dao.BlogDao;
import entity.BlogS;
import util.Json;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;


/**
 * @author HuMingSen
 */
@WebServlet(name = "Charts", urlPatterns = "/servlet/Charts")
public class Charts extends HttpServlet {


    /**
     * doPost方法的重写
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType ( "text/html;charset=utf-8" );
        PrintWriter out = response.getWriter ();
        Gson gson = new Gson ();
        BlogS blogS = Json.getBlogs ( request );

        //表示前端传来的动作 great 点赞 ,share分享
        String action = blogS.getAction ();
        BlogDao blogDao = new BlogDao ();
        if (action != null) {
                try {
                    String jsons = gson.toJson ( blogDao.getCharts ( action ) );
                    out.println ( jsons );
                    out.flush ();
                    out.close ();
                }catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace ();
                }
        }
    }

}



