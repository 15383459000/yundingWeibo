package dao;

import com.mysql.cj.protocol.Resultset;
import entity.Blog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDao {
    //通过文章id返回文章属性
    private Blog getFavoriteBlogById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection();
            // SQL语句
            String sql = "select * from blog where id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Blog blog=new Blog();
            while (rs.next()) {
                blog.setContent(rs.getClob("content"));
                blog.setBlogTime(rs.getDate("blogTime"));
                blog.setGreat(rs.getInt("great"));
                blog.setShare(rs.getInt("share"));
                blog.setUserName(rs.getString("userName"));
            }
            // 返回Blog。
            return blog;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            //            释放对象
            closeAll ( stmt,rs );
        }
    }
//    TODO

    /**通过用户id获得此用户喜爱的blog集合
     *
     * @param u_id 用户id
     * @return list 所有文章对象List  ,null  出错
     */
    public List<Blog> getAllFavoriteBlogById(int u_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection();
            // SQL语句
            String sql = "select favorite from usersinformation where id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, u_id);
            rs = stmt.executeQuery();
            String fav=new String();
            List<Blog> list=new ArrayList<>();
            while (rs.next()) {
               fav=rs.getString("favorite");
            }
            String[] favorite=fav.split("#");
            for (String i:favorite){
                list.add(getFavoriteBlogById(Integer.valueOf(i)));
            }
// 返回集合。
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            //            释放对象
            closeAll ( stmt,rs );
        }
    }
    //通过用户id获取文章
    public ArrayList<Blog> getAllBlogById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Blog> list = new ArrayList<Blog>(); // 文章集合
        try {
            conn = DButil.getConnection();
            // SQL语句
            String sql = "select * from blog where u_id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Blog blog=new Blog();
                blog.setContent(rs.getClob("content"));
                blog.setBlogTime(rs.getDate("blogTime"));
                blog.setGreat(rs.getInt("great"));
                blog.setShare(rs.getInt("share"));
                blog.setUserName(rs.getString("userName"));
                list.add(blog);// 把一个文章加入集合
            }
            return list; // 返回集合。
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            //            释放对象
            closeAll ( stmt,rs );
        }
    }

    /**通过用户id和博客id添加收藏
     *
     * @param u_id 用户的id
     * @param b_id 文章的id
     */
    public void addFavorite(String u_id,String b_id){
        Connection conn = null;

//        预处理对象
        PreparedStatement stmt = null;

//        结果集
        ResultSet rs = null;
        String favorite = "";
        try {
//            获取数据库连接
            conn = DButil.getConnection();
            // SQL语句
            String sql = "select favorite from usersinformation where id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.valueOf(u_id));
            rs= stmt.executeQuery();
            while (rs.next()){
                favorite=rs.getString("favorite");
            }
            if(favorite!=null) {
                favorite += "#"+b_id ;
            }else {
                favorite=b_id;
            }
            updateFavorite(Integer.valueOf(u_id),favorite);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//            释放对象
            closeAll ( stmt,rs );
        }
    }

    /**通过用户id和添加要收藏的博客id后的收藏更新用户收藏
     *
     * @param u_id
     * @param favorite 添加的收藏
     */
    private void updateFavorite(int u_id,String favorite){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DButil.getConnection();


            String sql=    " update usersinformation "+
                    "set favorite=? " +
                    " where id=? ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,favorite);
            stmt.setInt(2,u_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**上传blog文章
     *
     * @param blog blog
     */
    private void addBlog(Blog blog){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DButil.getConnection();
            String sql="insert into blog (u_id,userName,content,blogTime)  values (?,?,?,current_date())";
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,blog.getId());
            stmt.setString(2,blog.getUserName());
            stmt.setClob(3,blog.getContent());
            stmt.execute();
            // 返回集合。
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    //通过博客id删除
    public void deleteBlog(String id){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DButil.getConnection();
            String sql = "delete from blog where id=?;"; // SQL语句
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.valueOf(id));
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //通过博客id和更新的文章内容更改文章
    public void updateBlog(String id,String content){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DButil.getConnection();


            String sql=    " update blog "+
                    "set content=? " +
                    " where id=? ";
            stmt = conn.prepareStatement(sql);
            stmt.setClob(1,new javax.sql.rowset.serial.SerialClob(content.toCharArray()));
            stmt.setInt(2,Integer.valueOf(id));
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    //通过博客id和新点赞数更新文章点赞数
    private void updateGreat(String id,int great){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DButil.getConnection();
            String sql=" update blog  set great=?  where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, great);
            stmt.setInt(2,Integer.valueOf(id));
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    //通过博客id点赞
    public void addGreat(String id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int great = 0;
        try {
            conn = DButil.getConnection();
            // SQL语句
            String sql = "select great from blog where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.valueOf(id));
            rs= stmt.executeQuery();
            while (rs.next()){
                great=rs.getInt("great");
            }
            great++;
            updateGreat(id,great);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//            释放对象
          closeAll ( stmt,rs );
        }
    }

    /**获取文章信息来创建文章
     * 创建博客
     * @param u_id
     * @param userName
     * @param content
     * @throws SQLException
     */
    public void createBlog(String u_id, String userName, String content) throws  SQLException {
        Blog blog=new Blog();
        blog.setId(Integer.valueOf(u_id));
        blog.setUserName(userName);
        blog.setContent(new javax.sql.rowset.serial.SerialClob(content.toCharArray()));
        addBlog(blog);
    }

    /**
     * 释放数据集对象，预处理语句对象
     * @param statement
     * @param resultSet
     */
    protected void closeAll(PreparedStatement statement,ResultSet resultSet){
        // 释放数据集对象
        if (resultSet != null) {
            try {
                resultSet.close();
                resultSet = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        // 释放语句对象
        if (statement != null) {
            try {
                statement.close();
                statement = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * 通过b_id获得文章
     */
    public Blog getBlogById( String b_id) throws SQLException, ClassNotFoundException {
        Connection connection = DButil.getConnection ();
        // SQL语句
        String sql = "select * from blog where id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement ( sql );
        preparedStatement.setString ( 1,b_id );
        preparedStatement.execute ();
        ResultSet resultSet = preparedStatement.getResultSet ();
        Blog blog = new Blog(resultSet.getInt ( "id" ))
    }
}

