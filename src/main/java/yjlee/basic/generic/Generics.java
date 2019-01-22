package yjlee.basic.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yjlee on 2018-10-16.
 */
public class Generics {


    public <T>  Generics(T t){

    }

    // bounded type parameter
    static class GenericOne<T extends List & Serializable & Comparable>{

    }

    // T type parameter  , Generic class
    static class Hello<T> {
        T t;
        <T> void method(T t){

        }

        static <S> void staticMethod(S t){

        }
    }

    static interface HelloInterfce<T>{

        <T> void hello(T t);
    }

    static <T> void print(T t){
        System.out.println(t.toString());
    }

    static void print(String value){
       System.out.println(value);
    }

    public static void main(String[] args){
       print("Generics");

       new Hello<String>();   // type argument
       List<String> list = new ArrayList<>();


       // Raw Type
        List list1 = new ArrayList<Integer>();

        List<Integer> ints = Arrays.asList(1,2,3);
        List raws = ints;
        List<Integer> ints2 = raws;




    }
}
