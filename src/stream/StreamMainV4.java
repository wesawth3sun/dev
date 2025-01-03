package stream;

import java.util.Arrays;

public class StreamMainV4 {
    public static void main(String[] args) {
        //자바 스트림은 원본의 불변성을 보장한다
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        //arr의 값이 각각 2배된 배열 만들기
        //블록 람다 표현식
        int[] doubleArr = Arrays.stream(arr).map(num -> {
            return num * 2;
        }).toArray();
        Arrays.stream(doubleArr).forEach(System.out::println);

        //arr의 값이 각각 3배된 배열 만들기
        //표현식 람다
        int[] tripleArr = Arrays.stream(arr).map(num -> num * 3).toArray();
        Arrays.stream(tripleArr).forEach(System.out::println);

        //arr의 값이 각각 10배된 배열 만들기
        int[] multipleTen = Arrays.stream(arr).map(num -> num * 10).toArray();
        Arrays.stream(multipleTen).forEach(System.out::println);
    }
}
