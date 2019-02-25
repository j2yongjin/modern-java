package modernjava.dynamicParam;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by yjlee on 2019-02-10.
 */
public class exam1 {

    public static void main(String[] args){


        List<Integer> weights = Arrays.asList(60,65,70,75,80,90);
        List<Integer> result = filterWeight(weights, x -> x > 80);

        for(Integer r : result){
            System.out.println(" result : " + r);
        }

    }

    static List<Integer> filterWeights;

    static List<Integer> filterWeight(List<Integer> weights , Predicate<Integer> predicate){

        List<Integer> newList = new ArrayList<>();
        for(Integer w : weights) {
            if (predicate.test(w)){
                newList.add(w);
            }
        }
        return  newList;
    }


}
