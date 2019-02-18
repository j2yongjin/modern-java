package basic.generictype;

import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yjlee on 2018-10-14.
 */
public class SpringTypeReference {

    public static void main(String[] args){

        //  익명 클래스
        ParameterizedTypeReference type = new ParameterizedTypeReference<List<Map<Set<Integer>,String>>>() {};

        // type
        System.out.println(type.getType());

        //

    }
}
