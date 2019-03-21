package servlet;

import entity.*;
import dao.commentDao;
import util.Json;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author wangran
 */
@WebServlet(name = "Comment", urlPatterns = "/servlet/Comment")
public class Comment extends HttpServlet {
    private String action;
    private entity.Comment comment = new entity.Comment ();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //配置Servlet
        response.setContentType ( "text/json;charset=UTF-8" );
        response.setCharacterEncoding ( "UTF-8" );
        response.setContentType ( "json" );

//        获取PrintWriter对象
        PrintWriter out = response.getWriter ();
        String comJson = Json.getString ( request );
        Gson gs = new Gson ();
        Blog bl = new Blog ();
        CommentS commentS = gs.fromJson ( comJson, CommentS.class );
        commentDao cd = new commentDao ();
        //判断是否为"获得评论"功能
        if (commentS.getAction () != null) {
            this.action = commentS.getAction ();
            if (action.equals ( "returnComment" )) {
                String json = null;
                try {
                    //这里输入的是微博文章的id
                    json = gs.toJson(cd.getAllCommentById(commentS.getComment().getId()));
                }catch (SQLException e) {
                    e.printStackTrace ();
                }
                out.println ( json );
                out.flush ();
                out.close ();
            }
            //判断是否为"添加评论"功能
            if (action.equals ( "addComment" )) {
                String u_id = commentS.getComment ().getUId () + "";
                String userName = commentS.getComment ().getUserName () + "";
                String id = commentS.getComment ().getId () + "";
                String comment = commentS.getComment ().getComment () + "";
                try {
                    //调用Servlet
                    cd.addCom ( u_id, userName, id, comment );
                }catch (SQLException e) {
                    e.printStackTrace ();
                }
            }
        }

    }
}
