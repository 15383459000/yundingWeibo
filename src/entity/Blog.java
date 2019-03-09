package entity;

import dao.UserUtil;

import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;

public class Blog {
    private int id;
    private int u_id;
    private String userName;
    private Users origin;
    private String content;
    private String blogTime;
    private int great;
    private int share;
    private String greatPerson;
    private String sharePerson;
    private String comment;
    private String[] images;
    private String title;

    public Blog(){}

    public Blog(int id, int u_id, String userName, Users origin, String content, String blogTime, int great, int share, String greatPerson, String sharePerson, String comment, String[] images, String title) {
        this.id = id;
        this.u_id = u_id;
        this.userName = userName;
        this.origin = origin;
        this.content = content;
        this.blogTime = blogTime;
        this.great = great;
        this.share = share;
        this.greatPerson = greatPerson;
        this.sharePerson = sharePerson;
        this.comment = comment;
        this.images = images;
        this.title = title;
    }

    public Users getOrigin() throws SQLException, ClassNotFoundException {
        UserUtil userUtil = new UserUtil ();
        this.origin = userUtil.getUsersById ( String.valueOf ( origin ) );
        return origin;
    }

    public void setOrigin(int origin) throws SQLException, ClassNotFoundException {
        //todo
        UserUtil userUtil = new UserUtil ();
        this.origin = userUtil.getUsersById ( String.valueOf ( origin ) );
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOrigin(Users origin) {
        this.origin = origin;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getBlogTime() {
        return blogTime;
    }

    public void setBlogTime(String blogTime) {
        this.blogTime = blogTime;
    }

    public int getGreat() {
        return great;
    }

    public void setGreat(int great) {
        this.great = great;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public String getGreatPerson() {
        return greatPerson;
    }

    public void setGreatPerson(String greatPerson) {
        this.greatPerson = greatPerson;
    }

    public String getSharePerson() {
        return sharePerson;
    }

    public void setSharePerson(String sharePerson) {
        this.sharePerson = sharePerson;
    }
}
