package wiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public WiseSaying findById(int id) {
        return repository.findById(id);
    }

    public WiseSaying add(String content, String author) {
        return repository.add(content, author);
    }

    public void update(WiseSaying wiseSaying, String newContent, String newAuthor) {
        wiseSaying.setContent(newContent);
        wiseSaying.setAuthor(newAuthor);
        //비즈니스 로직
        //기존의 것을 다른 것으로 교체, list에 저장하는 것은 아님

        repository.update(wiseSaying);
    }

    public void delete(WiseSaying wiseSaying) {
        repository.delete(wiseSaying);
    }

    public List<WiseSaying> getList() {
        return repository.getList();
    }

}