package app.standard;

import app.domain.wiseSaying.WiseSaying;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

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
}
