package modernjava.stream.example;

import modernjava.stream.dto.Type;
import modernjava.stream.dto.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yjlee on 2019-01-22.
 */
public class Eaxm2 {

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

        List<User> users = userList.stream().filter(User::isMarriage).collect(Collectors.toList());

        users.stream().forEach(s -> System.out.println(s));
        System.out.println(" ==========================================================");

        // distict 함수 사용
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream().filter(i -> i%2 == 0)
                .distinct()
                .forEach(System.out::println);

        System.out.println(" ==========================================================");

        //   skip 을 통한 건너띄기
        List<User> users1 = userList.stream().filter(d ->d.getAge()>22).skip(2).collect(Collectors.toList());
        users1.stream().forEach(System.out::println);
        System.out.println(" ==========================================================");

        // 각 단어의 길이를 리턴
        List<Integer> length = userList.stream().map(User::getName).map(String::length).collect(Collectors.toList());
        length.stream().forEach(System.out::println);
        System.out.println(" ==========================================================");

    }

}
