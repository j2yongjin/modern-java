package yjlee.modernjava.lamda;

import java.util.function.Predicate;

public class PredicateMain {

    public static void main(String[] args){

        Object a= null;
        Predicate.isEqual(a).and(Predicate.isEqual(a))
        .and(Predicate.isEqual(a).or(Predicate.isEqual(a)));

    }
}
