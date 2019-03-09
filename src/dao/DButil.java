package dao;


import entity.Users;
import java.sql.*;

/**
 * 
 */
public class DButil {
    //todo 修改为正确的数据库

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "wenzhu27";

    /**
     *  数据库连接
     * @return  connection
     * @throws SQLException sql异常
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获得数据库连接
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    /**
     *数据库查询返回user对象
     * @param column 字段名
     * @param value 字段值
     * @return users对象
     * @throws SQLException 数据库错误
     * @throws ClassNotFoundException jar包错误
     */
    public static Users referUser(String column ,Object value) throws SQLException, ClassNotFoundException {
        Connection connection = DButil.getConnection ();
        PreparedStatement preparedStatement = connection.prepareStatement ( "select * from UsersInformation where " + column + "=\"" + value + "\"" );
        preparedStatement.executeQuery ();
        ResultSet resultSet = preparedStatement.getResultSet ();
        Users user = null;
        while(resultSet.next ()){
            user = new Users();
//            TODO 补充用户的属性信息
            user.setUserName ( resultSet.getString ( "userName" ) );
            user.setPassword ( resultSet.getString ( "password" ) );
            user.setEmail ( resultSet.getString ( "email" ) );
            user.setRegisterTime ( resultSet.getString ( "registerTime" ) );
            user.setId ( resultSet.getInt ( "id" ) );
        }
        connection.close ();
        return user;
    }
}
