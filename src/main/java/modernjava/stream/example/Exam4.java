package modernjava.stream.example;

import modernjava.stream.dto.Type;
import modernjava.stream.dto.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by yjlee on 2019-01-23.
 */
public class Exam4 {

    public static void main(String[] args) {


        List<User> userList = Arrays.asList(new User("yjlee21",21, Type.PERSONAL,false)
                ,new User("yjlee22",22, Type.PERSONAL,false)
                ,new User("yjlee23",23, Type.PERSONAL,false)
                ,new User("yjlee24",24, Type.PERSONAL,false)
                ,new User("yjlee25",25, Type.PERSONAL,false)
                ,new User("yjlee26",26, Type.PERSONAL,false)
                ,new User("yjlee36",36, Type.PERSONAL,true)
                ,new User("yjlee37",37, Type.PERSONAL,true)
        );

        // any match
        if(userList.stream().anyMatch(User::isMarriage)){
            System.out.println("is marriage");
        }
        System.out.println("==============================================================");
        // none Match
        boolean isYoungBoy = userList.stream().noneMatch(s -> s.getAge() <=20);
        System.out.println("isYoundBoy : " + isYoungBoy);
        System.out.println("==============================================================");
        //
        Optional<User> user = userList.stream().filter(User::isMarriage).findAny();

        System.out.println("user : " + user);

        System.out.println("==============================================================");
        userList.stream().filter(User::isMarriage).findAny().ifPresent(d -> System.out.println(d));

        System.out.println("==============================================================");
        userList.stream().filter(User::isMarriage).findFirst().ifPresent(d -> System.out.println(d));


    }
}
