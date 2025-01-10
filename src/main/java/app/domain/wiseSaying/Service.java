package app.domain.wiseSaying;

import app.domain.wiseSaying.repo.FileRepo;
import app.domain.wiseSaying.repo.Repository;

import java.io.IOException;
import java.util.List;

public class Service {
    //비즈니스 로직
    //service 클래스에서는 입출력을 해서는 안 됨!!!

    private final Repository fileRepo;


    public Service() {
        this.fileRepo = new FileRepo();
    }

    public WiseSaying write(String content, String author){
        return fileRepo.save(content, author);
    }

    public List<WiseSaying> getList() {
        return fileRepo.getList();
    }

    public void delete(WiseSaying wiseSaying) {
        fileRepo.deleteById(wiseSaying.getId());
    }
    public WiseSaying findById(int id) {
        return fileRepo.getId(id);
    }

    public void update(WiseSaying wiseSaying, String newContent, String newAuthor) {
        wiseSaying.setContent(newContent);
        wiseSaying.setAuthor(newAuthor);
        //비즈니스 로직
        //기존의 것을 다른 것으로 교체, list에 저장하는 것은 아님
    }

    public void build() {
        fileRepo.build();
    }

    public List<WiseSaying> search(String keywordType, String keyword) {
        return fileRepo.search(keywordType, keyword);
    }

    public void makeSampleData(int sampleCount) {
        fileRepo.makeSampleData(sampleCount);
    }
}
