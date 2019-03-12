package servlet;

import com.google.gson.Gson;
import dao.BlogDao;

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
        response.setContentType ( "text/html;charset=utf-8" );
        PrintWriter out = response.getWriter ();
        BlogDao blogDao = new BlogDao ();
        Gson gson = new Gson ();

        try {
            String json = gson.toJson ( blogDao.getCharts ( "time" ) );
            out.print ( json );
        }catch (SQLException | ClassNotFoundException e) {
            out.print ( "\"status\":\"-1\"" );
        }
        out.flush ();
        out.close ();
    }

}
