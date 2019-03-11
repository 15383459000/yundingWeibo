package servlet;

import java.util.Arrays;

public class Timecharts {
    private int u_id;
    private String userName;
    private String content;
    private String blogTime;
    private int great;
    private int share;
    private String[] images;
    private String title;
    private String b_id;

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

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    @Override
    public String toString() {
        return '"' + "Timecharts" + '"' + ":" + "{" +
                '"' + "u_id" + '"' + ":" + u_id + "  " +
                '"' + "userName" + '"' + ":" + '"' + userName + '"' + "  " +
                '"' + "content" + '"' + ":" + content + "  " +
                '"' + "blogTime" + '"' + ":" + blogTime + "  " +
                '"' + "great" + '"' + ":" + great + "  " +
                '"' + "share" + '"' + ":" + share + "  " +
                '"' + "images" + '"' + ":" + images + "  " +
                '"' + "title" + '"' + ":" + '"' + title + '"' + "  " +
                '"' + "b_id" + '"' + ":" + b_id + '}';
    }
}
