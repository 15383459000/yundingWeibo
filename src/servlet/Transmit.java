package servlet;

import com.google.gson.Gson;
import dao.BlogDao;
import entity.Blog;
import entity.BlogS;
import entity.Users;
import util.Json;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * 1.获取文章id,用户id,用户名userName
 * 2.将文章中的 userName 属性和 u_id属性修改为转发者的属性
 *
 * @author guohaodong
 */
@WebServlet(name = "Transmit", urlPatterns = "/servlet/Transmit")
public class Transmit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //        设置编码格式
        request.setCharacterEncoding ( "utf-8" );
        response.setCharacterEncoding ( "utf-8" );
        response.setContentType ( "json" );
        //        获取输出（printWriter）
        PrintWriter out = response.getWriter ();
        //        实例化用户文章工具
        BlogDao blogDao = new BlogDao ();
        Gson gson = new Gson ();
        //        获取blogS对象userId blogId
        BlogS blogS = gson.fromJson ( Json.getString ( request ), BlogS.class );

        Users user = blogS.getUsers ();
        try {
            blogDao.addShare(String.valueOf(blogS.getBlog().getId()));
            Blog blog = blogDao.getBlogById ( String.valueOf ( blogS.getBlog ().getId () ) );
            blog.setUserName ( user.getUserName () );
            blog.setU_id ( user.getId () );
            blogDao.addBlog ( blog );
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
        }
        out.print ( "{\"status\":\"1\"}" );
//            输出 status 值
        out.flush ();
        out.close ();
    }


}

