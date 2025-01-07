import java.util.Scanner;

public class App {
    //명령만 내리는 역할

    private final Scanner scanner;
    private final Controller controller;

    public App(Scanner scanner, Controller controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.println("명령 ) ");
            String str = scanner.nextLine();
            switch (str) {
                case "종료":
                    System.out.println("명언앱을 종료합니다.");
                    break;
                case "등록":
                    controller.actionWrite();
                case "목록":
                    controller.actionPrint();
                default:
                    System.out.println("다시 입력해 주세요.");
                    return;
            }

        }
    }
}

