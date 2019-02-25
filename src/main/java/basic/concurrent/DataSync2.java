package basic.concurrent;

import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by yjlee on 2019-02-25.
 */
public class DataSync2 {

    public static void main(String[] args) throws InterruptedException {


        int nThreads = 20;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        CountDownLatch countDownLatch = new CountDownLatch(nThreads);

        DataSyncSample dataSyncSample = new DataSyncSample();
        int count = 20000;
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));

        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));

        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));

        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));
        executorService.submit(new Worker(dataSyncSample,count,countDownLatch));

        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.MILLISECONDS);


    }

    @Slf4j
    static class Worker implements Runnable{

        final DataSyncSample dataSyncSample;
        int count;
        final CountDownLatch countDownLatch;

        public Worker(DataSyncSample dataSyncSample,int count,CountDownLatch countDownLatch) {
            this.dataSyncSample = dataSyncSample;
            this.countDownLatch = countDownLatch;
            this.count = count;
        }

        @Override
        public void run() {

            try {
                countDownLatch.countDown();
                countDownLatch.await();
                log.info(" ============================= start ===============================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(int i=0;i<count;i++){

                KeyData object = null;
                object = dataSyncSample.increase();

                log.info("new Obj {}" , object);

                if (object != null)
                    dataSyncSample.release(object);
            }

            log.info(" data number {} , keyData {} , getFreeSize {}  "
                    , dataSyncSample.getData() , dataSyncSample.getKeyDataNumber() , dataSyncSample.getFreeSize());
        }
    }

    @Slf4j
    static class DataSyncSample{

        int data = 0;
        volatile Map<KeyData,AtomicInteger> maps = new ConcurrentHashMap<>();

        static final int READY =1;
        static final int USE =2;
        volatile List<KeyData> values = null;

        public IncreaseSampleData increaseSampleData = new IncreaseSampleData();




        Set<Object> sets = new HashSet<>();

        public DataSyncSample() {
            maps.put(new KeyData() , new AtomicInteger(READY));
            maps.put(new KeyData() , new AtomicInteger(READY));
            maps.put(new KeyData() , new AtomicInteger(READY));
            maps.put(new KeyData() , new AtomicInteger(READY));
            maps.put(new KeyData() , new AtomicInteger(READY));
            values = maps.keySet().stream().collect(Collectors.toList());
        }

        public int getFreeSize(){
//            return maps.entrySet().stream().map(s -> s.getValue()).filter(s ->s.get() == READY).reduce(
//                    new AtomicInteger(0)
//                    ,(a,b) ->a.intValue() + b.intValue());
            return maps.size();
        }

        public KeyData increase(){

//            data+=1;
            increaseSampleData.numberData +=1;

//            List<Object>
            List<KeyData> values = maps.keySet().stream().collect(Collectors.toList());

            for(KeyData object : values){

                    AtomicInteger status = maps.get(object);
                    log.info("status {} ", status);

                    if(status.compareAndSet(READY,USE)) {
                        if (!sets.add(object)) {
                            log.info(" already obj {} , size {}  ", object, maps.size());
                        }
                        return object;
                    }
//                    if (status == READY) {
//                        status = USE;
//                        maps.put(object, status);
//                        if (!sets.add(object)) {
//                            log.info(" already obj {} , size {}  ", object, maps.size());
//                        }
//                        return object;
//                    }
                }

//                AtomicInteger atomicInteger = maps.get(object);
//                log.info("atomic {} " , atomicInteger);
//                if(atomicInteger.get() == READY){
//                   atomicInteger.incrementAndGet();
//                    if(!sets.add(object)){
//                        log.info(" already obj {} , size {} " , object , sets.size());
//                    }
//                    return object;
//                }
//                object.keyIncrement+=1;
//            }



            return null;
        }

        public void release(KeyData object){

//            data -=1;


            AtomicInteger status = maps.get(object);

            if(status.compareAndSet(USE,READY)){
                sets.remove(object);
            }else{
                log.info(" fail release");
            }

//            if (status == USE) {
////                status.decrementAndGet();
//                maps.put(object, READY);
//                sets.remove(object);
////                log.info(" fail release");
//            } else {
//                log.info(" fail release");
//            }

//            if(!status.compareAndSet(USE,READY)){
//                log.info(" fail release");
//            }
        }


        public int getData() {
            return increaseSampleData.numberData;
        }

        public int getKeyDataNumber(){
            return values.stream().map(s -> s.keyIncrement).reduce(0,(a,b) ->  a + b);
        }
    }

    static class IncreaseSampleData {

        public Integer numberData= 0;
        public Integer status = 0;



    }

    static class KeyData{
        int keyIncrement=0;
    }

}



