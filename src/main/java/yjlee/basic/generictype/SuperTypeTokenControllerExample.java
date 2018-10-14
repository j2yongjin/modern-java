package yjlee.basic.generictype;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yjlee on 2018-10-14.
 */
@SpringBootApplication
public class SuperTypeTokenControllerExample {

    public static void main(String[] args){

        SpringApplication.run(SuperTypeTokenControllerExample.class,args);
    }

    static class GenericService<T>{
        @Autowired
        T t;
    }

    @Component
    static class MyService extends GenericService<MyBean1>{
    }

    @Component
    static class MyService2 extends GenericService<MyBean2>{
    }

    @Component
    static class MyBean2{
    }

    @Component
    static class MyBean1{
    }




    @RestController
    public static class MyController{

        @RequestMapping("/")
        public List<User> users(){
            return Arrays.asList(new User("a"),new User("b"),new User("c"));
        }

    }

    public static class User{
        String name;

        public User(String name) {
            this.name = name;
        }

        public User(){

        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
