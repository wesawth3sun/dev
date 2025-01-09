package app.standard;

import app.App;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TestBot {

    public static String run(String input) {
        Scanner scanner = new Scanner(input + "종료\n");
        //라인의 역할은 사용자 입력을 시뮬레이션하는 것
        //실제로 콘솔에서 사용자 입력을 기다리지 않고도 프로그램의 동작을 테스트

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        App app = new App(scanner);
        app.run();

        return out.toString();
    }
}
