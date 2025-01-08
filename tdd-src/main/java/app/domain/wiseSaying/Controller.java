package app.domain.wiseSaying;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Controller {
    //클라이언트와 맞닿아있으면서, 사용자 요청을 처리하고 응답을 반환하는 역할
    //서비스로부터 받은 결과를 클라이언트에게 반환

    private final Scanner scanner;
    private final Service service;

    public Controller(Scanner scanner, Service service) {
        this.scanner = scanner;
        this.service = service;
    }

    public void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        WiseSaying w = service.write(content, author);

        System.out.println(w.getId() + "번 명언이 등록되었습니다.");
    }

    public void actionPrint() {
        System.out.println("번호 / 명언 / 작가");
        System.out.println(" --------------------- ");

        List<WiseSaying> list = service.getList();
          /*      for (int i = list.size() - 1; i >= 0; i--) {
            app.inside.WiseSaying wiseSaying = list.get(i);
            System.out.println(wiseSaying.getId() + " / " + wiseSaying.getContent() + " / " + wiseSaying.getAuthor());
                }*/
        //컬렉션과 스트림으로 배열 역순 정렬
        Collections.reverse(list);
        list.forEach(wiseSaying -> System.out.println(wiseSaying.getId() + " / " + wiseSaying.getContent() + " / " + wiseSaying.getAuthor()));
    }

    public void actionDelete(int id) {
        List<WiseSaying> list = service.getList();
        for (WiseSaying wiseSaying : list) {
            if (wiseSaying.getId() == id) {
                service.delete(wiseSaying);
                System.out.println(id + "번 명언이 삭제되었습니다.");
                return;
            }
        }
        System.out.println(id + "번 명언은 존재하지 않습니다.");
    }

    public void actionModify(int id) {
        WiseSaying wiseSaying = service.findById(id);
        if (wiseSaying == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }
        //빈 문자열을 입력했을 때 기존 값을 유지
        System.out.println("기존 명언: " + wiseSaying.getContent());
        System.out.print("수정 명언: ");
        String newContent = scanner.nextLine().trim();

        System.out.println("기존 작가: " + wiseSaying.getAuthor());
        System.out.print("수정 작가: ");
        String newAuthor = scanner.nextLine().trim();

        service.update(wiseSaying, newContent, newAuthor);
        System.out.println(id + "번 명언이 수정되었습니다.");
    }
}
