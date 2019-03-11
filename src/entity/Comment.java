package entity;


import com.mysql.cj.x.protobuf.MysqlxDatatypes;

public class Comment {

    //评论类的表
    private int cid;
    private int u_id;
    private String userName;
    //用这个↓来与主表连接
    private int id;
    private String comment;
    private java.sql.Timestamp createTime;


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }


    public int getUId() {
        return u_id;
    }

    public void setUId(int u_id) {
        this.u_id = u_id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

}
