package modernjava.completeablefuture.example;

/**
 * Created by yjlee on 2019-01-27.
 */
public enum  Money {

    EUR(1) , USD(2);

    Integer exchange;

    Money(Integer exchange) {
        this.exchange = exchange;
    }

}
