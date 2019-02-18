package modernjava.completeablefuture.example;

import java.util.concurrent.*;

/**
 * Created by yjlee on 2019-01-27.
 */
public class exam1 {

    public static void main(String[] args){

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Double> future = executorService.submit(
                new Callable<Double>() {
                    @Override
                    public Double call() throws InterruptedException {
                        Double result = doSomethingComputation();
                        System.out.println("executor service result : " + result);
                        Thread.sleep(100);
                        return result;
                    }

                    private Double doSomethingComputation() {

                        return 10.0;
                    }
                }
        );

        doSomeThingElse();

        try {
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        executorService.shutdown();


    }

    private static void doSomeThingElse() {
        System.out.println("doSomeThingElse");
    }
}
