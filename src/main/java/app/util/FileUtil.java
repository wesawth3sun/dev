package app.util;

import app.main.WiseSaying;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUtil {
    public static final String FILE_PATH = "db/wiseSaying/";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void saveWiseSaying(WiseSaying wiseSaying) {
        try {
            String json = objectMapper.writeValueAsString(wiseSaying);
            Path filePath = Paths.get(FILE_PATH + wiseSaying.getId() + ".json");
            Files.createDirectories(filePath.getParent());
            Files.writeString(filePath, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WiseSaying loadWiseSaying(int id) {
        try {
            File file = new File(FILE_PATH + id + ".json");
            if (file.exists()) {
                //ObjectMapper에게 JSON 데이터를 어떤 타입의 객체로 변환해야 하는지 정확히 알려주기 위해
                //WiseSaying의 클래스 메타 데이터를 넘김
                //ObjectMapper에게 JSON 데이터를 WiseSaying 객체의 구조에 맞게 파싱하라고 지시
                //json 파일은 구조화된 파일이기 때문에 이렇게 읽어올 수가 있음
                return objectMapper.readValue(file, WiseSaying.class);
            }
        } catch (IOException e) {
            System.err.println("파일 읽기 오류: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("JSON 파싱 오류: " + e.getMessage());
        }
        return null;
    }

    public static void deleteWiseSaying(int id) {
        File file = new File(FILE_PATH + id + ".json");
        if (file.exists()) {
            file.delete();
        }
    }

    public static void saveLastId(int lastId) {
        //Files.write() 메서드는 바이트 배열을 인자로 받기 때문에, 정수형 lastId를 직접 사용할 수 없음
        //따라서 tring.valueOf(lastId)를 사용하여 정수를 문자열로 변환
        try {
            Path filePath = Path.of(FILE_PATH + "lastId.txt");
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, String.valueOf(lastId).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadLastId() {
        Path filePath = Paths.get(FILE_PATH + "lastId.txt");

        //단순히 정수 값을 문자열 형태로 저장하는 평범한 텍스트 파일
        //구조화된 데이터가 아니며, 오직 하나의 값(마지막 ID)만 포함
        //바이트 배열로 읽고, 이를 문자열로 변환한 후에 정수로 변환
        //특별한 클래스 메타데이터가 필요하지 않음

            /*JSON 파일은 복잡한 데이터 구조를 가지고 있어 ObjectMapper와 같은 라이브러리를 사용하여 역직렬화해야 하고,
            lastId.txt 파일은 단순한 텍스트 데이터이므로 기본적인 파일 입출력 메서드를 사용하여 읽고 변환하면 된다*/
        try {
            if (Files.exists(filePath)) {
                byte[] bytes = Files.readAllBytes(filePath);
                String lastIdString = new String(bytes, UTF_8).trim();
                return Integer.parseInt(lastIdString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
