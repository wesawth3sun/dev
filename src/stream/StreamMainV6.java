package stream;

import java.util.Arrays;

public class StreamMainV6 {

    public static void main(String[] args) {
        //숫자 배열을 1번, 2번, 3번... 형식으로 만들기

        int[] arr = {1, 2, 3, 4, 5};
        String[] array = Arrays.stream(arr)
                .mapToObj(num -> num + "번")
                .toArray(String[]::new);
        // Object[] array 배열로 리턴되니까, toArray에 String[]::new 를 추가해 주면
        // String[] 로 리턴해 준다.
        Arrays.stream(array).forEach(System.out::println);


    }
}
