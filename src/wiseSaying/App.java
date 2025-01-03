package wiseSaying;

import java.util.Scanner;

public class App {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int lastNo = 1;
        String content = "";
        String author = "";

        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine();

            if (command.equals("종료")) {
                System.out.println("명언 앱을 종료합니다.");
                break;
            } else if (command.equals("등록")) {
                System.out.print("명언: ");
                content = scanner.nextLine();

                System.out.print("작가: ");
                author = scanner.nextLine();

                System.out.println(lastNo + " 번 명업이 등록되었습니다.");
                lastNo++;
            } else if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("-------------------");
                System.out.println(lastNo + " / " + author + " / " + content);
            }
        }
    }
}