package stream;

import java.util.Arrays;

public class StreamMainV5 {

    public static void main(String[] args) {
        //strNums에서 홀수만 뽑아서 int 배열로 만들기
        //예제 분석하기
        String[] strNums = {"1번", "2번", "3번", "4번", "5번"};
        code1(strNums);
        code2(strNums);
    }

    private static void code2(String[] strNums) {
        int[] oddNumbers = Arrays.stream(strNums)
                .map(str -> str.substring(0, 1))
                .mapToInt(Integer::parseInt)
                .filter(num -> num % 2 != 0)
                .toArray();

        System.out.println("== 두 번째 풀이 ==");
        Arrays.stream(oddNumbers).forEach(System.out::println);
    }

    private static void code1(String[] strNums) {
        int[] oddNumbers = Arrays.stream(strNums)
                .mapToInt(str -> Integer.parseInt(str.replaceAll("[^0-9]", "")))
                .filter(num -> num % 2 != 0)
                .toArray();
        System.out.println("== 첫 번째 풀이 ==");
        Arrays.stream(oddNumbers).forEach(System.out::println);
    }
}
