package app.domain.wiseSaying;

import java.util.*;

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

    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", this.id);
        map.put("content", this.content);
        map.put("author", this.author);
        return map;
    }

    public static WiseSaying mapToWise(Map<String, Object> map) {
        int id = (int) map.get("id");
        String content = String.valueOf(map.get("content"));
        String author = String.valueOf(map.get("author"));
        return new WiseSaying(id, content, author);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        WiseSaying that = (WiseSaying) object;
        return id == that.id && Objects.equals(content, that.content) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, author);
    }
}
