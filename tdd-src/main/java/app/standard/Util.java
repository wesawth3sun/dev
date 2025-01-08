package app.standard;

import java.io.IOException;
import java.nio.file.*;

public class Util {

    public static class fileUtils {
        public static void test() {
            System.out.println("파일 유틸 테스트");
        }

        public static void createFile(String path) {
            write(path, "");
        }

        public static String readFile(String path) throws IOException {
            Path filePath = Paths.get(path);
            String readString = Files.readString(filePath);
            return readString;
        }

        public static void write(String path, String content) {
            Path filePath = Paths.get(path);
            //그냥 문자열은 안 들어가기 때문에 꼭 path 로 변환해 주기
            try {
                Files.writeString(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                System.out.println("파일 쓰기 실패");
                e.printStackTrace();
            }
        }

        public static void delete(String file) {
            Path path = Paths.get(file);
            try {
                Files.delete(path);
                System.out.println(file + " 파일이 삭제되었습니다.");
            } catch (IOException e) {
                System.out.println("파일 삭제 오류");
                e.printStackTrace();
            }
        }
    }
}
