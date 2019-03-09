package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author
 */
public class sharechartsdao {


    public List<sharecharts> get() throws SQLException, ClassNotFoundException {
        List<sharecharts> result = new ArrayList<sharecharts> ();

        Connection conn = DButil.getConnection ();
        StringBuilder sb = new StringBuilder ();
        sb.append ( "select u_id,userName,share,id,title from blog " );

        PreparedStatement ptmt = conn.prepareStatement ( sb.toString () );

        ResultSet rs = ptmt.executeQuery ();

        sharecharts g = null;
        while (rs.next ()) {
            g = new sharecharts ();
            g.setU_id ( rs.getInt ( "u_id" ) );
            g.setUserName ( rs.getString ( "userName" ) );
            g.setShare ( rs.getInt ( "share" ) );
            g.setId ( rs.getInt ( "id" ) );
            g.setTitle ( rs.getString ( "title" ) );
            result.add ( g );

        }

        /**
         * 利用collection函数中的sort方法，对list集合中的元素进行排序
         * 重写compare方法，使返回的结果从大到小排列
         */
        Collections.sort ( result, new Comparator<sharecharts> () {

            @Override
            public int compare(sharecharts p1, sharecharts p2) {

                if (p1.getShare () > p2.getShare ()) {
                    return -1;
                }
                if (p1.getShare () == p2.getShare ()) {
                    return 0;
                }
                return 1;
            }

        } );
        return result;
    }
}
