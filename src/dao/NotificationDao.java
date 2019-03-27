package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 微博公告
 */
public class NotificationDao {
    public static List<String> getNotification() throws SQLException, ClassNotFoundException {
        Connection connection = DButil.getConnection();
        String sql = "select * from notification ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.getResultSet();
        List<String> notification = new ArrayList<>(1);
        while (resultSet.next()) {
            notification.add(resultSet.getString("notification"));
        }
        return notification;
    }
}
