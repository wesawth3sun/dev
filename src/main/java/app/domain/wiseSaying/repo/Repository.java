package app.domain.wiseSaying.repo;

import app.domain.wiseSaying.WiseSaying;

import java.util.List;

public interface Repository {

    WiseSaying save(String content, String author);
    WiseSaying getId(int id);
    void delete(WiseSaying wiseSaying);
    List<WiseSaying> getList();


}
