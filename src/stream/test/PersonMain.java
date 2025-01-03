package stream.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class PersonMain {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person(1, "Alice", 20, 'F'));
        people.add(new Person(2, "Bob", 25, 'M'));
        people.add(new Person(3, "David", 35, 'M'));

        int sum = 0;
        int count = 0;

        //남자들 나이의 합, 나이 평균, 이름

        //명령형 풀이 (for, if)
        useForIf(people, sum, count);

        //선언형 풀이 (stream)

        useStream(people);

    }

    private static void useStream(List<Person> people) {
        // 남자들의 나이 합
        int sumOfAges = people.stream()
                .filter(c -> c.getGender() == 'M') // 남자 필터링
                .mapToInt(Person::getAge)
                .sum();
        System.out.println("남자들 나이의 합: " + sumOfAges);

        // 나이의 평균
        OptionalDouble averageAge = people.stream()
                .filter(c -> c.getGender() == 'M')
                .mapToInt(Person::getAge)
                .average();
        double asDouble = averageAge.getAsDouble();
        System.out.println("남자들 나이 평균: " + asDouble);

        //이름 출력
        List<String> nameList = people.stream()
                .filter(c -> c.getGender() == 'M')
                .map(Person::getName)
                .toList();
        for (String name : nameList) {
            System.out.println(name);
        }
    }

    private static void useForIf(List<Person> people, int sum, int count) {
        for (Person person : people) {
            if (person.getGender() == 'M') {
                sum += person.getAge();
                count++;
            }
        }
        System.out.println("남자들 나이의 합: " + sum);

        System.out.println("남자들 나이 평균: " + (double) sum / count);

        for (Person person : people) {
            if (person.getGender() == 'M') {
                System.out.println(person.getName());
            }
        }
    }
}
