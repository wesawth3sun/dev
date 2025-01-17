package app.repo;

import app.main.WiseSaying;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import static app.util.FileUtil.*;

public class FileRepository implements Repository{

    private int lastId;

    public FileRepository() {
        this.lastId = loadLastId();
    }

    @Override
    public List<WiseSaying> getList() {
        List<WiseSaying> list = new LinkedList<>();
        //기본 경로에 있는 모든 파일 폴더를 가져옴
        File folder = new File(FILE_PATH);
        //파일들을 순서대로 반환
        File[] files = folder.listFiles((dir, name) ->
                name.endsWith(".json"));
        if (files != null) {
            for (File file : files) {
                //id의 시작 번호는 실제로 저장된 파일의 이름에 따라 결정
                //WiseSaying 객체의 id 값이 파일 이름에 사용되므로, 저장되는 파일 이름은 WiseSaying 객체의 id와 일치
                int id = Integer.parseInt(file.getName().replace(".json", ""));
                WiseSaying wiseSaying = loadWiseSaying(id);
                if (wiseSaying != null) {
                    list.add(wiseSaying);
                }
            }
        }
        return list;
    }

    @Override
    public int getLastId() {
        return lastId;
    }

    @Override
    public void add(String content, String author) {
        lastId++;
        WiseSaying wiseSaying = new WiseSaying(lastId, author, content);
        saveWiseSaying(wiseSaying);
        saveLastId(lastId);
    }

    @Override
    public boolean remove(int number) {
        WiseSaying wiseSaying = loadWiseSaying(number);
        if (wiseSaying != null) {
            deleteWiseSaying(number);
            if (number == lastId) {
                lastId--;
                saveLastId(lastId);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean update(int number, String newAuthor, String newContent) {
        WiseSaying wiseSaying = loadWiseSaying(number);
        if (wiseSaying != null) {
            wiseSaying.setAuthor(newAuthor);
            wiseSaying.setContent(newContent);
            saveWiseSaying(wiseSaying);
            return true;
        }
        return false;
    }

    @Override
    public WiseSaying getById(int id) {
        return loadWiseSaying(id);
    }
}
