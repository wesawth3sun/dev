package app.main;

public class WiseSaying {

    private int id;
    private String author;
    private String content;


    public WiseSaying() {
    }

    public WiseSaying(int id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
