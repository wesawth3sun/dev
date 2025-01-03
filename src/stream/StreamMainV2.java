package stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamMainV2 {

    //1~100까지 중 짝수만 출력될 수 있도록 하는 스트림 예제

    public static void main(String[] args) {
        //숫자 스트림을 바로 생성할 수 있다
        //rangeClosed() 숫자의 경계까지 포함해서 스트림으로 만들어 줌
        IntStream.rangeClosed(1, 100).forEach(StreamMainV2::even);

        //람다 -> 익명 함수 (이름이 없는 함수, 일회용 함수)
        //한 번만 사용할 건데 메서드를 만들기 애매할 때
        //메서드 레퍼런스가 필요한 부분에 람다 정의가 가능 () -> {}
        IntStream.rangeClosed(1, 100).forEach((int num) ->
                                            {if (num % 2 == 0) {
                                             System.out.println(num);}
                                             });

        //기본적으로 람다로 쓰지만
        //람다 안에서 사용하는 것이 재사용성이 높으면 메서드로 뽑아서 사용하는 게 낫다
    }

    private static void even(int num) {
        if (num % 2 == 0) {
            System.out.println(num);
        }
    }
}
