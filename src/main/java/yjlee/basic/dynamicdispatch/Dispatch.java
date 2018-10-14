package yjlee.basic.dynamicdispatch;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yjlee on 2018-10-14.
 */
public class Dispatch {
    // static dispatch   , dynamic dispatch

    // case is static dispatch :  이미 함수 호출이 결정된 경우
    static class StaticDispatchService {
        void run(int number){
            System.out.println("run()" + number);
        }
        void run(String mag){
            System.out.println("run()" +mag);
        }
    }
    static abstract class AbstractDispatchService {
        abstract void run();
    }

    static class DispatchMyService extends AbstractDispatchService {

        @Override
        void run() {
            System.out.println("run1");
        }
    }

    static class DispatchMyService2 extends AbstractDispatchService {

        @Override
        void run() {
            System.out.println("run2");
        }
    }




    public static void main(String[] args){
        new StaticDispatchService().run(1111);
        new StaticDispatchService().run("Dispatch");

        DispatchMyService service1 = new DispatchMyService();
        service1.run();

        DispatchMyService2 service2 = new DispatchMyService2();
        service2.run();

        // dynamic dispatch
        AbstractDispatchService dispatchService = new DispatchMyService();
        dispatchService.run();  // receiver parameter   ( this 을 이용해 호출)

        dispatchService = new DispatchMyService2();
        dispatchService.run();  // receiver parameter   ( this 을 이용해 호출)

        List<AbstractDispatchService> svcList = Arrays.asList(new DispatchMyService() , new DispatchMyService2());
        svcList.forEach(AbstractDispatchService::run);  // consumer
        // svcList.forEach( s -> s.run());

        // method reference
        // Method Signature
        // name , parameter types

        // Method Type
        // return type , method type parameter , method argument types , exception
        // ==> Method Reference 에서 사용가능함

    }

}
