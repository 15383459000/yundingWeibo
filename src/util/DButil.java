package util;


import entity.Users;
import java.sql.*;


public class DButil {
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
        Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        return  connection;
    }

    /**
     *数据库查询
     * @param column
     * @param value
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Users referUser(String column ,Object value) throws SQLException, ClassNotFoundException {
        Connection connection = DButil.getConnection ();
        StringBuilder sql = new StringBuilder ( "select * from UsersInformation where " );
        sql.append ( column ).append ( "=\"" ).append ( value ).append ( "\"" );
        PreparedStatement preparedStatement = connection.prepareStatement ( sql.toString () );
        preparedStatement.executeQuery ();
        ResultSet resultSet = preparedStatement.getResultSet ();
        Users user = null;
        while(resultSet.next ()){
            user = new Users();
//            TODO 补充用户的属性信息
            user.setUserName ( resultSet.getString ( "userName" ) );
            user.setPassword ( resultSet.getString ( "password" ) );
            user.setEmail ( resultSet.getString ( "email" ) );
        }
        connection.close ();
        return user;
    }
}
