package app.util;

import app.main.WiseSaying;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        try {
            Path filePath = Path.of(FILE_PATH + "lastId.txt");
            Files.createDirectories(filePath.getParent());
            Files.writeString(filePath, String.valueOf(lastId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadLastId() {
        Path filePath = Paths.get(FILE_PATH + "lastId.txt");

        try {
            if (Files.exists(filePath)) {
                String lastIdStr = Files.readString(filePath);
                return Integer.parseInt(lastIdStr.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
