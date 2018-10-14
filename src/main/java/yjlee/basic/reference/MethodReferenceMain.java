package yjlee.basic.reference;

import java.util.ArrayList;
import java.util.List;

public class MethodReferenceMain {

    public static void main(String[] args){

        List<String> lists = new ArrayList<>();
//        Arrays.sort(lists,String::compareToIgnoreCase);
        lists.forEach(System.out::println);

        lists.forEach(MethodReferenceTypeOne::staticPrint);  // static
//        lists.forEach(this::equals);
    }

}

class MethodReference{
    public void print(String x){

    }
}

@FunctionalInterface
interface MethodReferenceTypeOne {
    public void print(String x);
    default void printOne(String x){

    }
    static void staticPrint(String x){

    }

}
