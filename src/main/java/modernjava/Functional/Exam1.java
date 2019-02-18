package modernjava.Functional;

import modernjava.stream.dto.Transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by yjlee on 2019-01-28.
 */
public class Exam1 {

    public static void main(String[] args){

        List<Transaction> transactions = new ArrayList<>();
        Transaction mostExpensive = transactions.get(0);

        if(mostExpensive == null) throw new IllegalArgumentException("Empty list of transactions");

        for(Transaction transaction : transactions.subList(1,transactions.size())){
            if(transaction.getValue() > mostExpensive.getValue()){
                mostExpensive = transaction;
            }
        }


        Optional<Transaction> mostExpensiveResult = transactions.stream().max(Comparator.comparing(Transaction::getValue));

    }
}
