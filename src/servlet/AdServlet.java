package servlet;

import com.google.gson.Gson;
import dao.Ad;
import entity.AdEntity;
import entity.Blog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "AdServlet", urlPatterns = "/servlet/AdServlet")
public class AdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        try {
            AdEntity ad = Ad.getAd();
            Gson gson = new Gson();
            String json = gson.toJson(ad);
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
