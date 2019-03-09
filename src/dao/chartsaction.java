package dao;

import java.sql.SQLException;
import java.util.List;

public class chartsaction {

    public List<greatcharts> getgreat() throws SQLException, ClassNotFoundException {
        greatchartsdao dao = new greatchartsdao ();
        return dao.get ();

    }

    public List<sharecharts> getshare() throws SQLException, ClassNotFoundException {
        sharechartsdao dao = new sharechartsdao ();
        return dao.get ();

    }
}
