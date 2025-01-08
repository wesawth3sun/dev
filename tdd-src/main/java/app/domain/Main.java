package app.domain;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        test2();
    }

    private static void test2() {
        //출력문의 값을 문자열로 받아서 테스트봇에게 줄 수 있다

        PrintStream origin = System.out;
        //내가 직접 보고 확인하는 게 아니라, 일부러 반환값을 바꿔서 출력하는 게 아니라
        //새로운 스트림을 만들어서 값을 저장하고 해당 값을 모니터가 아닌 테스트봇에게 전달해 주는 것
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        //setOut() -> 기본 출력은 모니터(콘솔)인데 다른 곳으로 세팅하겠다는 것
        System.out.println("hello");
        //모니터에 출력하려면 다시 set을 바꿔야 한다
        System.setOut(origin);
        String string = output.toString();
        System.out.println(string);
    }

    private static void test1() {
        Scanner scanner = new Scanner("등록\n즐길 수 없다면 피해라\n작자 미상");

        String string1 = scanner.nextLine();
        System.out.println(string1);

        String string2 = scanner.nextLine();
        System.out.println(string2);

        String string3 = scanner.nextLine();
        System.out.println(string3);

        // 테스트 봇 만들기
        //app.domain.App app = new app.domain.App(scanner);
        //app.run();
    }


}
