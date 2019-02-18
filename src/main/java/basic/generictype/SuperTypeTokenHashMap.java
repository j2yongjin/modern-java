package basic.generictype;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yjlee on 2018-10-14.
 */
public class SuperTypeTokenHashMap {

    static class TypeSafeMap {
        Map<TypeReference<?>,Object> map = new HashMap<>();
        <T> void put(TypeReference<T> tr,T value){
            map.put(tr,value);
        }

        <T> T get(TypeReference<T> tr){
//            return ((Class<T>)tr.type).cast(map.get(tr));   // TypeReference<String>
            if(tr.type instanceof Class<?>){
                return ((Class<T>)tr.type).cast(map.get(tr));
            }else
                return ((Class<T>)((ParameterizedType)tr.type).getRawType()).cast(map.get(tr));
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

        // equal 함수 와  hash 함께 구현
        // map get 함수는 equal 함수를 사용한다.
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;  // 변경
            if(o == null || getClass().getSuperclass() != o.getClass().getSuperclass())
                return false;

            TypeReference<?> that = (TypeReference<?>) o;

            return type != null ? type.equals(that.type) : that.type == null;
        }

        @Override
        public int hashCode() {
            return type != null ? type.hashCode() : 0;
        }
    }

    // Super Type Token
    public static void main(String[] args){

        TypeReference t = new TypeReference<String>(){};
        System.out.println(t.type);

        TypeSafeMap map = new TypeSafeMap();
        map.put(new TypeReference<Integer>() {} , 1);
        map.put(new TypeReference<String>(){},"String");
        map.put(new TypeReference<List<Integer>>(){},Arrays.asList(1,2,3));
        map.put(new TypeReference<List<String>>(){},Arrays.asList("a","b","c"));

        System.out.println(map.get(new TypeReference<Integer>(){}));
        System.out.println(map.get(new TypeReference<String>(){}));
        System.out.println(map.get(new TypeReference<List<Integer>>(){}));
        System.out.println(map.get(new TypeReference<List<String>>(){}));

//        TypeSafeMap typeSafeMap = new TypeSafeMap();
//        typeSafeMap.put(Integer.class,1);
//        typeSafeMap.put(String.class,"String");
//        typeSafeMap.put(List.class, Arrays.asList("1",1,2));
//
//        System.out.println( typeSafeMap.get(Integer.class));
//        System.out.println( typeSafeMap.get(String.class));
//        System.out.println( typeSafeMap.get(List.class));
//
//        //typeSafeMap.put(List.class , Arrays.asList("1",2));  값을 덮어 씌움 같은  List class 타입
//
//        // 사용할수 없음
//        // typeSafeMap.put(List<String>.class,Arrays.asList("1","2"));
//        // typeSafeMap.put(List<Integer>.class,Arrays.asList(1,2));


    }
}
