package servlet;

import com.google.gson.Gson;
import dao.BlogDao;
import entity.Blog;
import entity.BlogS;
import util.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SongYuChao
 */
@WebServlet(name = "BlogServlet", urlPatterns = "/servlet/BlogServlet")
public class BlogServlet extends HttpServlet {
    private String action;
    //表示博客的动作 ,add,search,delete,update
    //博客业务逻辑类的对象
    private BlogDao blogDao = new BlogDao ();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet ( request, response );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        更改编码格式
        response.setContentType ( "text/json;charset=UTF-8" );
        response.setCharacterEncoding ( "UTF-8" );
        PrintWriter out = response.getWriter ();
        Gson gson = new Gson ();

        String blogJson = Json.getString ( request );
//        json --> BlogS
        BlogS blogS = gson.fromJson ( blogJson, BlogS.class );
        if (blogS.getAction () != null) {
            this.action = blogS.getAction ();
            if (action.equals ( "addBlog" )) {
                blogDao.addBlog ( blogS.getBlog () );
            }
            if (action.equals ( "addGreat" )) {
                //向服务器请求文章id来点赞
                blogDao.addGreat ( String.valueOf ( blogS.getBlog ().getId () ) );

            }
            if (action.equals ( "searchBlogs" )) {
                //向服务器请求要查询的用户id，返回此用户所有文章
                //List<Blog> list =new ArrayList();
                String json = gson.toJson ( blogDao.getAllBlogById ( blogS.getBlog ().getU_id () ) );
                System.out.println ( json );
                out.println ( json );
                out.flush ();
                out.close ();
            }
            if (action.equals ( "searchFavorites" )) {
                //向服务器请求要查询的用户id，返回此用户所有收藏的文章
                String json = gson.toJson ( blogDao.getAllFavoriteBlogById ( blogS.getBlog ().getU_id () ) );
                out.println ( json );
                out.flush ();
                out.close ();
            }
            if (action.equals ( "addShare" )) {
                //给用户添加转发的文章
                blogDao.addSharedBlog ( String.valueOf ( blogS.getBlog ().getU_id () ), String.valueOf ( blogS.getBlog ().getId () ) );

            }
            if (action.equals ( "searchSharedBlog" )) {
                //向服务器请求要查询的用户id，返回此用户所有分享的文章
                String json = gson.toJson ( blogDao.getAllShareBlogById ( blogS.getBlog ().getU_id () ) );
                System.out.println ( json );
                out.println ( json );
                out.flush ();
                out.close ();
            }
            if (action.equals ( "addFavorite" )) {
                //向服务器请求要添加收藏的用户id和博客id，添加博客id到用户的收藏
                blogDao.addFavorite ( String.valueOf ( blogS.getBlog ().getU_id () ), String.valueOf ( blogS.getBlog ().getId () ) );

            }
            if (action.equals ( "delete" )) {
                //向服务器请求要删除的文章id，删除文章
                blogDao.deleteBlog ( String.valueOf ( blogS.getBlog ().getId () ) );
            }
            if (action.equals ( "update" )) {
                //向服务器请求要更新的博客id和更新内容，并更新数据库
                blogDao.updateBlog ( String.valueOf ( blogS.getBlog ().getId () ), blogS.getBlog ().getContent () );

            }
            if (action.equals ( "returnImages" )) {
                //返回图片路径
                if (blogDao.returnImages ( blogS.getBlog ().getId () ) != null) {
                    String json = gson.toJson ( blogDao.returnImages ( blogS.getBlog ().getId () ) );
                    System.out.println ( json );
                    out.println ( json );
                    out.flush ();
                    out.close ();
                } else {
                    out.println ( "" );
                    out.flush ();
                    out.close ();
                }
            }
        }
    }

}