package dao;

import java.sql.Clob;
import java.sql.Date;

/**
 * 点赞量的排行榜的工具类
 */
public class greatcharts {
    private int u_id;        //用户的id
    private String userName; //用户的姓名
    private int great;       //点赞量
    private String title;    //标题
    private int id;          //文章id

    /**
     * 属性的封装
     *
     * @return
     */

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGreat() {
        return great;
    }

    public void setGreat(int great) {
        this.great = great;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * tostring方法的重写
     *
     * @return
     */
    @Override
    public String toString() {
        return '"' + "greatcharts" + '"' + ":" + "{" +
                '"' + "u_id" + '"' + ":" + u_id + "  " +
                '"' + "userName" + '"' + ":" + '"' + userName + '"' + "  " +
                '"' + "id" + '"' + ":" + id + "  " +
                '"' + "title" + '"' + ":" + '"' + title + '"' + "  " +
                '"' + "great" + '"' + ":" + great + '}';
    }

}
