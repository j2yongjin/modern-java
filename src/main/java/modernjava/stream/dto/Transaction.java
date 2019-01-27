package modernjava.stream.dto;

import lombok.Data;

/**
 * Created by yjlee on 2019-01-23.
 */
@Data
public class Transaction {

    private final Trader trader;
    private final int year;
    private final int value;

    private Currency currency;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Transaction(Currency currency) {
        this.currency = currency;
        this.trader = null;
        this.year = 0;
        this.value = 0;
    }
}
