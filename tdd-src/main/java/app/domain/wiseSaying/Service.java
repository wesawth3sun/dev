package app.domain.wiseSaying;

import java.util.List;

public class Service {
    //비즈니스 로직
    //service 클래스에서는 입출력을 해서는 안 됨!!!

    private final Repository repository;


    public Service(Repository repository) {
        this.repository = repository;
    }

    public WiseSaying write(String content, String author) {
        return repository.save(content, author);
    }

    public List<WiseSaying> getList() {
        return repository.getList();
    }

    public void delete(WiseSaying wiseSaying) {
        repository.delete(wiseSaying);
    }
    public WiseSaying findById(int id) {
        return repository.getId(id);
    }

    public void update(WiseSaying wiseSaying, String newContent, String newAuthor) {
        wiseSaying.setContent(newContent);
        wiseSaying.setAuthor(newAuthor);
        //비즈니스 로직
        //기존의 것을 다른 것으로 교체, list에 저장하는 것은 아님
    }
}
