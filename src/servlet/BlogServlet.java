package servlet;

import dao.BlogDao;
import com.google.gson.Gson;
import util.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SongYuChao
 */
@WebServlet(name = "BlogServlet", urlPatterns = "/servlet/BlogServlet")
public class BlogServlet extends HttpServlet {
    /**
     * 用表单的方式传入数据
     * action 表示博客的动作
     */

    private BlogDao blogDao = new BlogDao ();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet ( request, response );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        设置编码格式
        response.setContentType ( "text/json;charset=UTF-8" );
        response.setCharacterEncoding ( "UTF-8" );
        request.setCharacterEncoding ( "utf-8" );
        PrintWriter out = response.getWriter ();

//        获取前端数据，保存在map中
        String json = Json.getJson ( request );
        Gson gson = new Gson ();
        Map<String, String> map = new HashMap ();
        map = gson.fromJson ( json, map.getClass () );

//        获取action内容
        final String action = map.get ( "action" );
        if (action != null) {
            final String addBlog = "addBlog";
            final String addGreat = "addGreat";
            final String searchBlogs = "searchBlogs";
            final String searchFavorites = "searchFavorites";
            final String addFavorite = "addFavorite";
            final String delete = "delete";
            final String update = "update";
            if (action.equals ( addBlog )) {
                try {
                    //向服务器请求作者id，作者名称，文章内容的数据来创建文章
                    blogDao.createBlog ( request.getParameter ( "u_id" ), request.getParameter ( "userName" ), request.getParameter ( "content" ) );
                }catch (SQLException e) {
                    e.printStackTrace ();
                }
            }
            if (action.equals ( addGreat )) {
                //向服务器请求文章id来点赞
                blogDao.addGreat ( request.getParameter ( "b_id" ) );
            }
            if (action.equals ( searchBlogs )) {
                //向服务器请求要查询的用户id，返回此用户所有文章
//                json返回的文章对象
                String result = gson.toJson ( blogDao.getAllBlogById ( Integer.valueOf ( request.getParameter ( "u_id" ) ) ) );
                out.println ( result );
                out.flush ();
                out.close ();
            }
            if (action.equals ( searchFavorites )) {
                //向服务器请求要查询的用户id，返回此用户所有收藏的文章
                String result = gson.toJson ( blogDao.getAllFavoriteBlogById ( Integer.valueOf ( request.getParameter ( "u_id" ) ) ) );
                out.println ( result );
                out.flush ();
                out.close ();
            }
            if (action.equals ( addFavorite )) {
                //向服务器请求要添加收藏的用户id和博客id，添加博客id到用户的收藏
                blogDao.addFavorite ( request.getParameter ( "u_id" ), request.getParameter ( "b_id" ) );
            }
            if (action.equals ( delete )) {
                //向服务器请求要删除的用户id，删除文章
                blogDao.deleteBlog ( request.getParameter ( "b_id" ) );
            }
            if (action.equals ( update )) {
                //向服务器请求要更新的博客id和更新内容，并更新数据库
                blogDao.updateBlog ( request.getParameter ( "b_id" ), request.getParameter ( "content" ) );
            }


        }
    }
}
