package modernjava.stream.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by yjlee on 2019-01-23.
 */
public class Exam3 {

    public static void main(String[] args){

        String[] arrayOfWords = {"Goodbye","World"};
        Stream<String> stringStream = Arrays.stream(arrayOfWords);

        stringStream.forEach(s -> System.out.println(s));
        System.out.println("=======================================================");


        String[] word = {"hello","world"};
//        Stream<Stream<String>> result = Arrays.stream(word).map(
//                w -> w.split("")
//        ).distinct().collect(toList());
//
        List<String> result2 = Arrays.stream(word).map(w -> w.split(""))
                .flatMap(Arrays::stream).distinct().collect(toList());

        result2.forEach(s -> System.out.println(s));
        System.out.println("=======================================================");

        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        List<Integer> squares = numbers.stream().map(n -> n*n).collect(toList());
        squares.stream().forEach(s -> System.out.println(s));

        System.out.println("=======================================================");

        List<Integer> number1 = Arrays.asList(1,2,3);
        final List<Integer> number2 = Arrays.asList(3,4);
        List<int[]> pairs = number1.stream().flatMap(i -> number2.stream().map(j -> new int[]{i,j}))
                .collect(toList());

        pairs.stream().forEach(s -> System.out.println(s[0] + " " + s[1]));

        System.out.println("=======================================================");

        number1 = Arrays.asList(1,2,3);
        final List<Integer> number3 = Arrays.asList(3,4);
        pairs = number1.stream().flatMap(i -> number3.stream().filter(j -> (i+j) %3 ==0 ).map(j -> new int[]{i,j}))
                .collect(toList());

        pairs.stream().forEach(s -> System.out.println(s[0] + " " + s[1]));

        System.out.println("=======================================================");

    }
}
