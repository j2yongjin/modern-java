package modernjava.completeablefuture.example;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by yjlee on 2019-01-27.
 */
class Shop {

    String name;

    public String getName() {
        return name;
    }

    public Shop(String name) {
        this.name = name;
    }

    //  비동기2
    public Future<Double> getPriceAsyc2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }


    // 비동기2
    public Future<Double> getPriceAsync(String product) {

        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                completableFuture.complete(price);
            } catch (Exception ex) {
                completableFuture.completeExceptionally(ex); // exception throw --> ExecutionException
            }
        }).start();

        return completableFuture;
    }

    // 동기
    public double getPrice(String product) {

        double price = calculatePrice(product);
        return price;
    }

    public String getPriceWithDiscount(String product){
        double price = calculatePrice(product);
        Discount.Code  code = Discount.Code.values()[
                new Random().nextInt(Discount.Code.values().length)];

        return String.format("%s:%f:%s",name,price,code);
    }



    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
