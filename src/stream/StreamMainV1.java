package stream;

import java.util.Arrays;

public class StreamMainV1 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        //전부 출력, 명령형 방식
        for (int i : arr) {
            System.out.println(i);
        }

        //선언형 -> 무엇을 하겠다고 선언, 어떻게 할지는 관심 x
        //자바가 미리 만들어놨다고 생각하면 편함

        Arrays.stream(arr).forEach(System.out::println);
    }
}
