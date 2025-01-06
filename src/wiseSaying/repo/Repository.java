package wiseSaying.repo;

import wiseSaying.WiseSaying;

import java.util.List;

public interface Repository {
    WiseSaying findById(int id);
    WiseSaying add(String content, String author);
    void delete(WiseSaying wiseSaying);
    List<WiseSaying> getList();
    void update(WiseSaying wiseSaying);

}
