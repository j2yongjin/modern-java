package modernjava.functional;

import ch.qos.logback.core.db.dialect.MySQLDialect;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by yjlee on 2019-02-04.
 */
public class Exam4 {


    public static void main(String[] args){
        MyList<Integer> list =
                new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>() ) );



        LazyList<Integer> numbers = from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();

        System.out.println(two + " " + three + " " + four);

    }



    public static LazyList<Integer> from(int n)
    {
        return new LazyList<Integer>(n , () ->from(n+1));
    }

    static class LazyList<T> implements MyList<T> {

        final T head;
        final Supplier<MyList<T>> tail;

        public LazyList(T head, Supplier<MyList<T>> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public MyList<T> tail() {
            return tail.get();
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

//        static MyList<T> filter(Predicate<T> p){
//            return  p.test();
//        }

    }











    static class MyLinkedList<T> implements MyList<T>{

        private final T head;
        private final MyList<T> tail;

        public MyLinkedList(T head, MyList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public MyList<T> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }

    static class Empty<T> implements MyList<T>{

        @Override
        public T head() {
            throw new UnsupportedOperationException();
        }
        @Override
        public MyList<T> tail(){
            throw new UnsupportedOperationException();
        }


    }

    interface MyList<T>{

        T head();
        MyList<T> tail();

        default boolean isEmpty(){
            return true;
        }

    }
}
