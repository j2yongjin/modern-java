package yjlee.modernjava.lamda;

import java.util.Arrays;
import java.util.Comparator;

public class HigherOrderFuction {
    public static void main(String[] args){

        //함수를 반환하는 메서드
        String[] friends = {"yjlee","tom"};
        Arrays.sort(friends,compareInDirection(-1));

        // 함수를 수정하는 메서드
        Arrays.sort(friends,reverse(String::compareToIgnoreCase));


        // 함수를 수정하는 메서드
        Person[] persons = {new Person() , new Person()};
        Arrays.sort(persons,Comparator.comparing(Person::getLastName));

        Arrays.sort(persons,Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName));

        Arrays.asList(persons,Comparator.comparing(Person::getLastName,
                (s,t)->s.length() - t.length()));

    }

    static Comparator<String> compareInDirection(int direction){
        return (x,y) -> direction * x.compareTo(y);
    }

    static Comparator<String> reverse(Comparator<String> comp){
        return (x,y) -> comp.compare(x,y);
    }
}

class Person{
    String firstName;
    String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
