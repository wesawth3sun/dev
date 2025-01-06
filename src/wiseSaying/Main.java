package wiseSaying;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Repository repository = new Repository();
        Service service = new Service(repository);
        Controller controller = new Controller(service, repository, new Scanner(System.in));

        App app = new App(controller, new Scanner(System.in));
        app.run();
    }
}

