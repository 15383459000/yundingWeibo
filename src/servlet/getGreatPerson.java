package servlet;

import com.google.gson.Gson;
import dao.BlogDao;
import entity.Blog;
import entity.Users;
import util.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "getGreatPerson", urlPatterns = "/servlet/getGreatPerson")
public class getGreatPerson extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //todo getGreatPerson
        String json = Json.getString(request);
        Gson gson = new Gson();
        Users user = gson.fromJson(json, Users.class);

        List<Blog> blogList = null;
        try {
            blogList = (new BlogDao()).getAllBlogById(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        for (Blog blog :
                blogList) {
            List<String> personList = Arrays.asList(blog.getGreatPerson().split("#"));
            String result = gson.toJson(personList);
            out.print(result);
            out.flush();
            out.close();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
