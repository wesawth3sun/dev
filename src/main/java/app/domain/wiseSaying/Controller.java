package app.domain.wiseSaying;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Controller {
    //클라이언트와 맞닿아있으면서, 사용자 요청을 처리하고 응답을 반환하는 역할
    //서비스로부터 받은 결과를 클라이언트에게 반환

    private final Scanner scanner;
    private final Service service;
    private Command command;

    public Controller(Scanner scanner) {
        this.scanner = scanner;
        this.service = new Service();
    }

    public void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        WiseSaying w = service.write(content, author);

        System.out.println(w.getId() + "번 명언이 등록되었습니다.");
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

    public void actionBuild() {
        service.build();
    }

    public void makeSampleData(int sampleCount) {
        service.makeSampleData(sampleCount);
    }

    public void actionList(Command command) {

        String keywordType = command.getParam("keywordType");
        String keyword = command.getParam("keyword");
        String pageStr = command.getParam("page");
        int page = 1; // 기본값

        if (pageStr != null) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                // 유효하지 않은 페이지 번호 처리
            }
        }
        List<WiseSaying> wiseSayings;
        if (keywordType != null && keyword != null) {
            // 검색 기능
            wiseSayings = service.search(keywordType, keyword);
            System.out.println("----------------------");
            System.out.println("검색 타입: " + keywordType);
            System.out.println("검색어: " + keyword);
        } else {
            // 전체 목록 기능
            wiseSayings = service.getList();
            Collections.reverse(wiseSayings);
        }

        System.out.println("----------------------");
        System.out.println("번호 / 명언 / 작가");
        System.out.println("----------------------");

        if (wiseSayings.isEmpty()) {
            System.out.println(keywordType != null ? "검색 결과가 없습니다." : "등록된 명언이 없습니다.");
        } else {
            // 페이징 처리
            int itemsPerPage = 5;
            //한 페이지에 표시할 항목의 수를 5개로 설정
            int startIndex = (page - 1) * itemsPerPage;
            //현재 페이지에서 표시할 첫 번째 항목의 인덱스를 계산
            //1페이지 (page = 1):
            //startIndex = (1-1) * 5 = 0
            //표시될 항목: 1번부터 5번 (인덱스 0~4)
            //2페이지 (page = 2):
            //startIndex = (2-1) * 5 = 5
            //표시될 항목: 6번부터 10번 (인덱스 5~9)
            int endIndex = Math.min(startIndex + itemsPerPage, wiseSayings.size());
            //현재 페이지에서 표시할 마지막 항목의 인덱스를 계산
            //startIndex + itemsPerPage와 wiseSayings.size() 중 작은 값을 선택
            for (int i = startIndex; i < endIndex; i++) {
                WiseSaying wiseSaying = wiseSayings.get(i);
                System.out.printf("%d / %s / %s\n",
                        wiseSaying.getId(), wiseSaying.getContent(), wiseSaying.getAuthor());
            }

        }
        printPage(wiseSayings.size(), page);
    }

    private void printPage(int totalItems, int currentPage) {
        int itemsPerPage = 5;
        //한 페이지당 표시할 항목 수를 5로 설정
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        //전체 페이지 수를 계산
        System.out.println("----------------------");
        System.out.print("페이지 : ");
        for (int i = 1; i <= totalPages; i++) {
            if (i == currentPage) {
                //사용자의 입력에 따라 currentPage가 결정
                //기본은 1이 보여짐
                System.out.print("[" + i + "]");
                //현재 페이지인 경우 대괄호로 감싸서 출력
            } else {
                System.out.print(i);
                //현재 페이지가 아닌 경우 숫자만 출력
            }
            if (i < totalPages) {
                System.out.print(" / ");
                //마지막 페이지가 아니면 " / "를 출력하여 페이지 번호를 구분
            }
        }
        System.out.println();
    }
}

