package stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamMainV3 {
    public static void main(String[] args) {
        //1부터 10까지 중, 짝수만 따로 뽑아서 새로운 배열을 만들자

        //명령형
        useForIf();

        //선언형
        useStream();
    }

    private static void useStream() {
        System.out.println("== 스트림 ==");
        IntStream.rangeClosed(1, 10)
                .filter((int num) -> {
                    if (num % 2 == 0) {
                        return true;
                    }
                    return false;
                })
                .forEach(System.out::println);
    }

    private static void useForIf() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("== 명령형 ==");
        int[] newArr = new int[arr.length];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                newArr[index] = arr[i];
                index++;
            }
        }
        System.out.println(Arrays.toString(newArr));
    }
}
