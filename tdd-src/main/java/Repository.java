import java.util.ArrayList;
import java.util.List;

public class Repository {
    //데이터베이스와의 직접적인 상호 작용
    //CRUD(Create, Read, Update, Delete) 작업 수행
    //저장소의 역할

    private int lastNo;
    private final List<WiseSaying> list;

    public Repository() {
        this.list = new ArrayList<>();
        lastNo = 1;
    }

    public void save(String content, String author) {
        list.add(new WiseSaying(lastNo, content, author));
        lastNo++;
    }


    public int getLastNo() {
        return lastNo;
    }
    public List<WiseSaying> getList() {
        return list;
    }
}
