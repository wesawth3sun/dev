package app.standard;

import app.domain.wiseSaying.WiseSaying;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import static app.standard.Util.*;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {

    @Test
    @DisplayName("Map을 json으로 변환, 속성이 한 개")
    void t1() {
        Map<String, Object> map = Map.of("name", "홍길동");
        String jsonString = jsonUtils.mapToJson(map);
        //객체를 저장할 거니까 object 저장하기
        assertThat(jsonString).isEqualTo("""
                {
                    "name" : "홍길동"
                }""");
    }

    @Test
    @DisplayName("Map을 json으로 변환, 속성이 두 개")
    void t2() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", "홍길동");
        map.put("home", "서울");
        String jsonString = jsonUtils.mapToJson(map);
        //객체를 저장할 거니까 object 저장하기
        assertThat(jsonString).isEqualTo("""
                {
                    "name" : "홍길동",
                    "home" : "서울"
                }""");
    }

    @Test
    @DisplayName("Map을 json으로 변환, 속성이 두 개")
    void t3() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", "홍길동");
        map.put("home", "서울");
        map.put("age", 20);
        String jsonString = jsonUtils.mapToJson(map);
        assertThat(jsonString).isEqualTo("""
                {
                    "name" : "홍길동",
                    "home" : "서울",
                    "age" : 20
                }""");
    }

    @Test
    @DisplayName("WiseSaying을 Map으로 변환 -> Json 으로 변환")
    void t4() {
        WiseSaying wiseSaying = new WiseSaying(1, "aaa", "bbb");
        Map<String, Object> map = wiseSaying.toMap();
        String jsonString = jsonUtils.mapToJson(map);
        assertThat(jsonString).isEqualTo("""
                {
                    "id" : 1,
                    "content" : "aaa",
                    "author" : "bbb"
                }""");
    }

    @Test
    @DisplayName("WiseSaying을 Map으로 변환 -> Json 으로 변환")
    void t5() throws IOException {
        WiseSaying wiseSaying = new WiseSaying(1, "aaa", "bbb");
        Map<String, Object> map = wiseSaying.toMap();
        String jsonString = jsonUtils.mapToJson(map);
        String filePath = "test/%d.json".formatted(wiseSaying.getId());
        jsonUtils.writeMapToFile(filePath, map);

        assertThat(Files.exists(Path.of(filePath))).isTrue();
        //파일이 제대로 생성되었는지
        String readString = fileUtils.readFile(filePath);
        assertThat(readString).isEqualTo("""
                {
                    "id" : 1,
                    "content" : "aaa",
                    "author" : "bbb"
                }""");
        //해당 파일의 내용이 정확한지
    }

    @Test
    @DisplayName("Json 문자열을 Map으로 변환")
    void t6() throws IOException {
        String jsonString = """
                {
                    "id" : 1,
                    "content" : "aaa",
                    "author" : "bbb"
                }
                """;
        Map<String, Object> map = jsonUtils.readJsonToMap(jsonString);
        assertThat(map).hasSize(3)
                .containsEntry("id", 1)
                .containsEntry("content", "aaa")
                .containsEntry("author", "bbb");
    }

    @Test
    @DisplayName("파일명을 넘기면 Map으로 읽어오기")
    void t7() throws IOException {
        String filePath = "test/%d.json".formatted(1);
        Map<String, Object> map = jsonUtils.readFileToMap(filePath);
        assertThat(map).hasSize(3)
                .containsEntry("id", 1)
                .containsEntry("content", "aaa")
                .containsEntry("author", "bbb");
    }

    @Test
    @DisplayName("Map을 받아서 WiseSaying 객체로 변환하기")
    void t8() throws IOException {
        String filePath = "test/%d.json".formatted(1);
        Map<String, Object> map = jsonUtils.readFileToMap(filePath);
        WiseSaying wiseSaying = WiseSaying.mapToWise(map);
        assertThat(wiseSaying.getId()).isEqualTo(1);
        assertThat(wiseSaying.getContent()).isEqualTo("aaa");
        assertThat(wiseSaying.getAuthor()).isEqualTo("bbb");
    }
}

