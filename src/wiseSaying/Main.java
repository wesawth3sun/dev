package wiseSaying;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        App app = new App(new Controller(new Scanner(System.in), new Service()));
        app.run();
    }
}

