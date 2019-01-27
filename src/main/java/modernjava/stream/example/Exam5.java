package modernjava.stream.example;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yjlee on 2019-01-23.
 */
public class Exam5 {

    public static void main(String[] args){

        List<Integer> numbers = Arrays.asList(1,4,5,6,2,0);

        int sum = numbers.stream().reduce(0, (a,b) -> a+b);
        System.out.println(sum);

        sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        System.out.println("===========================================");

        int max = numbers.stream().reduce(0, Integer::max);
        System.out.println(max);
        System.out.println("===========================================");


    }
}
