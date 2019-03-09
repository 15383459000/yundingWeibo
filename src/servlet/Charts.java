package servlet;

import com.google.gson.Gson;
import dao.chartsaction;
import util.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HuMingSen
 */
@WebServlet(name = "Charts")
public class Charts extends HttpServlet {


    /**
     * dopost方法的重写
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType ( "text/html;charset=utf-8" );
        PrintWriter out = response.getWriter ();
        Gson gson = new Gson ();

//todo
        String blogJson = Json.getString ( request );
        Map<String, String> map = new HashMap ();
        map = gson.fromJson ( blogJson, map.getClass () );
        //表示前端传来的动作
        final String action = map.get ( "action" );
        final String greatcharts = "greatcharts";
        final String sharecharts = "sharecharts";
        if (action != null) {
            if (action.equals ( greatcharts )) {
                chartsaction actions = new chartsaction ();

                try {
                    String jsons = gson.toJson ( actions.getgreat () );
                    out.println ( jsons );
                    out.flush ();
                    out.close ();
                }catch (SQLException e) {
                    e.printStackTrace ();
                }catch (ClassNotFoundException e) {
                    e.printStackTrace ();
                }

            }
            if (action.equals ( sharecharts )) {
                chartsaction actions = new chartsaction ();

                try {
                    String jsons = gson.toJson ( actions.getshare () );
                    out.println ( jsons );
                    out.flush ();
                    out.close ();
                }catch (SQLException e) {
                    e.printStackTrace ();
                }catch (ClassNotFoundException e) {
                    e.printStackTrace ();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost ( request, response );
    }
}



