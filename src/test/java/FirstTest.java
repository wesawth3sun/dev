import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstTest {

    @Test
    void t1() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    void t2() {
        TestApp app = new TestApp();
        //String run = app.run();

        //aaa가 제대로 출력되는가? -> 이를 사람이 검증하지 않고 자동화 도구를 통해서 확인
        //assertThat(run).isEqualTo("aaa");
    }

    @Test
    void t3() {
        Scanner scanner = new Scanner("종료\n");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        TestApp app = new TestApp();
        app.run();

        assertThat(out.toString()).contains("명언앱을 종료합니다.");
    }

    @Test
    @DisplayName("앱 시작 시 '== 명언 앱 ==' 출력")
    void t4() {
        //red 단계: 실패하는 테스트 케이스 작성
        Scanner scanner = new Scanner("종료\n");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        TestApp app = new TestApp();
        app.run();

        assertThat(out.toString())
                .contains("== 명언 앱 ==")
                .contains("명언앱을 종료합니다.");
    }
}
