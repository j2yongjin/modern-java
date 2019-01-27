package modernjava.stream.example;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import modernjava.stream.dto.Type;
import modernjava.stream.dto.User;
import modernjava.stream.dto.UserLevel;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yjlee on 2019-01-27.
 */
public class Exam9 {

    public static void main(String[] args){

        List<User> userList = Arrays.asList(new User("yjlee21",21, Type.PERSONAL,false)
                ,new User("yjlee22",22, Type.PERSONAL,false)
                ,new User("yjlee23",23, Type.BUSINESS,false)
                ,new User("yjlee24",24, Type.PERSONAL,false)
                ,new User("yjlee25",25, Type.PERSONAL,false)
                ,new User("yjlee26",26, Type.BUSINESS,false)
                ,new User("yjlee36",36, Type.PERSONAL,true)
                ,new User("yjlee37",37, Type.PERSONAL,true)
        );

        Map<Type, List<User>> userType = userList.stream().collect(Collectors.groupingBy(User::getType));

        System.out.println("userType " + userType);
        System.out.println("==================================================");

        Map<UserLevel,List<User>> userLevelListMap = userList.stream().collect(
                Collectors.groupingBy(
                        user -> {
                            if(user.getAge() <= 25) return UserLevel.NORMAL;
                            else if(user.getAge() > 25 && user.getAge() < 30) return UserLevel.GOLD;
                            else return UserLevel.VIP;
                        }
                )
        );

        System.out.println("userLevelListMap :  " + userLevelListMap);
        System.out.println("==================================================");


        Map<Type,Map<UserLevel,List<User>>> multiGrouping = userList.stream().collect(Collectors.groupingBy(
                User::getType, Collectors.groupingBy( user -> {
                            if (user.getAge() <= 25) return UserLevel.NORMAL;
                            else if (user.getAge() > 25 && user.getAge() < 30) return UserLevel.GOLD;
                            else return UserLevel.VIP;
                        }
                )
        ));

        System.out.println("multiGrouping : " + multiGrouping);

        System.out.println(" business grouping : " + multiGrouping.get(Type.BUSINESS));



//        Map<Type,Long> typeCount = userList.stream().collect(User::getType, Collectors.counting());

        Map<Type,Optional<User>> mostAges = userList.stream().collect(Collectors.groupingBy(User::getType
        ,Collectors.maxBy(Comparator.comparing(User::getAge))));

        System.out.println("mostAges : " + mostAges);

        Map<Type,User> mostSubGroupAges = userList.stream().collect(Collectors.groupingBy(User::getType
        ,Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(User::getAge)),Optional::get)));

        System.out.println("mostSubGroupAges : " + mostSubGroupAges);

        Map<Type,Integer> totalByType = userList.stream().collect(
                Collectors.groupingBy(User::getType,Collectors.summingInt(User::getAge))
        );
        System.out.println( " totalByType : " + totalByType);

        Map<Type,Set<UserLevel>> userLevelType = userList.stream().collect(
                Collectors.groupingBy(User::getType, Collectors.mapping( user -> {
                            if (user.getAge() <= 25) return UserLevel.NORMAL;
                            else if (user.getAge() > 25 && user.getAge() < 30) return UserLevel.GOLD;
                            else return UserLevel.VIP;
                        },Collectors.toSet()
                ))
        );

        System.out.println("userLevelType : " + userLevelType );

        Map<Type,Set<UserLevel>> userLevelTypeCollections = userList.stream().collect(
                Collectors.groupingBy(User::getType, Collectors.mapping( user -> {
                            if (user.getAge() <= 25) return UserLevel.NORMAL;
                            else if (user.getAge() > 25 && user.getAge() < 30) return UserLevel.GOLD;
                            else return UserLevel.VIP;
                        },Collectors.toCollection(HashSet::new)
                ))
        );

        System.out.println("userLevelTypeCollections : " + userLevelTypeCollections );

        Map<Boolean,List<User>> partitionUser = userList.stream().collect(Collectors.partitioningBy(User::isMarriage));

        System.out.println("partitionUser : "  + partitionUser);


        Map<Boolean,Map<Type,List<User>>> marriageByTypes = userList.stream().collect(
                Collectors.partitioningBy(User::isMarriage,Collectors.groupingBy(
                        User::getType
                ))
        );

        System.out.println(" marriageByTypes : " + marriageByTypes );

        Map<Boolean,Map<Boolean,List<User>>> multiPartitions =
                userList.stream().collect(Collectors.partitioningBy(User::isMarriage
                ,Collectors.partitioningBy(user -> user.getAge() > 30)));

        System.out.println("multiPartitions : " + multiPartitions );


    }
}
