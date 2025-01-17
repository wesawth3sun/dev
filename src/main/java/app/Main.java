package app;

import app.main.Controller;
import app.main.Service;
import app.repo.FileRepository;
import app.repo.Repository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Repository repository = new FileRepository();
        Service service = new Service(repository);
        Controller controller = new Controller(scanner, service);
        App app = new App(scanner, controller);
        app.run();
    }
}
