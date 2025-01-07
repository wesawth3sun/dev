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
        if (content != null && !content.isEmpty()) {
            this.content = content;
        }
    }

    public void setAuthor(String author) {
        if (author != null && !author.isEmpty()) {
            this.author = author;
        }
    }
}
