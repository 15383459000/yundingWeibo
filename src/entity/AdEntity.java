package entity;

public class AdEntity {
    private String content;
    private String title;
    private String[] images;

    public AdEntity(String content, String images[], String title) {
        this.content = content;
        this.title = title;
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
