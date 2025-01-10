package app.domian.wiseSaying;

import app.domain.wiseSaying.repo.FileRepo;
import app.domain.wiseSaying.repo.Repository;
import app.standard.TestBot;
import app.standard.Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class CotrollerTest {

    Repository repository = new FileRepo();

    @Test
    void t1() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    void t2() {
        //app.App app = new app.App();
        //String run = app.run();

        //aaa가 제대로 출력되는가? -> 이를 사람이 검증하지 않고 자동화 도구를 통해서 확인
        //assertThat(run).isEqualTo("aaa");
    }

    @Test
    void t3() {
        String run = TestBot.run("");
        assertThat(run).containsSubsequence("명령 ) ", "명언앱을 종료합니다.");
    }

    @Test
    @DisplayName("명령을 여러개 입력할 수 있다")
    void t4() {
        String run = TestBot.run("""
                등록
                즐길 수 없다면 피해라
                작자 미상
                등록
                즐길 수 없다면 피해라
                작자 미상
                """);
        assertThat(run).contains("명령 ) ")
                .contains("명령 ) ");
        long count = run.split("명령 \\) ").length - 1;
        //-1을 하는 이유는 분할된 부분의 개수가 아닌 구분자의 개수를 세기 위함
        assertThat(count).isEqualTo(3);
    }


    @Test
    @DisplayName("앱 시작 시 '== 명언 앱 ==' 출력")
    void t5() {

        String run = TestBot.run("");

        assertThat(run)
                .containsSubsequence("== 명언 앱 ==", "명령 ) ", "명언앱을 종료합니다.");
        //containsSubsequence(): 출력 순서를 보장
    }

    @Test
    @DisplayName("등록 - 명언 1개 입력")
    void t6() {
        //""" """ -> 텍스트 블록
        String run = TestBot.run("""
                등록
                즐길 수 없다면 피해라
                작자 미상
                """);

        assertThat(run)
                .containsSubsequence("명령 ) ", "명언 : ", "작가 :");
        //containsSubsequence(): 출력 순서를 보장
    }

    @Test
    @DisplayName("등록 - 명언 1개 입력, 명언 번호 출력")
    void t7() {
        //""" """ -> 텍스트 블록
        String run = TestBot.run("""
                등록
                즐길 수 없다면 피해라
                작자 미상
                """);

        assertThat(run).contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("등록 - 명언 2개 입력, 명언 번호 증가")
    void t8() {
        //""" """ -> 텍스트 블록
        String run = TestBot.run("""
                등록
                즐길 수 없다면 피해라
                작자 미상
                등록
                즐길 수 없다면 피해라
                작자 미상
                """);

        assertThat(run).containsSubsequence("1번 명언이 등록되었습니다.",
                "2번 명언이 등록되었습니다");
    }

    @Test
    @DisplayName("등록 - 명언 2개 입력, 명언 번호 증가")
    void t9() {
        //""" """ -> 텍스트 블록
        String run = TestBot.run("""
                등록
                즐길 수 없다면 피해라
                작자 미상
                등록
                아는 것이 힝이다
                작자 미상
                목록
                """);

        assertThat(run).contains("번호 / 명언 / 작가")
                .contains(" --------------------- ")
                .contains("2 / 아는 것이 힝이다 / 작자 미상")
                .contains("1 / 즐길 수 없다면 피해라 / 작자 미상");
    }

    @Test
    @DisplayName("삭제 - id를 이용해서 해당 id의 명언을 삭제할 수 있다. 입력: 삭제?id=2")
    void test10() {
        String string = TestBot.run("""
                등록
                즐길 수 없다면 피해라
                작자 미상
                등록
                아는 것이 힝이다
                작자 미상
                삭제?id=1
                목록
                """);
        assertThat(string).contains("1번 명언이 삭제되었습니다")
                .contains("2 / 아는 것이 힝이다 / 작자 미상")
                .doesNotContain("1 / 즐길 수 없다면 피해라 / 작자 미상");

    }

    @Test
    @DisplayName("삭제 예외처리 - 없는 id로 삭제를 실행하면 예외 처리 메시지가 나온다")
    void test11() {
        String string = TestBot.run("""
                등록
                즐길 수 없다면 피해라
                작자 미상
                등록
                아는 것이 힝이다
                작자 미상
                삭제?id=1
                목록
                삭제?id=1
                """);
        assertThat(string).contains("1번 명언이 삭제되었습니다")
                .contains("2 / 아는 것이 힝이다 / 작자 미상")
                .doesNotContain("1 / 즐길 수 없다면 피해라 / 작자 미상")
                .contains("1번 명언은 존재하지 않습니다.");

    }

    @Test
    @DisplayName("수정 기능 구현")
    void test12() {
        String string = TestBot.run("""
                등록
                즐길 수 없다면 피해라
                작자 미상
                등록
                아는 것이 힝이다
                작자 미상
                수정?id=1
                졸려요
                서니
                목록
                삭제?id=1
                수정?id=1
                """);

        assertThat(string).contains("1번 명언이 수정되었습니다.")
                .contains("1 / 졸려요 / 서니")
                .contains("1번 명언이 삭제되었습니다")
                .contains("1번 명언은 존재하지 않습니다.");

    }

    @Test
    @DisplayName("목록 - 명언이 없을 때")
    void t13() {
        String string = TestBot.run("""
                목록
                """);
        assertThat(string).contains("등록된 명언이 없습니다.");
    }

    @Test
    @DisplayName("빌드")
    void t14() {
        String string = TestBot.run("""
                등록
                즐길 수 없다면 피해라
                작자 미상
                등록
                아는 것이 힝이다
                작자 미상
                빌드
                """);

        assertThat(Files.exists(Path.of(repository.getBuildPath()))).isTrue();
    }

    @Test
    @DisplayName("검색 기능 구현")
    void t15() {
        String string = TestBot.run("""
                등록
                즐길 수 없다면 피해라
                작자 미상
                등록
                아는 것이 힝이다
                작자 미상
                목록?keywordType=content&keyword=아는
                """);
        assertThat(string)
                .contains("2 / 작자 미상 / 아는 것이 힝이다")
                .doesNotContain("1 / 작자 미상 / 즐길 수 없다면 피해라");
    }

    @Test
    @DisplayName("검색 기능 구현")
    void t16() {
        TestBot.makeSample(10);
        String string = TestBot.run("""
                목록
                """);
        assertThat(string)
                .contains("1 / 명언1 / 작가1")
                .contains("10 / 명언10 / 작가10");
    }
}
