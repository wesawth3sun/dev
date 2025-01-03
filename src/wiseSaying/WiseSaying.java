package wiseSaying;

public class WiseSaying {
    private int id; //명언 넘버
    private String content;
    private String autuor;

    public WiseSaying(int id, String content, String autuor) {
        this.id = id;
        this.content = content;
        this.autuor = autuor;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAutuor() {
        return autuor;
    }
}
