package app.domain.wiseSaying.repo;

import app.domain.wiseSaying.WiseSaying;
import app.standard.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static app.standard.Util.*;

public class FileRepo implements Repository{
    //데이터베이스와의 직접적인 상호 작용
    //CRUD(Create, Read, Update, Delete) 작업 수행
    //저장소의 역할

    private int lastNo;
    private final List<WiseSaying> list;

    public FileRepo() {
        this.list = new ArrayList<>();
        lastNo = 1;
        System.out.println("파일 DB 사용");

    }

    public WiseSaying save(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(lastNo, content, author);
        jsonUtils.writeMapToFile("db/wiseSaying/%d.json".formatted(wiseSaying.getId()), wiseSaying.toMap());
        lastNo++;
        return wiseSaying;
    }

    public List<WiseSaying> getList() {
        return list;
    }


    public void deleteById(int id) {
        fileUtils.delete("db/wiseSaying/%d.json".formatted(id));
    }

    @Override
    public Optional<WiseSaying> findById(int id) {
        String path = "db/wiseSaying/%d.json".formatted(id);
        try {
            Map<String, Object> map = jsonUtils.readFileToMap(path);
            if (map != null && !map.isEmpty()) {
                WiseSaying wiseSaying = WiseSaying.mapToWise(map);
                return Optional.of(wiseSaying);
            }
        } catch (IOException e) {
            System.err.println("파일 읽기 중 오류 발생: " + e.getMessage());
        }
        return Optional.empty();
    }

    public WiseSaying getId(int id) {
        for (WiseSaying wiseSaying : list) {
            if (wiseSaying.getId() == id) {
                return wiseSaying;
            }
        }
        return null;
    }

}
