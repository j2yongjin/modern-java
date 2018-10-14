package java.basic.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ConstructReferenceMain {

    public static void main(String[] args){
        List<String> names = Arrays.asList("yjlee");
        Stream<Employee> stream = names.stream().map(Employee::new);

    }
}

class Employee{
    public Employee(String name){
        this.name = name;
    }

    public Employee(){

    }

    String name;
    Integer age;
}
