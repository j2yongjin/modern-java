package yjlee.basic.generictype;

import java.util.*;

/**
 * Created by yjlee on 2018-10-14.
 */
public class TypeToken {

    static class TypeSafeMap {
        Map<Class<?>,Object> map = new HashMap<>();
        <T> void put(Class<T> clazz,T value){
            map.put(clazz,value);
        }

        <T> T get(Class<T> clazz){
            return clazz.cast(map.get(clazz));
        }
    }

    public static void main(String[] args){

        TypeSafeMap typeSafeMap = new TypeSafeMap();
        typeSafeMap.put(Integer.class,1);
        typeSafeMap.put(String.class,"String");
        typeSafeMap.put(List.class, Arrays.asList("1",1,2));

        System.out.println( typeSafeMap.get(Integer.class));
        System.out.println( typeSafeMap.get(String.class));
        System.out.println( typeSafeMap.get(List.class));

        //typeSafeMap.put(List.class , Arrays.asList("1",2));  값을 덮어 씌움 같은  List class 타입

        // 사용할수 없음
        // typeSafeMap.put(List<String>.class,Arrays.asList("1","2"));
        // typeSafeMap.put(List<Integer>.class,Arrays.asList(1,2));


    }

}
