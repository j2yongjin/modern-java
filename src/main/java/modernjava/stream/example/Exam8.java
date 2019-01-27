package modernjava.stream.example;

import modernjava.stream.dto.Currency;
import modernjava.stream.dto.Transaction;
import modernjava.stream.dto.Type;
import modernjava.stream.dto.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yjlee on 2019-01-23.
 */
public class Exam8 {

    public static void main(String[] args){

        List<Transaction> transactions = Arrays.asList(new Transaction(new Currency("한국"))
                ,new Transaction(new Currency("usa")),new Transaction(new Currency("usa"))
        ,new Transaction(new Currency("japan") ));

        Map<Currency,List<Transaction>> tr = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));

        System.out.println(tr);

        List<User> userList = Arrays.asList(new User("yjlee21",21, Type.PERSONAL,false)
                ,new User("yjlee22",22, Type.PERSONAL,false)
                ,new User("yjlee23",23, Type.PERSONAL,false)
                ,new User("yjlee24",24, Type.PERSONAL,false)
                ,new User("yjlee25",25, Type.PERSONAL,false)
                ,new User("yjlee26",26, Type.PERSONAL,false)
                ,new User("yjlee36",36, Type.PERSONAL,true)
                ,new User("yjlee37",37, Type.PERSONAL,true)
        );

        Optional<User> userMax = userList.stream().collect(Collectors.maxBy(Comparator.comparing(User::getAge)));
        System.out.println("userMax : " + userMax);
        System.out.println("=========================================");

        int totalAge = userList.stream().collect(Collectors.summingInt(User::getAge));

        System.out.println("totalAge" + totalAge);
        System.out.println("=========================================");

        double averageAge = userList.stream().collect(Collectors.averagingInt(User::getAge));

        System.out.println("averageAge: " + averageAge);

        System.out.println("=========================================");
        IntSummaryStatistics statistis = userList.stream().collect(Collectors.summarizingInt(User::getAge));

        System.out.println("statistis" + statistis);

        System.out.println("=========================================");
        String sumName = userList.stream().map(User::getName).collect(Collectors.joining());
        System.out.println("sumName" + sumName);

        System.out.println("=========================================");
        sumName = userList.stream().map(User::getName).collect(Collectors.joining(","));
        System.out.println("sumName" + sumName);

        System.out.println("=========================================");
        Optional<User> users = userList.stream().collect(Collectors.reducing( (d1,d2) -> d1.getAge() > d2.getAge() ? d1 : d2 ));
        System.out.println("users" + users);

        System.out.println("=========================================");

        int totalAges = userList.stream().collect(Collectors.reducing(0,User::getAge,Integer::sum));
        System.out.println("totalAges : " + totalAges);






    }

}
