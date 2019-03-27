package servlet;

import com.google.gson.Gson;
import dao.BlogDao;
import entity.BlogS;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author HuMingSen
 */
@WebServlet(name = "ChartsTime", urlPatterns = "/servlet/ChartsTime")
public class ChartsTime extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        设置编码格式
        response.setContentType ( "text/html;charset=utf-8" );
        PrintWriter out = response.getWriter ();
        BlogDao blogDao = new BlogDao ();
        Gson gson = new Gson ();
        BlogS blogS = new BlogS();

        try {
            blogS.setBlogList(blogDao.getCharts("time"));
            String json = gson.toJson(blogS);
            out.print ( json );
        }catch (SQLException | ClassNotFoundException e) {
            //出现数据库异常返回-1
            out.print ( "\"status\":\"-1\"" );
        }
        out.flush ();
        out.close ();
    }

}
