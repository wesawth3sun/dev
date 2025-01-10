package app.standard;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
                //주어진 파일 경로의 부모 디렉토리 경로를 얻음
                //부모 경로에 존재하지 않는 모든 디렉토리를 생성
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
        private static final ObjectMapper objectMapper = new ObjectMapper();
        //ObjectMapper 클래스를 사용하여 JSON 처리를 담당

        //wise -> map -> json(sting) -> file 변환

        public static String mapToJson(Map<String, Object> map) {

            //return문은 mapToJson 메서드의 결과를 반환하기 위해 사용
            //이 경우, 전체 스트림 연산의 결과가 메서드의 반환값
            return map.entrySet().stream().map(entry -> {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        String formattedValue = (value instanceof String) ? String.format("\"%s\"", value) :
                                (value instanceof Number) ? value.toString() : String.format("\"%s\"", value);
                        //(value instanceof String): 문자열인 경우 따옴표로 감쌈
                        //(value instanceof Number): 숫자인 경우 그대로 문자열로 변환
                        //그 외의 경우: 문자열로 변환하고 따옴표로 감쌈
                        return String.format("    \"%s\" : %s", key, formattedValue);
                    })
                    .collect(Collectors.joining(",\n", "{\n", "\n}"));
            //모든 키-값 쌍을 하나의 문자열로 결합
            //",\n": 각 키-값 쌍 사이에 쉼표와 줄바꿈을 삽입
        }


        //map을 넘기면 파일로 저장해 주는 역할
        public static String listToJson(List<Map<String, Object>> mapList) {
            return mapList.stream()
                    .map(jsonUtils::mapToJson)
                    .collect(Collectors.joining(",\n", "[\n", "\n]"));
        }

        public static void writeMapToFile(String filePath, Map<String, Object> map) {
            String jsonString = mapToJson(map);
            fileUtils.write(filePath, jsonString);
        }
        //json을 읽어서 map으로 변환할 수 있게끔 함

        public static Map<String, Object> readJsonToMap(String jsonString) throws IOException {
            return objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
                //objectMapper.readValue(): 사용하여 JSON 문자열을 파싱하고 Map으로 변환
                //TypeReference를 사용하여 변환할 타입을 Map<String, Object>로 지정
            });
        }
        //파일명을 넘기면 이를 읽어서 map으로 반환해 주는

        public static Map<String, Object> readFileToMap(String filePath) throws IOException {
            String readString = Files.readString(Path.of(filePath));
            //readString을 사용하는 것이 더 효율적
            return readJsonToMap(readString);
        }
        //모든 명언 파일을 가져오는 메서드

        public static List<Map<String, Object>> readAllJsonFromDir(String directory) {
            List<Map<String, Object>> list = new ArrayList<>();
            try {
                Files.list(Path.of(directory)).filter(path -> path.toString().endsWith(".json"))
                        //Files.list(): 지정된 디렉토리의 항목들(파일과 하위 디렉토리)을 나열
                        //디렉토리 내용을 Stream<Path> 형태로 반환
                        //Files.list() 메서드가 Stream<Path>를 직접 반환하기 때문에 추가로 .stream()을 호출할 필요가 없음!
                        //추가 호출 없이도 stream의 메서드를 사용할 수 있다
                        //Stream<Path>의 의미: 디렉토리 내의 각 항목(파일 또는 하위 디렉토리)이 Path 객체로 표현
                        //이 Path 객체들의 "흐름"을 Stream으로 제공
                        //Stream을 사용하면 각 Path에 대해 순차적으로 또는 병렬로 작업을 수행할 수 있음
                        .forEach(path -> {
                            try {
                                Map<String, Object> map = readFileToMap(path.toString());
                                list.add(map);
                                //블록 람다식 사용했지만 void 반환: 반환값이 없는 경우(void), return 문을 사용하지 않음
                            } catch (IOException e) {
                                System.err.println("파일 읽기 중 오류 발생: " + e.getMessage());
                            }
                        });
            } catch (IOException e) {
                System.err.println("디렉토리 읽기 중 오류 발생: " + e.getMessage());
            }
            return list;
        }
    }
}
