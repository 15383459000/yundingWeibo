package dao;

import entity.Blog;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDao {

    /**
     * 通过用户id来获取所有的收藏文章
     *
     * @param u_id 用户id
     * @return blog的集合
     */
    public List<Blog> getAllShareBlogById(int u_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection ();
            String sql = "select shareBlog from UsersInformation where id=?;";
            // SQL语句
            stmt = conn.prepareStatement ( sql );
            stmt.setInt ( 1, u_id );
            rs = stmt.executeQuery ();
            String share = new String ();
            List<Blog> list = new ArrayList<> ();
            while (rs.next ()) {
                share = rs.getString ( "shareBlog" );
            }
            String[] shares = share.split ( "#" );
            for (String i : shares) {
                list.add ( getBlogById ( i ) );
            }

            return list;
            // 返回集合。
        }catch (Exception ex) {
            ex.printStackTrace ();
            return null;
        } finally {
            // 释放数据集对象
            BlogDao.re ( stmt, rs );
        }
    }

    /**
     * 通过文章id来获取文章
     *
     * @param id 文章id
     * @return blog
     */
    /*private Blog getBlogById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection();
            // SQL语句
            String sql = "select * from blog where id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery ();
            Blog blog = new Blog();
            while (rs.next ()) {
                blog.setId ( rs.getInt ( "id" ) );
                blog.setU_id ( rs.getInt ( "u_id" ) );
                blog.setContent ( rs.getString ( "content" ).toString () );
                blog.setBlogTime ( rs.getString("blogTime"));
                blog.setGreat(rs.getInt("great"));
                blog.setShare(rs.getInt("share"));
                blog.setUserName(rs.getString( "userName" ) );
                if (rs.getString ( "image" ) != null) {
                    blog.setImages ( rs.getString ( "image" ).split ( "#" ) );
                }
            }
            return blog;
            // 返回Blog。
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            BlogDao.re(stmt, rs);
        }
    }*/

    /**
     * 通过用户id来获取该用户收藏
     * @param u_id 用户id
     * @return blog的集合
     */
    public List<Blog> getAllFavoriteBlogById(int u_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection ();
            String sql = "select favorite from UsersInformation where id=?;";
            // SQL语句
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, u_id);
            rs = stmt.executeQuery ();
            List<Blog> list = new ArrayList<>();
            while (rs.next ()) {
                String[] favorite = rs.getString ( "favorite" ).split ( "#" );
                for (String i : favorite) {
                    list.add ( getBlogById ( i ) );
                }
            }


            return list;
            // 返回集合。
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            BlogDao.re(stmt, rs);
        }
    }

    /**
     * 通过用户id来获取文章集合
     * @param id 用户id
     * @return bolg的集合
     */
    public List<Blog> getAllBlogById(int id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        UserUtil util = new UserUtil ();
        List<Blog> list = new ArrayList<Blog> ();
        // 文章集合
        try {
            conn = DButil.getConnection();
            String sql = "select * from blog where u_id=?;";
            // SQL语句
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery ();
            list = resultSetToBlog ( resultSet );


                // 把一个文章加入集合
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
        }
        return list;
            // 返回集合。

    }

    /**
     * 添加收藏
     * @param u_id 用户id
     * @param b_id 博客id
     */
    public boolean addFavorite(String u_id, String b_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String favorite = "";
        try {
            conn = DButil.getConnection ();
            String sql = "select favorite from UsersInformation where id=?;";
            // SQL语句
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.valueOf ( u_id ) );
            rs = stmt.executeQuery ();
            while (rs.next ()) {
                favorite = rs.getString("favorite");
            }
            if (favorite != null) {
                favorite += "#" + b_id;
            } else {
                favorite = b_id;
            }
            updateFavorite ( Integer.valueOf ( u_id), favorite);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            BlogDao.re ( stmt, rs );
        }
        return true;
    }

    /**
     * 添加转发
     *
     * @param u_id 用户id
     * @param b_id 博客id
     */
    public boolean addSharedBlog(String u_id, String b_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String shareBlog = "";
        try {
            conn = DButil.getConnection ();
            String sql = "select shareBlog from UsersInformation where id=?;";
            // SQL语句
            stmt = conn.prepareStatement ( sql );
            stmt.setInt ( 1, Integer.valueOf ( u_id ) );
            rs = stmt.executeQuery ();
            while (rs.next ()) {
                shareBlog = rs.getString ( "shareBlog" );
            }
            if (shareBlog != null) {
                shareBlog += "#" + b_id;
            } else {
                shareBlog = b_id;
            }
            updateShareBlog ( Integer.valueOf ( u_id ), shareBlog );
        }catch (Exception ex) {
            ex.printStackTrace ();
        } finally {
            BlogDao.re( stmt, rs );
        }
        return true;
    }

    /**
     * 更新转发人
     *
     * @param u_id       用户id
     * @param shareBlog 新的转发单
     */
    private void updateShareBlog(int u_id, String shareBlog) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DButil.getConnection ();
            String sql = " update UsersInformation " +
                    "set shareBlog=? " +
                    " where id=? ";
            stmt = conn.prepareStatement ( sql );
            stmt.setString ( 1, shareBlog );
            stmt.setInt ( 2, u_id );
            stmt.execute ();
        }catch (Exception ex) {
            ex.printStackTrace ();
        } finally {
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close ();
                    stmt = null;
                }catch (Exception ex) {
                    ex.printStackTrace ();
                }
            }
        }
    }

    /**
     * 更新收藏文章
     * @param u_id     用户id
     * @param favorite 新的收藏
     */
    private void updateFavorite(int u_id, String favorite) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DButil.getConnection ();


            String sql = " update UsersInformation "+
                    "set favorite=? " +
                    " where id=? ";
            stmt = conn.prepareStatement ( sql );
            stmt.setString ( 1, favorite );
            stmt.setInt ( 2, u_id );
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

    /**
     * 图片路径数组该字符串
     * @param images 图片路径数组
     * @return 图片路径以#隔开
     */
    private String imagesDao(String[] images) {
        String image = images[0];
        if (images.length > 1) {
            for (int i = 1; i < images.length; i++) {
                image += "#" + images[i];
            }
        }
        return image;
    }

    /**
     * 返回图片路径数组
     *
     * @param b_id 用户id
     * @return 图片路径数组
     */
    public String[] returnImages(int b_id){
        //创建连接
        Connection conn = null;
        //创建sql语句
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String []images=null;
        try {
            conn = DButil.getConnection ();
            String sql = "select image from blog where id=?;";
            // SQL语句
            stmt = conn.prepareStatement ( sql );
            stmt.setInt ( 1, b_id );
            rs = stmt.executeQuery ();
            while (rs.next ()) {
                if (rs.getString ( "image" ) != null) {
                    images = rs.getString ( "image" ).split ( "#" );
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace ();
        } finally {
            // 释放数据集对象
            BlogDao.re ( stmt, rs );
        }
        return images;
    }

    /**
     * 发布文章
     *
     * @param blog 博客对象
     */
    public boolean addBlog(Blog blog) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DButil.getConnection ();
            String sql = "insert into blog (u_id,userName,content,blogTime,image,title,origin)  values (?,?,?,current_timestamp (),?,?,?)";
            stmt = conn.prepareStatement ( sql );
            stmt.setInt ( 1, blog.getU_id () );
            stmt.setString ( 2, blog.getUserName () );
            stmt.setString ( 3, blog.getContent () );
            try {
                stmt.setString ( 4, imagesDao ( blog.getImages () ) );
            }catch (NullPointerException ignore) {
                stmt.setString ( 4, null );
            }
            try {
                stmt.setString ( 5, blog.getTitle () );
            }catch (NullPointerException ignore) {
                stmt.setString ( 5, null );
            }
            stmt.setInt ( 6, blog.getOrigin () );
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
        return true;
    }

    /**
     * 删除文章
     *
     * @param id 博客id
     */
    public boolean deleteBlog(String id){
        Connection conn;
        PreparedStatement stmt;
        try {
            conn = DButil.getConnection ();
            String sql = "delete from blog where id=?;";
            // SQL语句
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.valueOf(id));
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace ();
        }
        return true;
    }

    /**
     * 更新博客内容
     *
     * @param id      博客id
     * @param content 内容
     */
    public boolean updateBlog(String id,String content){
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
                    ex.printStackTrace ();
                }
            }
        }
        return true;
    }

    /**
     *更新点赞数
     * @param id 博客id
     * @param great 新点赞数
     */
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
                    ex.printStackTrace ();
                }
            }
        }
    }

    /**
     *点赞
     * @param id 博客id
     */
    public void addGreat(String id){
        Connection conn;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int great = 0;
        try {
            conn = DButil.getConnection ();
            String sql = "select great from blog where id=?;";
            // SQL语句
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.valueOf(id));
            rs= stmt.executeQuery();
            while (rs.next()){
                great=rs.getInt("great");
            }
            great++;
            updateGreat(id,great);
        } catch (Exception ex) {
            ex.printStackTrace ();
        } finally {
            // 释放数据集对象
            BlogDao.re ( stmt, rs );
        }
    }

    /**
     * 根据点赞/分享排行
     *
     * @param v 表示获取的排行榜的排行依据
     * @return blog的list集合
     */
    public List<Blog> getCharts(String v) throws SQLException, ClassNotFoundException {
        Connection conn = DButil.getConnection ();
        StringBuilder sb = new StringBuilder ();
        final String great = "great";
//        if (v.equals ( great )) {
//            sb.append ( "select * from blog order by great desc" );
//        } else {
//            sb.append ( "select * from blog order by share desc" );
//        }
        switch (v) {
            case "great": {
                sb.append ( "select * from blog order by great desc limit 10" );
                break;
            }
            case "share": {
                sb.append ( "select * from blog order by share desc limit 10" );
                break;
            }
            default: {
                sb.append ( "select * from blog order by id desc limit 10" );
            }
        }

        PreparedStatement ptmt = conn.prepareStatement ( sb.toString () );

        ResultSet rs = ptmt.executeQuery ();


        return BlogDao.resultSetToBlog ( rs );
    }

    /**
     * 清空语句和结果集缓存
     * @param stmt 要清空的语句
     * @param rs 要清空的结果集
     */
    private static void re(PreparedStatement stmt , ResultSet rs ){
        // 释放数据集对象
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace ();
            }
        }
        // 释放语句对象
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 通过b_id获得文章对象
     */
    public Blog getBlogById(String b_id) throws SQLException, ClassNotFoundException {
        Connection connection = DButil.getConnection ();
        // SQL语句
        String sql = "select * from blog where id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement ( sql );
        preparedStatement.setString ( 1,b_id );
        preparedStatement.execute ();
        ResultSet resultSet = preparedStatement.getResultSet ();

        //遍历结果集实例化blog
        Blog blog = resultSetToBlog ( resultSet ).get ( 0 );
        // 释放数据集对象
        BlogDao.re ( preparedStatement, resultSet );


        return blog;
    }

    /**
     * 利用数据集返回单个blog对象
     *
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected static List<Blog> resultSetToBlog(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        List<Blog> list = new ArrayList<> ( 1 );
        Blog blog;
        while (resultSet.next ()) {
            blog = new Blog (
                    resultSet.getInt ( "id" ),
                    resultSet.getInt ( "u_id" ),
                    resultSet.getString ( "userName" ),
                    resultSet.getInt ( "origin" ),
                    resultSet.getString ( "content" ),
                    resultSet.getDate ( "blogTime" ).toString (),
                    resultSet.getInt ( "great" ),
                    resultSet.getInt ( "share" ),
                    resultSet.getString ( "greatPerson" ),
                    resultSet.getString ( "sharePerson" ),
                    resultSet.getString ( "comment" ),
                    resultSet.getString ( "image" ).split ( "#" ),
                    resultSet.getString ( "title" )
            );
            list.add ( blog );
        }
        return list;
    }



}

