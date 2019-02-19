package modernjava.functional;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yjlee on 2019-02-04.
 */
public class Exam3 {

    public static void main(String[] args){

        primes(10).forEach(n -> System.out.println(n));

        IntStream numbers = numbers();

        int head = head(numbers);
        IntStream filtered = tail(numbers).filter(n -> n %head !=0 );

        System.out.println(" ===================================================== ");

        filtered.forEach(n -> System.out.println(n));

    }

    public static IntStream primes(int n){
        return IntStream.iterate(2, i -> i + 1)
                .filter(MyMathUtils::isPrime)
                .limit(n);
    }

    static IntStream numbers(){
        return IntStream.iterate(2 , n -> n+1);
    }

    static int head(IntStream numbers){
        return numbers.findFirst().getAsInt();
    }

    static IntStream tail(IntStream numbers){
        return numbers.skip(1);
    }

    static class MyMathUtils {
        public static boolean isPrime(int candidate){
            int candidateRoot = (int) Math.sqrt(candidate);
            return IntStream.rangeClosed(2,candidateRoot)
                    .noneMatch(i -> candidate %i == 0);
        }
    }

}
