package entity;

import java.sql.Clob;
import java.sql.Date;

public class Blog {
    private int id;
    private String userName;
    private Clob content;
    private Date blogTime;
    private int great;
    private int share;
    private String greatPerson;
    private String sharePerson;

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

    public Clob getContent() {
        return content;
    }

    public void setContent(Clob content) {
        this.content = content;
    }

    public Date getBlogTime() {
        return blogTime;
    }

    public void setBlogTime(Date blogTime) {
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
