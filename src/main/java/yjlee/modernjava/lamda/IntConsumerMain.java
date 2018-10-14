package yjlee.modernjava.lamda;

public class IntConsumerMain {

    public static void main(String[] args){
        repeat(10,i-> System.out.println("Countdown : " + (9-i)));
    }

    public static void repeat(int n, java.util.function.IntConsumer consumer){
        for(int i=0;i<n;i++)
            consumer.accept(i);
    }


}


