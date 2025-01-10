package app;

import app.domain.wiseSaying.Controller;

import java.io.IOException;
import java.util.Scanner;

public class App {
    //명령만 내리는 역할

    private final Scanner scanner;
    private final Controller controller;

    public App(Scanner scanner) {
        this.scanner = scanner;
        this.controller = new Controller(this.scanner);
    }

    public void run(){
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령 ) ");
            String str = scanner.nextLine();

            String[] split = str.split("\\?");

            switch (split[0]) {
                case "종료":
                    System.out.println("명언앱을 종료합니다.");
                    return;
                case "등록":
                    controller.actionWrite();
                    break;
                case "목록":
                    controller.actionPrint();
                    break;
                case "삭제":
                    controller.actionDelete(Integer.parseInt(split[1].substring(3)));
                    break;
                case "수정":
                    controller.actionModify(Integer.parseInt(split[1].substring(3)));
                    break;
                case "빌드":
                    controller.actionBuild();

            }
        }
    }
}

