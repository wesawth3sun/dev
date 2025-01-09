package app.standard;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.Set;

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
                Files.createDirectories(filePath.getParent());
                Files.writeString(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                System.out.println("파일 쓰기 실패");
                e.printStackTrace();
            }
        }

        public static void delete(String file) {
            Path path = Paths.get(file);
            try {
                if (Files.exists(path)) {
                    Files.delete(path);
                    System.out.println(file + " 파일이 삭제되었습니다.");
                } else {
                    return;
                }
            } catch (IOException e) {
                System.out.println("파일 삭제 오류");
                e.printStackTrace();
            }
        }

        public static void createDir(String dirPath) {
            Path path = Paths.get(dirPath);
            try {
                Files.createDirectories(path);
                System.out.println("디렉토리가 성공적으로 생성되었습니다: " + dirPath);
            } catch (IOException e) {
                System.err.println("디렉토리 생성 중 오류 발생: " + e.getMessage());
            }
        }

        public static void deleteDir(String dirPath) {
            Path path = Paths.get(dirPath);
            try {
                Files.delete(path);
                System.out.println("디렉토리가 성공적으로 삭제되었습니다: " + dirPath);
            } catch (IOException e) {
                System.err.println("디렉토리 삭제 중 오류 발생: " + e.getMessage());
            }
        }

        public static void deleteForce(String path) {
            Path dirPath = Paths.get(path);
            try {
                Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>() {
                    //walkFileTree(): 메서드는 주어진 경로부터 시작해 모든 하위 디렉토리와 파일을 순회
                    //SimpleFileVisitor: 파일 트리 순회 시 각 파일과 디렉토리를 어떻게 처리할지 정의하는 클래스
                    //SimpleFileVisitor를 익명 클래스로 정의: 선언과 동시에 인스턴스를 생성
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        //visitFile 메서드: 각 파일을 방문할 때 호출
                        //SimpleFileVisitor 클래스의 visitFile 메서드를 오버라이드한 것
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                        //각 파일을 삭제하고 계속해서 다음 파일로 진행
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exception) throws IOException {
                        //postVisitDirectory 메서드는 각 디렉토리의 내용을 모두 처리한 후 호출
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                        //디렉토리 자체를 삭제하고 계속해서 다음으로 진행
                    }
                });
            } catch (IOException ex) {
                System.out.println("파일, 디렉토리 삭제 오류: " + ex);
                ex.printStackTrace();
            }
        }
    }

    public static class jsonUtils {


        public static String mapToJson(Map<String, Object> map) {
            StringBuilder sb = new StringBuilder();
            sb.append("{\n");

            int size = map.size();
            int count = 0;

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                sb.append(String.format("    \"%s\" : ", key));
                //key는 항상 String이니까 String.format 해도 상관없음

                //키 포맷팅과 값 포맷팅을 분리
                if (value instanceof String) {
                    sb.append(String.format("\"%s\"", value));
                } else if (value instanceof Number) {
                    sb.append(value);
                } else {
                    //value의 타입이 예상치 못한 타입일 때 기본적인 문자열 처리를 제공
                    sb.append("\"" + value + "\"");
                }
                if (++count < size) {
                    sb.append(",");
                } //항목 처리 후 쉼표 추가, 마지막이면 쉼표 추가하지 않음
                sb.append("\n");
            }
            sb.append("}");

            return sb.toString();
        }
    }
}
