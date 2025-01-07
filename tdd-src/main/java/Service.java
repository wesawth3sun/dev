import java.util.List;

public class Service {
    //비즈니스 로직
    //service 클래스에서는 입출력을 해서는 안 됨!!!

    private final Repository repository;


    public Service(Repository repository) {
        this.repository = repository;
    }

    public void write(String content, String author) {
        repository.save(content, author);
    }

    public int getLastNo() {
        return repository.getLastNo();
    }

    public List<WiseSaying> getList() {
        return repository.getList();
    }

}
