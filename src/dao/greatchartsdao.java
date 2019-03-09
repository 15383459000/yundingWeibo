package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class greatchartsdao {


    public List<greatcharts> get() throws SQLException, ClassNotFoundException {
        List<greatcharts> result = new ArrayList<greatcharts> ();

        Connection conn = DButil.getConnection ();
        StringBuilder sb = new StringBuilder ();
        sb.append ( "select u_id,userName,great,id,title from blog " );

        PreparedStatement ptmt = conn.prepareStatement ( sb.toString () );

        ResultSet rs = ptmt.executeQuery ();

        greatcharts g = null;
        while (rs.next ()) {
            g = new greatcharts ();
            g.setU_id ( rs.getInt ( "u_id" ) );
            g.setUserName ( rs.getString ( "userName" ) );
            g.setGreat ( rs.getInt ( "great" ) );
            g.setId ( rs.getInt ( "id" ) );
            g.setTitle ( rs.getString ( "title" ) );
            result.add ( g );

        }

        /**
         * 利用collection函数中的sort方法，对list集合中的元素进行排序
         * 重写compare方法，使返回的结果从大到小排列
         */
        Collections.sort ( result, new Comparator<greatcharts> () {

            @Override
            public int compare(greatcharts p1, greatcharts p2) {

                if (p1.getGreat () > p2.getGreat ()) {
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
