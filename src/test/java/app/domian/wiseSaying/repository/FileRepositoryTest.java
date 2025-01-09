package app.domian.wiseSaying.repository;

import app.domain.wiseSaying.WiseSaying;
import app.domain.wiseSaying.repo.FileRepo;
import app.domain.wiseSaying.repo.Repository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

import static app.standard.Util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class FileRepositoryTest {

    Repository repository = new FileRepo();

    @Test
    @DisplayName("명언 저장")
    void t1() throws IOException {
        WiseSaying wiseSaying = new WiseSaying(1, "aaa", "bbb");
        repository.save(wiseSaying.getContent(), wiseSaying.getAuthor());

        String filePath = "db/wiseSaying/1.json";
        boolean exists = Files.exists(Path.of(filePath));
        assertThat(exists).isTrue();

        Map<String, Object> map = jsonUtils.readFileToMap(filePath);
        WiseSaying restoredWiseSaying = wiseSaying.mapToWise(map);
        assertThat(restoredWiseSaying).isEqualTo(wiseSaying);
        //assertThat().isEqualTo()는 기본적으로 객체의 참조를 비교
        //내용이 같더라도 다른 인스턴스라면 다르다고 판단
        //WiseSaying 클래스에 equals()와 hashCode() 메서드를 오버라이드 필수
    }

    @Test
    @DisplayName("명언 삭제")
    void t2(){
        WiseSaying wiseSaying = new WiseSaying(1, "aaa", "bbb");
        repository.save(wiseSaying.getContent(), wiseSaying.getAuthor());
        String filePath = "db/wiseSaying/1.json";
        repository.deleteById(1);
        boolean exists = Files.exists(Path.of(filePath));
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("명언 찾기")
    void t3(){
        WiseSaying wiseSaying = new WiseSaying(1, "aaa", "bbb");
        repository.save(wiseSaying.getContent(), wiseSaying.getAuthor());
        String filePath = "db/wiseSaying/1.json";
        Optional<WiseSaying> optionalWiseSaying = repository.findById(1);
        //Optional은 값이 존재할 수도, 존재하지 않을 수도 있는 컨테이너 객체
        //ID가 1인 WiseSaying이 존재하면 Optional에 그 객체가 포함
        //존재하지 않으면 빈 Optional이 반환
        WiseSaying foundWiseSaying = optionalWiseSaying.orElse(null);
        //orElse(null)는 Optional에서 값을 추출하는 메서드
        //Optional이 값을 포함하고 있다면 (즉, WiseSaying 객체가 존재한다면) 그 값을 반환
        //Optional이 비어있다면 (즉, WiseSaying 객체가 존재하지 않는다면) null을 반환
        assertThat(foundWiseSaying).isNotNull();
        assertThat(foundWiseSaying).isEqualTo(wiseSaying);
    }
}