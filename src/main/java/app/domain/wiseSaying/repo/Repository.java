package app.domain.wiseSaying.repo;

import app.domain.wiseSaying.WiseSaying;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Repository {

    WiseSaying save(String content, String author);
    List<WiseSaying> getList();
    WiseSaying getId(int id);
    void deleteById(int i);
    Optional<WiseSaying> findById(int i);
}
