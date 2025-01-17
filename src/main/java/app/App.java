package app;

import app.main.Controller;

import java.util.Scanner;

public class App {

    private final Scanner scanner;
    private final Controller controller;

    public App(Scanner scanner, Controller controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void run() {

        System.out.println("=== 명언 앱 ===");

        while (true) {
            System.out.print("명령) ");
            String input = scanner.nextLine();

            if (input.equals("종료")) {
                break;
            }

            Command command = Command.parse(input);

            controller.process(command);
        }
        scanner.close();
    }
}


