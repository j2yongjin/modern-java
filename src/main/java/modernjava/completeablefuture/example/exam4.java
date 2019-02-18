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
public class exam4 {

    static List<Shop> shops = Arrays.asList(new Shop("Best Price")
            , new Shop("LetsSaveBig")
            , new Shop("MyFavoriteShop")
            , new Shop("BuyItAll")
    ) ;

    public static void main(String[] args){

        long start =System.nanoTime();

        System.out.println(" findPrics"
                 + findPrices("myPhoness122")
        );

        long duration =  ( System.nanoTime() - start ) / 1_000_000;
        System.out.println("duration : " + duration);


        start =System.nanoTime();

        System.out.println(" findPrics Async"
                + findPricesAsync("myPhoness122")
        );

        duration =  ( System.nanoTime() - start ) / 1_000_000;
        System.out.println("async duration : " + duration);


    }

    private  static final Executor executor =
            Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }
            });

    static List<String> findPrices(String product){
        return shops.stream().map(shop -> shop.getPriceWithDiscount(product))
                                .map(Quote::prase)
                                .map(Discount::applyDiscount)
                            .collect(Collectors.toList());
    }

    static List<String> findPricesAsync(String product){
        List<CompletableFuture<String>> priceFutures =
                // 각 상점에서 할인전 가격을 비동기적으로 얻는다.
                shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceWithDiscount(product),executor))
                                // 상점에서 반환한 문자열을 Quote 객체로 변환한다.
                                .map(future -> future.thenApply(Quote::prase))

                                // 결과 Future를 다른 비동기 작업과 조합해서 할인 코드를 적용한다. ( thenCompose)
                                .map(future -> future.thenCompose(quote ->
                                            CompletableFuture.supplyAsync( () -> Discount.applyDiscount(quote),executor)))

                .collect(Collectors.toList());

        return priceFutures.stream().map(CompletableFuture::join)  //  모든 스트림을 종료되길 기다렸다가 각각의 결과를 추출한다.
                .collect(Collectors.toList());


    }



}
