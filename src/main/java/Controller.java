import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Controller {
    //클라이언트와 맞닿아있으면서, 사용자 요청을 처리하고 응답을 반환하는 역할
    //서비스로부터 받은 결과를 클라이언트에게 반환

    private final Scanner scanner;
    private int lastNo;
    private final List<WiseSaying> list;

    public Controller(Scanner scanner) {
        this.scanner = scanner;
        list = new ArrayList<>();
        lastNo = 1;
    }

    public void write() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        list.add(new WiseSaying(lastNo, content, author));

        System.out.println(lastNo + "번 명언이 등록되었습니다.");
        lastNo++;
    }

    public void print() {
        System.out.println("번호 / 명언 / 작가");
        System.out.println(" --------------------- ");

/*      for (int i = list.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying = list.get(i);
            System.out.println(wiseSaying.getId() + " / " + wiseSaying.getContent() + " / " + wiseSaying.getAuthor());
                }*/
        //컬렉션과 스트림으로 배열 역순 정렬
        Collections.reverse(list);
        list.forEach(wiseSaying -> System.out.println(wiseSaying.getId() + " / " + wiseSaying.getContent() + " / " + wiseSaying.getAuthor()));

    }
}
