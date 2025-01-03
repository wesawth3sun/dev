package wiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int lastId = 1;
        List<WiseSaying> list = new ArrayList<>();
        list.add(new WiseSaying(lastId++, "즐길 수 없으면 피해라", "작자 미상"));
        list.add(new WiseSaying(lastId++, "행복해서 웃는 게 아니다... 웃어서 행복한 거다...", "작자 미상"));

        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine();

            if (command.equals("종료")) {
                System.out.println("명언 앱을 종료합니다.");
                break;

            } else if (command.equals("등록")) {
                System.out.print("명언: ");
                String content = scanner.nextLine();

                System.out.print("작가: ");
                String author = scanner.nextLine();

                WiseSaying wiseSaying = new WiseSaying(lastId, content, author);
                list.add(wiseSaying);

                System.out.println(lastId + " 번 명업이 등록되었습니다.");
                lastId++;
            } else if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("-------------------");
                for (WiseSaying wiseSaying : list) {
                    System.out.println(wiseSaying.getId() + " / "
                    + wiseSaying.getContent() + " / "
                    + wiseSaying.getAutuor());
                }
            }
        }
    }
}