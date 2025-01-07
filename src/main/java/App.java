import java.util.Scanner;

public class App {
    //명령만 내리는 역할

    private final Scanner scanner;
    private final Controller controller;

    public App(Scanner scanner, Controller controller) {
        this.scanner = scanner;
        this.controller = new Controller(scanner);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.println("명령 ) ");
            String str = scanner.nextLine();

            if (str.equals("종료")) {
                System.out.println("명언앱을 종료합니다.");
                break;
            } else if (str.equals("등록")) {
                controller.write();
            } else if (str.equals("목록")) {
                controller.print();
            }
        }

    }
}
