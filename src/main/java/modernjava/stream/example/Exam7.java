package modernjava.stream.example;

import modernjava.stream.dto.Type;
import modernjava.stream.dto.User;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yjlee on 2019-01-23.
 */
public class Exam7 {

    public static void main(String[] args){

        List<User> userList = Arrays.asList(new User("yjlee21",21, Type.PERSONAL,false)
                ,new User("yjlee22",22, Type.PERSONAL,false)
                ,new User("yjlee23",23, Type.PERSONAL,false)
                ,new User("yjlee24",24, Type.PERSONAL,false)
                ,new User("yjlee25",25, Type.PERSONAL,false)
                ,new User("yjlee26",26, Type.PERSONAL,false)
                ,new User("yjlee36",36, Type.PERSONAL,true)
                ,new User("yjlee37",37, Type.PERSONAL,true)
        );

        int age = userList.stream().mapToInt(s -> s.getAge()).sum();
        System.out.println(age);

        IntStream intStream = userList.stream().mapToInt(s -> s.getAge());

        Stream<Integer> integerStream = intStream.boxed();

        OptionalInt maxAge = userList.stream().mapToInt(User::getAge).max();
        System.out.println(maxAge);

    }
}
