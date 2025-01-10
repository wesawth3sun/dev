package app.domain.wiseSaying.repo;

import app.domain.wiseSaying.WiseSaying;

import java.util.List;
import java.util.Optional;

public interface Repository {

    WiseSaying save(String content, String author);
    List<WiseSaying> getList();
    Optional<WiseSaying> findById(int id);
    WiseSaying getId(int id);
    void deleteById(int i);
    void build();
    String getBuildPath();

    List<WiseSaying> search(String keywordType, String keyword);
    void makeSampleData(int sampleCount);
}
