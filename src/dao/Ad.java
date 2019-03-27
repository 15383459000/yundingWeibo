package dao;

import entity.AdEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ad {
    public static AdEntity getAd() throws SQLException, ClassNotFoundException {
        Connection connection = DButil.getConnection();
        String sql = "select * from Ad order by rand( ) limit 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        resultSet.getInt("id");
        AdEntity ad = new AdEntity(resultSet.getString("content"), resultSet.getString("images").split("#"), resultSet.getString("title"));
        return ad;
    }
}
