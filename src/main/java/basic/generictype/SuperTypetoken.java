package basic.generictype;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by yjlee on 2018-10-14.
 */
public class SuperTypetoken {

    static class Sup<T> {
        T value;
    }

    static class Sub extends Sup<String>{

    }

    // nested static class
    static class SubList extends Sup<List<String>>{

    }

    // ERASURE     (JAVA)
    // REIFICATION ( C# )
    public static void main(String[] args) throws NoSuchFieldException {


        Sup<String> sup = new Sup<>();
        System.out.println(
            sup.getClass().getDeclaredField("value").getType()
        );

        // generic parameter type을 확인 가능함
        Sub sub = new Sub();
        Type t = sub.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType)t;
        System.out.println(parameterizedType.getActualTypeArguments()[0]);

        // generic parameter
        SubList sublist = new SubList();
        Type tlist = sublist.getClass().getGenericSuperclass();
        ParameterizedType parameterizedTypelist = (ParameterizedType)tlist;
        System.out.println(parameterizedTypelist.getActualTypeArguments()[0]);

        // local class
        class SubLocal extends Sup<List<String>>{

        }
        SubLocal subLocal = new SubLocal();
        Type tlocal = subLocal.getClass().getGenericSuperclass();
        parameterizedType = (ParameterizedType)tlocal;
        System.out.println(parameterizedType.getActualTypeArguments()[0]);

        // anonymous class
        Sup supAnon = new Sup<String>() {
        };
        t = supAnon.getClass().getGenericSuperclass();
        parameterizedType = (ParameterizedType)t;
        System.out.println(parameterizedType.getActualTypeArguments()[0]);




    }

}
