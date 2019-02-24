package basic.concurrent;

import lombok.extern.slf4j.Slf4j;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by yjlee on 2019-02-22.
 */
@Slf4j
public class DataSync {

    public static void main(String[] args) throws InterruptedException {

        int size = 15;
        DataPool dataPool = new DataPool(size);

        Thread.sleep(100);

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(size *10 );

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(new MultiWorker(threadPoolExecutor,dataPool) );

        executorService.submit(new MultiWorker(threadPoolExecutor,dataPool) );

        executorService.submit(new MultiWorker(threadPoolExecutor,dataPool) );


    }

    static class MultiWorker implements Runnable {

        ThreadPoolExecutor threadPoolExecutor;
        DataPool dataPool;

        public MultiWorker(ThreadPoolExecutor threadPoolExecutor, DataPool dataPool) {
            this.threadPoolExecutor = threadPoolExecutor;
            this.dataPool = dataPool;
        }

        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()){
                if(threadPoolExecutor.getActiveCount() < threadPoolExecutor.getMaximumPoolSize()) {
                    threadPoolExecutor.submit(new Worker(dataPool));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }

    static class Worker implements Runnable{

        DataPool dataPool;

        public Worker(DataPool dataPool) {
            this.dataPool = dataPool;
        }

        @Override
        public void run() {
            Optional<Object> object = Optional.empty();
            int count = 15;
            while (!Thread.currentThread().isInterrupted()){

                object=dataPool.getPool();
                log.info(" obj {} , size {} " , object , dataPool.size());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                log.info(" get pool " + object);
                if(object.isPresent()) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dataPool.release(object.get());
//                    log.info(" release count :" + dataPool.size());
                }else{
                    if(count <=0) {
                        log.info(" get pool fail count : " + dataPool.size());
                        count = 15;
                    }
                    count--;
                }
            }

            dataPool.release(object.get());

        }
    }

    static class DataPool {

        Map<Object,Status> dataPoolMaps = new ConcurrentHashMap<>();


        int size;
        AtomicInteger atomicInteger ;
        public DataPool(int size){
            atomicInteger = new AtomicInteger(size);
            for(int i = 0;i<size;i++)
                dataPoolMaps.put(new Object() , Status.READY);
        }

        public Optional<Object> getPool(){
            Optional<Object> optional = Optional.empty();
//            optional = dataPoolMaps.entrySet().stream().map(s -> {
//                synchronized (s.getKey()){
//                    if(s.getValue().equals(Status.READY)){
//                        s.setValue(Status.USE);
//                        log.info(" get pool decrease : " + atomicInteger.get());
//                        atomicInteger.decrementAndGet();
//                        return s.getKey();
//                    }
//                    return null;
//                }
//            }).filter(s ->s!=null).findAny();

            List<Object> objs = dataPoolMaps.keySet().stream().collect(Collectors.toList());

            for(Object obj : objs){
                synchronized (obj){
                    Status status = dataPoolMaps.get(obj);
//                    log.info(" obj {} , status {} " , obj , status);
                    if(status.equals(Status.READY)){
                        dataPoolMaps.put(obj,Status.USE);
                        atomicInteger.decrementAndGet();
                        return Optional.of(obj);
                    }
                }

            }


            return optional;
        }

        public void release(Object object){
            if(object == null) return;
            synchronized (object){
                if(dataPoolMaps.get(object).equals(Status.USE)) {
                    dataPoolMaps.put(object, Status.READY);
//                    System.out.println(" PoolMaps size Increse :" + dataPoolMaps.size());
                    atomicInteger.incrementAndGet();
                }

            }
        }

        public int size(){
            return atomicInteger.get();
        }


    }

    enum Status{
        USE , READY
    }
}
