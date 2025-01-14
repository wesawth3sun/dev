

package wiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Controller controller;
    private final Scanner scanner;

    public App(Controller controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void run() {

        controller.makeTestData();

        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine();
            if (command.equals("종료")) {
                System.out.println("명언 앱을 종료합니다.");
                break;
            } else if (command.equals("등록")) {
                controller.write();
            } else if (command.equals("목록")) {
                controller.print();
            } else if (command.startsWith("삭제?id=")) {
                int id = Integer.parseInt(command.substring(6));
                controller.removeForId(id);
            } else if (command.startsWith("수정?id=")) {
                int id = Integer.parseInt(command.substring(6));
                controller.modify(id);
            }
        }
    }
}