package wiseSaying;

import java.util.Scanner;

public class App {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int lastNo = 1;
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine();
            if (command.equals("등록")) {
                System.out.print("명언: ");
                String wiseSaying = scanner.nextLine();
                System.out.print("작가: ");
                String author = scanner.nextLine();
                System.out.println(lastNo + " 번 명업이 등록되었습니다.");
                lastNo++;
            } else if (command.equals("종료")) {
                System.out.println("명언 앱을 종료합니다.");
                break;
            }
        }
    }
}