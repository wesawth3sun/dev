package wiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {

    private List<WiseSaying> list = new ArrayList<>();
    static int lastId = 1;

    public WiseSaying findById(int id) {
        for (WiseSaying wiseSaying : list) {
            if (wiseSaying.getId() == id) {
                return wiseSaying;
            }
        }
        return null;
    }

    public void update(WiseSaying wiseSaying, String newContent, String newAuthor) {
        if (newContent != null && !newContent.isBlank()) {
            wiseSaying.setContent(newContent);
        }
        if (newContent != null && !newContent.isBlank()) {
            wiseSaying.setAuthor(newAuthor);
        }
    }

    public void add(String content, String author) {
        list.add(new WiseSaying(lastId, content, author));
        System.out.println(lastId + " 번 명업이 등록되었습니다.");
        lastId++;
    }

    public void delete(int index) {
        list.remove(index);
    }

    public List<WiseSaying> getList() {
        return list;
    }
}