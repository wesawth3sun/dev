package wiseSaying;

import wiseSaying.repo.MemoryRepository;
import wiseSaying.repo.Repository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Repository repository = new MemoryRepository();
        Service service = new Service(repository);
        Controller controller = new Controller(service, new Scanner(System.in));

        App app = new App(controller, new Scanner(System.in));
        app.run();
    }
}

