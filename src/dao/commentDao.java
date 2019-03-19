/**
 * FileName:wangran
 */
package dao;

import entity.*;
import dao.DButil;

import java.sql.*;
import java.util.ArrayList;

public class commentDao {
    //连接的条件
    private Connection conn;

    {
        try {
            conn = DButil.getConnection ();
        }catch (SQLException e) {
            e.printStackTrace ();
        }catch (ClassNotFoundException e) {
            e.printStackTrace ();
        }
    }

    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement ps = null;

    //添加评论的方法,无返回
    public void addCom(String u_Id, String userName, String id, String comment) throws SQLException {

        stmt = conn.createStatement ();
        int lastId = 0;
//        String sql = "SELECT * FROM weibocomment ";
//        rs = stmt.executeQuery ( sql );
//        //找到最后一行
//        while (rs.next ()) {
//            lastId++;
//        }
//        Timestamp date = new Timestamp ( new java.util.Date ().getTime () );
        //添加评论
        String sqled = "INSERT INTO weibocomment ( u_id, userName,id,comment) VALUES ( ?, ?, ?, ?);";
        ps = conn.prepareStatement ( sqled );
        //依次输入各项参数
        ps.setString ( 1, u_Id );
        ps.setString ( 2, userName );
        ps.setString ( 3, id );
        ps.setString ( 4, comment );
//        ps.setTimestamp ( 5, date );
        ps.execute ();
    }

    //通过微博id获得评论的方法,返回一个Comment类型的list
    public ArrayList<Comment> getAllCommentById(int id) throws SQLException {

        ArrayList<Comment> list = new ArrayList<Comment> ();
        String sql = "SELECT * from weibocomment left blog JOIN  on blog.id=weibocomment.id WHERE blog.id= ?;";
        ps = conn.prepareStatement ( sql );
        ps.setInt ( 1, id );
        rs = ps.executeQuery ();
        Comment co = null;
        while (rs.next ()) {
            co = new Comment ();
            co.setCid ( rs.getInt ( "cid" ) );
            co.setUId ( rs.getInt ( "u_id" ) );
            co.setUserName ( rs.getString ( "userName" ) );
            co.setId ( rs.getInt ( "id" ) );
            co.setComment ( rs.getString ( "comment" ) );
            co.setCreateTime ( rs.getTimestamp ( "createTime" ) );
            list.add ( co );
            // 把一个评论加入集合
        }
        // 释放数据集对象
        if (rs != null) {
            rs.close ();
            rs = null;
        }
        // 释放语句对象
        if (ps != null) {
            ps.close ();
            ps = null;
        }
        return list;
        // 返回集合。
    }
}

