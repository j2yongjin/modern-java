package modernjava.completeablefuture.example;

/**
 * Created by yjlee on 2019-01-27.
 */
public class ExchangeService {

    public Double getRate(Money money1 , Money money2){
        return Double.valueOf(money1.exchange / money2.exchange);
    }
}
