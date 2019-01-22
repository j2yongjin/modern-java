package yjlee.basic.generictype;

import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yjlee on 2018-10-14.
 */
public class SuperTypeTokenHashMapAdvance {
    static class TypeSafeMap {
        Map<Type,Object> map = new HashMap<>();
        <T> void put(SuperTypeTokenHashMap.TypeReference<T> tr, T value){
            map.put(tr.type,value);
        }

        <T> T get(SuperTypeTokenHashMap.TypeReference<T> tr){
            if(tr.type instanceof Class<?>){
                return ((Class<T>)tr.type).cast(map.get(tr.type));
            }else
                return ((Class<T>)((ParameterizedType)tr.type).getRawType()).cast(map.get(tr.type));
        }
    }

    static class TypeReference<T>{
        Type type;

        public TypeReference() {
            Type stype = getClass().getGenericSuperclass();
            if(stype instanceof ParameterizedType){
                this.type = ((ParameterizedType) stype).getActualTypeArguments()[0];
            }else throw new RuntimeException();
        }

    }

    // Super Type Token
    public static void main(String[] args){

        SuperTypeTokenHashMap.TypeReference t = new SuperTypeTokenHashMap.TypeReference<String>(){};
        System.out.println(t.type);

        SuperTypeTokenHashMap.TypeSafeMap map = new SuperTypeTokenHashMap.TypeSafeMap();
        map.put(new SuperTypeTokenHashMap.TypeReference<Integer>() {} , 1);
        map.put(new SuperTypeTokenHashMap.TypeReference<String>(){},"String");
        map.put(new SuperTypeTokenHashMap.TypeReference<List<Integer>>(){}, Arrays.asList(1,2,3));
        map.put(new SuperTypeTokenHashMap.TypeReference<List<String>>(){},Arrays.asList("a","b","c"));

        System.out.println(map.get(new SuperTypeTokenHashMap.TypeReference<Integer>(){}));
        System.out.println(map.get(new SuperTypeTokenHashMap.TypeReference<String>(){}));
        System.out.println(map.get(new SuperTypeTokenHashMap.TypeReference<List<Integer>>(){}));
        System.out.println(map.get(new SuperTypeTokenHashMap.TypeReference<List<String>>(){}));

        //
        System.out.println(new SuperTypeTokenHashMap.TypeReference<List<String>>(){}.type);

        // spring 4에 구현됨
        ResolvableType rt = ResolvableType.forInstance(new TypeReference<List<String>>(){});
        System.out.println(rt.getSuperType().getGeneric(0).getNested(2).getType());


    }
}
