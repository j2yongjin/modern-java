package modernjava.stream.example;

import modernjava.stream.dto.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Created by yjlee on 2019-01-22.
 */
public class Exam1 {

    public static void main(String[] args){

        List<User> users = Arrays.asList(new User("name1" , 20),new User("name2",21),new User("name3",22)
                ,new User("name4",23));

        // stream
        List<String> names = users.stream().filter(d -> d.getAge() > 20)
                .sorted(comparing(User::getAge))
                .map(User::getName).collect(Collectors.toList());

        names.forEach(s -> System.out.println(s));
        System.out.println("====================================================");


        // parallel
        names = users.parallelStream().filter(d -> d.getAge() > 20)
                .sorted(comparing(User::getAge))
                .map(User::getName).collect(Collectors.toList());

        names.forEach(s -> System.out.println(s));
        System.out.println("====================================================");

        // limit
        names = users.stream().filter(d -> d.getAge() > 20)
                .sorted(comparing(User::getAge))
                .map(User::getName)
                .limit(1)
                .collect(Collectors.toList());

        names.forEach(s -> System.out.println(s));
        System.out.println("====================================================");


        // iterator
        Iterator<User> userIterator = users.stream().iterator();

        while (userIterator.hasNext()){
            System.out.println(" " + userIterator.next());
        }
        System.out.println("====================================================");

        names = users.stream().filter(d -> {
            System.out.println(" filtering : " + d.getName() );
            return d.getAge() > 20;
        })
         .map(d -> {
             System.out.println("mapping : " + d.getName());
             return d.getName();
         })
                .limit(2)
                .collect(Collectors.toList());

//        names.forEach(s -> System.out.println(s));
        System.out.println("====================================================");




    }

}
