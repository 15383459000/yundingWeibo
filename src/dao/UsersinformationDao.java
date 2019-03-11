package dao;

import entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersinformationDao {
    /**
     * 向服务器请求用户的id，返回用户的所有个人信息
     * json={"id":"?"}
     * 返回的结果{"id":"1","nickname":"df","username":"df","password":"df","sex":"nan","birthday":"2019-03-09 05:50:18.0","autograph":"123","registertime":"2019-03-09 05:50:24.0","qq":"12346","graduatefrom":"4536"}
     *
     * @param id
     * @return
     */
    public Users get(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection ();
            // SQL语句
            String sql = "select * from UsersInformation where id=?;";
            stmt = conn.prepareStatement ( sql );
            stmt.setInt ( 1, Integer.valueOf ( id ) );
            rs = stmt.executeQuery ();
            Users usersinformation = new Users ();
            while (rs.next ()) {
                usersinformation.setId ( Integer.valueOf ( rs.getString ( "id" ) ) );
                usersinformation.setNickName ( rs.getString ( "nickName" ) );
                usersinformation.setUserName ( rs.getString ( "userName" ) );
                usersinformation.setPassword ( rs.getString ( "password" ) );
                usersinformation.setSex ( rs.getString ( "sex" ) );
                usersinformation.setBirthday ( rs.getString ( "birthday" ) );
                usersinformation.setAutograph ( rs.getString ( "autograph" ) );
                usersinformation.setRegisterTime ( rs.getString ( "registerTime" ) );
                usersinformation.setQq ( rs.getString ( "qq" ) );
                usersinformation.setGraduateFrom ( rs.getString ( "graduateFrom" ) );
                usersinformation.setTags ( rs.getString ( "tags" ).split ( "#" ) );
                usersinformation.setEmail ( rs.getString ( "email" ) );
                usersinformation.setPlace ( rs.getString ( "place" ) );
                usersinformation.setImages ( rs.getString ( "image" ).split ( "#" ) );
            }
            return usersinformation;
            // 返回Blog。
        }catch (Exception ex) {
            ex.printStackTrace ();
            return null;
        } finally {
            // 释放数据集对象
            UsersinformationDao.re ( stmt, rs );
        }
    }

    private static void re(PreparedStatement stmt, ResultSet rs) {
        // 释放数据集对象
        if (rs != null) {
            try {
                rs.close ();
                rs = null;
            }catch (Exception ex) {
                ex.printStackTrace ();
            }
        }
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

    public void updateUsersInformation(Users u) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DButil.getConnection ();
            if (u.getUserName () != null) {
                String sql = " update usersinformation  set userName=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getUserName () );
                stmt.setInt ( 2, Integer.valueOf ( u.getId () ) );
                stmt.execute ();
            }
            if (u.getAutograph () != null) {
                String sql = " update usersinformation  set autograph=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getAutograph () );
                stmt.setInt ( 2, Integer.valueOf ( u.getId () ) );
                stmt.execute ();
            }
            if (u.getEmail () != null) {
                String sql = " update usersinformation  set email=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getEmail () );
                stmt.setInt ( 2, Integer.valueOf ( u.getId () ) );
                stmt.execute ();
            }
            if (u.getGraduateFrom () != null) {
                String sql = " update usersinformation  set graduateFrom=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getGraduateFrom () );
                stmt.setInt ( 2, Integer.valueOf ( u.getId () ) );
                stmt.execute ();
            }
            if (u.getImages () != null) {
                String sql = " update usersinformation  set image=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, Users.ArrayToString ( u.getImages () ) );
                stmt.setInt ( 2, Integer.valueOf ( u.getId () ) );
                stmt.execute ();
            }
            if (u.getNickName () != null) {
                String sql = " update usersinformation  set nickName=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getNickName () );
                stmt.setInt ( 2, Integer.valueOf ( u.getId () ) );
                stmt.execute ();
            }
            if (u.getPassword () != null) {
                String sql = " update usersinformation  set password=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getPassword () );
                stmt.setInt ( 2, Integer.valueOf ( u.getId () ) );
                stmt.execute ();
            }
            if (u.getPlace () != null) {
                String sql = " update usersinformation  set place=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getPlace () );
                stmt.setInt ( 2, Integer.valueOf ( u.getId () ) );
                stmt.execute ();
            }
            if (u.getQq () != null) {
                String sql = " update usersinformation  set qq=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getQq () );
                stmt.setInt ( 2, Integer.valueOf ( u.getId () ) );
                stmt.execute ();
            }
            if (u.getSex () != null) {
                String sql = " update usersinformation  set sex=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getSex () );
                stmt.setInt ( 2, Integer.valueOf ( u.getId () ) );
                stmt.execute ();
            }
            if (u.getTags () != null) {
                String sql = " update usersinformation  set tags=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, Users.ArrayToString ( u.getTags () ) );
                stmt.setInt ( 2, Integer.valueOf ( u.getId () ) );
                stmt.execute ();
            }
            if (u.getBirthday () != null) {
                String sql = " update usersinformation  set birthday=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getBirthday () );
                stmt.setInt ( 2, Integer.valueOf ( u.getId () ) );
                stmt.execute ();
            }

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

}
