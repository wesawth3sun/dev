package app.main;

import app.Command;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Controller {

    private final Scanner scanner;
    private final Service service;

    public Controller(Scanner scanner, Service service) {
        this.scanner = scanner;
        this.service = service;
    }

    public void process(Command command) {

        switch (command.getCommand()) {
            case "등록" :
                registerWiseSaying();
                break;
            case "목록" :
                showWiseSaying();
                break;
            case "삭제" :
                deleteWiseSaying(command.getNumber());
                return;
            case "수정" :
                modifyWiseSaying(command.getNumber());
                return;
        }
    }

    private void registerWiseSaying() {
        System.out.print("명언: ");
        String content = scanner.nextLine();
        System.out.print("작가: ");
        String author = scanner.nextLine();

        int id = service.addWiseSaying(content, author);
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    private void showWiseSaying() {
        List<WiseSaying> list = service.getList();
        list.stream().sorted((o1, o2) -> o2.getId() - o1.getId())
                .collect(Collectors.toList())
                .forEach(wiseSaying -> System.out.println(wiseSaying.getId() + " / "
                        + wiseSaying.getAuthor() + " / " + wiseSaying.getContent()));

    }

    private void deleteWiseSaying(int number) {
        if (service.removeWiseSaying(number)) {
            System.out.println(number + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(number + "번 명언은 존재하지 않습니다.");
        }
    }

    private void modifyWiseSaying(int number) {
        WiseSaying wiseSaying = service.getWiseSayingById(number);
        if (wiseSaying == null) {
            System.out.println(number + "번 명언은 존재하지 않습니다.");
            return;
        }
        System.out.println("명언(기존): " + wiseSaying.getContent());
        System.out.print("명언: ");
        String newContent = scanner.nextLine();

        System.out.println("작가(기존): " + wiseSaying.getAuthor());
        System.out.print("작가: ");
        String newAuthor = scanner.nextLine();

        if (service.updateWiseSaying(number, newAuthor, newContent)) {
            System.out.println(number + "번 명언이 수정되었습니다.");
        } else {
            System.out.println(number + "번 명언은 존재하지 않습니다.");
        }
    }
}