package modernjava.lamda;

import java.util.Comparator;

public class RamdaScopeExample {

    public static void main(String[] args){

        SocopeSample socopeSample = new SocopeSample();
        socopeSample.doWork();

//        int first = 0;
        Comparator<String> comp = (first,second) -> first.length() - second.length();

        repeatMessage("test message",100);
    }

    static void repeatMessage(String text ,Integer count){
        Runnable runnable = () -> {
            for(int i=0;i<count;i++){
                System.out.println(" text : " + text);
            }
        };
        new Thread(runnable).start();
    }

    static class SocopeSample {
        public void doWork(){

            System.out.println(" parent toString : " + this.toString());

            Runnable runnable = () -> System.out.println(this.toString());
            runnable.run();
        }
    }

}
