package dao;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Timechartsdao {
    public List<Timecharts> get() throws SQLException {
        List<Timecharts> result = new ArrayList<Timecharts> ();

        Connection conn = DB.getconnection ();
        StringBuilder sb = new StringBuilder ();
        sb.append ( "select u_id,userName,content,blogTime,great,share,image,title,id from blog limit 10 " );

        PreparedStatement ptmt = conn.prepareStatement ( sb.toString () );

        ResultSet rs = ptmt.executeQuery ();

        Timecharts g = null;
        while (rs.next ()) {
            g = new Timecharts ();
            g.setU_id ( rs.getInt ( "u_id" ) );
            g.setUserName ( rs.getString ( "userName" ) );
            g.setContent ( rs.getString ( "content" ) );
            g.setBlogTime ( rs.getString ( "blogTime" ) );
            g.setGreat ( rs.getInt ( "great" ) );
            g.setShare ( rs.getInt ( "share" ) );
            if (rs.getString ( "image" ) != null) {
                g.setImages ( rs.getString ( "image" ).split ( "#" ) );
            }
            g.setTitle ( rs.getString ( "title" ) );
            g.setB_id ( rs.getString ( "id" ) );
            result.add ( g );

        }

        /**
         * 利用collection函数中的sort方法，对list集合中的元素进行排序
         * 重写compare方法，使返回的结果从大到小排列
         */
        Collections.sort ( result, new Comparator<Timecharts> () {

            @Override
            public int compare(Timecharts p1, Timecharts p2) {

                if (Integer.valueOf ( p1.getB_id () ) > Integer.valueOf ( p2.getB_id () )) {
                    return -1;
                }
                if (p1.getGreat () == p2.getGreat ()) {
                    return 0;
                }
                return 1;
            }

        } );
        return result;
    }
}
