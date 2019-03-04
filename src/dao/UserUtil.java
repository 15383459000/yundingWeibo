package dao;

import entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 用户工具类
 * 对用户的，添加，修改，获取
 *
 * @author guohaodong
 */
public class UserUtil {


    /**
     * 通过用户id获取对象
     *
     * @param userId 用户的id号
     * @return Users类型的对象，查询不到返回NULL
     * @throws SQLException
     */
    public Users getUsersById(String userId) throws SQLException, ClassNotFoundException {
        Users user = DButil.referUser ( "id", userId );

        return user;
    }


    /**
     * 通过用户email获取对象
     *
     * @param userEmail 用户的email
     * @return Users类型的对象，查询不到返回NULL
     * @throws SQLException 数据库异常
     */
    public Users getUsersByEmail(String userEmail) throws SQLException, ClassNotFoundException {
        return DButil.referUser ( "email", userEmail );
    }


    /**
     * 注册用户
     *
     * @param user 要添加的用户对象
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addUser(Users user) throws SQLException, ClassNotFoundException {

//        连接数据库,关闭自动提交
        Connection connection = DButil.getConnection ();
        connection.setAutoCommit ( false );

//        预处理语句
        //todo 添加用户的属性
        String sql = "insert UsersInformation SET " +
                "email = ?," +
                "password = ?," +
                "registerTime = ?";
        PreparedStatement preparedStatement = connection.prepareStatement ( sql );
        preparedStatement.setString ( 1, user.getEmail () );
        preparedStatement.setString ( 2, user.getPassword () );
        preparedStatement.setString ( 3,user.getRegisterTime () );
        preparedStatement.execute ();

//        手动提交
        connection.commit ();
        connection.close ();
    }


    /**
     * 修改用户密码
     *
     * @param user 需要更改密码的 User 对象
     */
    public void modifyUser(Users user) throws SQLException, ClassNotFoundException {

//        连接数据库,关闭自动提交
        Connection connection = DButil.getConnection ();
        connection.setAutoCommit ( false );

//        预处理语句
        String sql = "UPDATE UPDATE UsersInformation set password=? where email=?";
        PreparedStatement preparedStatement = connection.prepareStatement ( sql );
        preparedStatement.setString ( 1, user.getPassword () );
        preparedStatement.setString ( 2, user.getEmail () );
        preparedStatement.execute ();

//        手动提交
        connection.commit ();
        connection.close ();
    }


}
