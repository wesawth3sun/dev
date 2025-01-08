package app.domain.wiseSaying;

public class WiseSaying {
    private int id; //명언 넘버
    private String content;
    private String author;

    public WiseSaying(int id, String content, String autuor) {
        this.id = id;
        this.content = content;
        this.author = autuor;
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
