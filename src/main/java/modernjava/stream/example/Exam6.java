package modernjava.stream.example;

import modernjava.stream.dto.Trader;
import modernjava.stream.dto.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * Created by yjlee on 2019-01-23.
 */
public class Exam6 {

    public static void main(String[] args){
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("mario","Milan");
        Trader alan = new Trader("alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian,2011,300)
                ,new Transaction(raoul,2012,1000)
                ,new Transaction(raoul,2011,400)
                ,new Transaction(mario,2012,710)
                ,new Transaction(mario,2012,700)
                ,new Transaction(alan,2012,950)
        );

        List<Transaction> tr2011 = transactions.stream().filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());

        tr2011.stream().forEach( s-> System.out.println(s));
        System.out.println("=====================================");

        List<Trader> traders = transactions.stream().map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());


        traders.stream().forEach( s-> System.out.println(s));
        System.out.println("=====================================");

        String traderString = transactions.stream().map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",  (s1 , s2 ) -> s1+s2);

        System.out.println(traderString);
        System.out.println("=====================================");

        traderString = transactions.stream().map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());

        System.out.println(traderString);
        System.out.println("=====================================");

        transactions.stream().filter( t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue).forEach(s -> System.out.println(s));

        System.out.println("=====================================");

        Optional<Integer> highestValue = transactions.stream().map(Transaction::getValue)
                .reduce(Integer::max);

        System.out.println(highestValue);

        System.out.println("=====================================");


        Optional<Integer> smallest = transactions.stream().map(Transaction::getValue).reduce( (t1 , t2) ->
                t1 < t2 ? t1 : t2 );

        System.out.println(smallest);
        System.out.println("=====================================");


        Optional<Transaction> smallestTr = transactions.stream().min(Comparator.comparing(Transaction::getValue));

        System.out.println(smallest);
        System.out.println("=====================================");


    }


}
