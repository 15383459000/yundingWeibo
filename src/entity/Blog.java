package entity;

public class Blog {
    private int id;
    private int origin = id;
    private String userName;
    private String originName = userName;
    private String[] images = {"#"};
    private int u_id;
    private String content;
    private String blogTime;
    private int great;
    private int share;
    private String greatPerson;
    private String sharePerson;
    private String comment;
    private String title;

    public Blog() {
    }

    public Blog(int id, int u_id, String userName, int origin, String content, String blogTime, int great, int share,
                String greatPerson, String sharePerson, String comment, String[] images, String title,
                String originName) {
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
        this.originName = originName;
    }

    public String getBlogTime() {
        return blogTime;
    }

    public void setBlogTime(String blogTime) {
        this.blogTime = blogTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGreat() {
        return great;
    }

    public void setGreat(int great) {
        this.great = great;
    }

    public String getGreatPerson() {
        return greatPerson;
    }

    public void setGreatPerson(String greatPerson) {
        this.greatPerson = greatPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public String getSharePerson() {
        return sharePerson;
    }

    public void setSharePerson(String sharePerson) {
        this.sharePerson = sharePerson;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
}


//~ Formatted by Jindent --- http://www.jindent.com
