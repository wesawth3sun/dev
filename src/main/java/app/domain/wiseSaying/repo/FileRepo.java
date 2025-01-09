package app.domain.wiseSaying.repo;

import app.domain.wiseSaying.WiseSaying;
import app.standard.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static app.standard.Util.*;

public class FileRepo implements Repository{
    //데이터베이스와의 직접적인 상호 작용
    //CRUD(Create, Read, Update, Delete) 작업 수행
    //저장소의 역할

    private int lastNo;

    public FileRepo() {
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
        //파일들을 전체 다 가져오고, 하나씩 읽어서 list로 반환한다
        String directory = "db/wiseSaying";
        List<Map<String, Object>> jsonDataList = jsonUtils.readAllJsonFromDir(directory);
        return jsonDataList.stream().map(map -> new WiseSaying(
                ((Number) map.get("id")).intValue(),
                (String) map.get("content"),
                (String) map.get("author"))).collect(Collectors.toList());
                //stream() 메서드와 map() 연산을 사용하면
                //forEach를 사용하지 않고도 리스트의 각 요소를 변환할 수 있음
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
        return findById(id).orElse(null);
        //orElse(null)를 사용하여 Optional 내부의 WiseSaying 객체를 추출
    }

}
