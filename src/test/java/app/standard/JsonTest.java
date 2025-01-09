package app.standard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
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

        Map<String, Object> map = Map.of("name", "홍길동"
        , "home", "서울");
        String jsonString = jsonUtils.mapToJson(map);
        //객체를 저장할 거니까 object 저장하기
        assertThat(jsonString).isEqualTo("""
                {
                    "name" : "홍길동",
                    "home" : "서울"
                }""");
    }
}
