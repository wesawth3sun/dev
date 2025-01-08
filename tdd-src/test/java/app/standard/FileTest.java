package app.standard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static app.standard.Util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;


public class FileTest {

    @Test
    @DisplayName("최초의 파일 테스트")
    void test1() {
        fileUtils.test();
    }

    @Test
    @DisplayName("파일 생성. 내용이 없는 빈 파일 생성")
    void test2() {
        String file = "temp/test.txt";
        fileUtils.createFile(file);
        assertThat(Files.exists(Paths.get(file))).isTrue();
    }

    @Test
    @DisplayName("파일 내용 조회")
    void test3() throws IOException {
        String testContent = "Hello, World!";

        String file = "temp/test.txt";
        String readContent = fileUtils.readFile(file);

        assertThat(readContent).isEqualTo(testContent);
    }

    @Test
    @DisplayName("파일 내용 수정")
    void test4() throws IOException {
        String file = "temp/test.txt";
        fileUtils.write(file, "modify content");
        String readContent = fileUtils.readFile(file);

        assertThat(readContent).isEqualTo("modify content");
    }

    @Test
    @DisplayName("파일 삭제")
    void test5() {
        String file = "temp/test3.txt";
        fileUtils.createFile(file);
        assertThat(Files.exists(Path.of(file))).isTrue();
        //파일 생성이 잘 됐는지 체크
        fileUtils.delete(file);
        //test3 파일 삭제
        assertThat(Files.exists(Path.of(file))).isFalse();
        //존재 여부 확인
    }

}
