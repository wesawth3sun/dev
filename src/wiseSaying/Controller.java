package wiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private final Service service;
    private final Repository repository;
    private final Scanner scanner;


    public Controller(Service service, Repository repository, Scanner scanner) {
        this.service = service;
        this.repository = repository;
        this.scanner = scanner;
    }


    public void modify(int id) {
        //WiseSaying wiseSaying = list.get(id);
        //인덱스와 id 값이 같지 않아서 이렇게는 가져오면 안 된다
        WiseSaying wiseSaying = service.findById(id);
        if (wiseSaying == null) {
            System.out.println(id + " 번 명언은 존재하지 않습니다.");
            return;
        }
        //빈 문자열을 입력했을 때 기존 값을 유지
        System.out.println("기존 명언: " + wiseSaying.getContent());
        System.out.print("수정 명언: ");
        String newContent = scanner.nextLine().trim();
        //문자열의 앞뒤 공백을 제거
        //사용자가 아무것도 입력하지 않고 엔터만 친 경우, 사용자가 공백만 입력하고 엔터를 친 경우
        //둘 다 값을 바꾸지 않기 위해서 trim() 메서드 추가

        System.out.println("기존 작가: " + wiseSaying.getAuthor());
        System.out.print("수정 작가: ");
        String newAuthor = scanner.nextLine().trim();

        service.update(wiseSaying, newContent, newAuthor);
        System.out.println(id + " 번 명언이 수정되었습니다.");

    }

    public void print() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        for (WiseSaying wiseSaying : service.getList().reversed()) {
            System.out.println(wiseSaying.getId() + " / "
                    + wiseSaying.getContent() + " / "
                    + wiseSaying.getAuthor());
        }
    }

    public void write() {
        System.out.print("명언: ");
        String content = scanner.nextLine();

        System.out.print("작가: ");
        String author = scanner.nextLine();

        service.add(content, author);
    }

    public void removeForId(int id) {
        List<WiseSaying> list = service.getList();
        for (int i = 0; i < list.size(); i++) {
            WiseSaying wiseSaying = list.get(i);
            if (wiseSaying.getId() == id) {
                service.delete(wiseSaying);
                System.out.println(id + "번 명언이 삭제되었습니다.");
                return;
                //메서드를 즉시 종료
                //이는 명언을 찾아 삭제했으므로 더 이상의 순회가 필요 없기 때문
            }
        }
        System.out.println(id + " 번 명령은 존재하지 않습니다.");
        // for 루프가 끝난 후에 실행
        // 만약 루프 내에서 return 문이 실행되지 않았다면(즉, 일치하는 ID를 찾지 못했다면), 이 메시지가 출력
    }
    //ConcurrentModificationException은 컬렉션을 순회하는 도중에 해당 컬렉션의 구조를 변경할 때 발생하는 예외
    //향상된 for 문을 사용하면 내부적으로 Iterator 사용
    //순회 중 컬렉션에서 요소를 제거하면 Iterator의 상태와 실제 컬렉션의 상태가 불일치

    //인덱스 기반 순회를 사용하면 직접 리스트의 인덱스를 제어, 요소 제거 후 리스트 크기가 자동으로 조정

    public void makeTestData() {
        service.add("즐길 수 없으면 피해라", "작자 미상");
        service.add("행복해서 웃는 게 아니다... 웃어서 행복한 거다...", "작자 미상");
    }
}