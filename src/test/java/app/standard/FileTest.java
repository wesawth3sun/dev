package app.standard;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static app.standard.Util.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {

    @BeforeAll
    @DisplayName("테스트 시작 전에 한 번만 실행")
    static void beforeAll() {
        String dirPath = "temp";
        fileUtils.createDir(dirPath);
    }

    @AfterAll
    @DisplayName("테스트 끝난 후 한 번만 실행")
    static void afterAll() {
        String dirPath = "temp";
        fileUtils.deleteDir(dirPath);
    }

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
        fileUtils.write(file, "Hello, World!");
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
        String file = "temp/test.txt";
        fileUtils.createFile(file);
        assertThat(Files.exists(Path.of(file))).isTrue();
        //파일 생성이 잘 됐는지 체크
        fileUtils.delete(file);
        //test3 파일 삭제
        assertThat(Files.exists(Path.of(file))).isFalse();
        //존재 여부 확인
    }

    @Test
    @DisplayName("파일을 없는 폴더에 생성해도 폴더를 생성하고, 파일을 생성할 수 있게 함")
    void test6() {
        String path = "temp/temp2/test.txt";
        fileUtils.write(path, "");
        boolean exists = Files.exists(Path.of(path));

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("비어 있지 않는 폴더 삭제 시 내부의 파일들과 함께 폴더를 삭제하도록 구현")
    void test7() throws IOException {
        String basePath = "test";

        // 테스트 디렉토리 및 파일 생성
        fileUtils.write(basePath + "/file1.txt", "Test content 1");
        fileUtils.write(basePath + "/file2.txt", "Test content 2");
        fileUtils.write(basePath + "/subDir/file3.txt", "Test content 3");

        fileUtils.deleteForce(basePath);
        //강제 삭제 적용
        assertThat(Files.exists(Paths.get(basePath))).isFalse();
        assertThat(Files.exists(Paths.get(basePath, "file1.txt"))).isFalse();
        assertThat(Files.exists(Paths.get(basePath, "subDir", "file3.txt"))).isFalse();
        assertThat(Files.exists(Paths.get(basePath))).isFalse();
    }
}
