package modernjava.completeablefuture.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * Created by yjlee on 2019-01-27.
 */
public class exam3 {


    static List<Shop> shops = Arrays.asList(new Shop("Best Price")
            , new Shop("LetsSaveBig")
            , new Shop("MyFavoriteShop")
            , new Shop("BuyItAll")
    ) ;

    public static void main(String[] args){


        long start =System.nanoTime();
        System.out.println(
                findPrices("myPhone25s")
        );
        long duration = (System.nanoTime() - start ) / 1_000_1000;

        System.out.println("duration : " + duration + " msecs");





    }



//    public static List<String> findPrices(String product){
//        return shops.parallelStream()
////        return shops.stream()  //
//                .map( shop -> String.format(" %s product is price %f",shop.getName()
//                ,shop.getPrice(product))).collect(Collectors.toList());
//    }

    private  static final Executor executor =
            Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }
            });


    public static List<String> findPrices(String product){


        List<CompletableFuture<String>> priceFutures =
                shops.stream().map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getName() + " price is " + shop.getPrice(product) ,  executor

                )).collect(Collectors.toList());


        return priceFutures.stream().map(CompletableFuture::join)//  모든 비동기 동작이 끝나길 기다린다.
                .collect(Collectors.toList());



    }





}
