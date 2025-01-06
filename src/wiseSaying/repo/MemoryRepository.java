package wiseSaying.repo;

import wiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class MemoryRepository implements Repository {

    private List<WiseSaying> list = new ArrayList<>();
    public static int lastId = 1;

    public WiseSaying findById(int id) {
        for (WiseSaying wiseSaying : list) {
            if (wiseSaying.getId() == id) {
                return wiseSaying;
            }
        }
        return null;
    }

    public WiseSaying add(String content, String author) {
        WiseSaying e = new WiseSaying(lastId, content, author);
        list.add(e);
        System.out.println(lastId + " 번 명업이 등록되었습니다.");
        lastId++;
        return e;
    }

    public void delete(WiseSaying wiseSaying) {
        list.remove(wiseSaying);
    }

    public List<WiseSaying> getList() {
        return list;
    }

    public void update(WiseSaying wiseSaying) {

    }
}
