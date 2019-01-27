package modernjava.CompleteableFuture.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by yjlee on 2019-01-27.
 */
public class exam2 {

    public static void main(String[] args){

        Shop shop = new Shop("BestShop");

        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");

        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println( "invocation time : " + invocationTime + " msecs");

        doSomethingElse();

        try {
            double price = futurePrice.get();
            System.out.println(" price " + price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long returnTime =  (System.nanoTime() - start) / 1_000_1000;

        System.out.println(" price return time " + returnTime + " msecs");


    }

    private static void doSomethingElse() {
        System.out.println("doSomthingElse");
    }

}
