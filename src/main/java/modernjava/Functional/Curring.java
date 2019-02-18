package modernjava.Functional;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;

/**
 * Created by yjlee on 2019-01-31.
 */
public class Curring {

    public static void main(String[] args){

        DoubleUnaryOperator convertUsdToGbp = converter(0.6,0);

        System.out.println(" result : " +
            convertUsdToGbp.applyAsDouble(1000)
        );




    }

    static DoubleUnaryOperator converter(double f,double b){
        return (double x) -> x * f+b;
    }

}
