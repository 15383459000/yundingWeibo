package dao;

import entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersInformation {
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
            Users UsersInformation = new Users ();
            while (rs.next ()) {
                UsersInformation.setId ( Integer.valueOf ( rs.getString ( "id" ) ) );
                UsersInformation.setUserName ( rs.getString ( "userName" ) );
                UsersInformation.setPassword ( rs.getString ( "password" ) );
                UsersInformation.setSex ( rs.getString ( "sex" ) );
                UsersInformation.setBirthday ( rs.getString ( "birthday" ) );
                UsersInformation.setAutograph ( rs.getString ( "autograph" ) );
                UsersInformation.setRegisterTime ( rs.getString ( "registerTime" ) );
                UsersInformation.setQq ( rs.getString ( "qq" ) );
                UsersInformation.setGraduateFrom ( rs.getString ( "graduateFrom" ) );
                UsersInformation.setTags ( rs.getString ( "tags" ).split ( "#" ) );
                UsersInformation.setEmail ( rs.getString ( "email" ) );
                UsersInformation.setPlace ( rs.getString ( "place" ) );
                UsersInformation.setImage(rs.getString("image"));
            }
            return UsersInformation;
            // 返回Blog。
        }catch (Exception ex) {
            ex.printStackTrace ();
            return null;
        } finally {
            // 释放数据集对象
            UsersInformation.re ( stmt, rs );
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
                String sql = " update UsersInformation set userName=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getUserName () );
                stmt.setInt ( 2, u.getId () );
                stmt.execute ();
            }
            if (u.getAutograph () != null) {
                String sql = " update UsersInformation  set autograph=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getAutograph () );
                stmt.setInt ( 2, u.getId () );
                stmt.execute ();
            }
            if (u.getEmail () != null) {
                String sql = " update UsersInformation  set email=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getEmail () );
                stmt.setInt ( 2, u.getId () );
                stmt.execute ();
            }
            if (u.getGraduateFrom () != null) {
                String sql = " update UsersInformation  set graduateFrom=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getGraduateFrom () );
                stmt.setInt ( 2, u.getId () );
                stmt.execute ();
            }
            if (u.getImage() != null) {
                String sql = " update UsersInformation  set image=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString(1, u.getImage());
                stmt.setInt ( 2, u.getId () );
                stmt.execute ();
            }

            if (u.getPassword () != null) {
                String sql = " update UsersInformation  set password=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getPassword () );
                stmt.setInt ( 2, u.getId () );
                stmt.execute ();
            }
            if (u.getPlace () != null) {
                String sql = " update UsersInformation  set place=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getPlace () );
                stmt.setInt ( 2, u.getId () );
                stmt.execute ();
            }
            if (u.getQq () != null) {
                String sql = " update UsersInformation  set qq=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getQq () );
                stmt.setInt ( 2, u.getId () );
                stmt.execute ();
            }
            if (u.getSex () != null) {
                String sql = " update UsersInformation  set sex=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getSex () );
                stmt.setInt ( 2, u.getId () );
                stmt.execute ();
            }
            if (u.getTags () != null) {
                String sql = " update UsersInformation  set tags=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, Users.ArrayToString ( u.getTags () ) );
                stmt.setInt ( 2, u.getId () );
                stmt.execute ();
            }
            if (u.getBirthday () != null) {
                String sql = " update UsersInformation  set birthday=?  where id=?";
                stmt = conn.prepareStatement ( sql );
                stmt.setString ( 1, u.getBirthday () );
                stmt.setInt ( 2, u.getId () );
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
