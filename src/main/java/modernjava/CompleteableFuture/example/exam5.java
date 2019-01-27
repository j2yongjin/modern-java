package modernjava.CompleteableFuture.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by yjlee on 2019-01-27.
 */
public class exam5 {

    static List<Shop> shops = Arrays.asList(new Shop("Best Price")
            , new Shop("LetsSaveBig")
            , new Shop("MyFavoriteShop")
            , new Shop("BuyItAll")
    ) ;

    public static void main(String[] args){



        String product = "best price";
        ExchangeService exchangService = new ExchangeService();

        // thenCombine 동시에 스레드를 실행할 경우에

//        shops.stream().map( shop -> {
//                    Future<Double> future = CompletableFuture.supplyAsync(
//                                    () -> shop.getPrice(product))
//                            .thenCombine( CompletableFuture.supplyAsync(
//                                    () -> exchangeService.getRate(Money.EUR,Money.USD))
//                            , (price , rate ) -> price * rate)
//                }
//
//
//        );


    }
}
