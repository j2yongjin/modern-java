package modernjava.completeablefuture.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

/**
 * Created by yjlee on 2019-01-27.
 */
public class exam6 {

    static List<Shop> shops = Arrays.asList(new Shop("Best Price")
            , new Shop("LetsSaveBig")
            , new Shop("MyFavoriteShop")
            , new Shop("BuyItAll")
    ) ;

    public static void main(String[] args) throws InterruptedException {

        findPriceSteam("myPhone ").map( f ->f.thenAccept(System.out::println));




//        CompletableFuture[] futures = findPriceSteam("myPhone").map(f -> f.thenAccept(
//                System.out::println))
//                .toArray( size -> new CompletableFuture[size]);
//        CompletableFuture.allOf(futures).join();

        System.out.println("exit");




    }

    static Stream<CompletableFuture<String>> findPriceSteam(String product){
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync( () -> shop.getPriceWithDiscount(product),executor))
                .map(future -> future.thenApply(Quote::prase))
                .map(future -> future.thenCompose( quote -> CompletableFuture.supplyAsync(
                        () -> Discount.applyDiscount(quote) , executor
                )));
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

}
