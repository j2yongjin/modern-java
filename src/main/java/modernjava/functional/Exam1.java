package modernjava.functional;

import java.util.function.Function;

/**
 * Created by yjlee on 2019-02-03.
 */
public class Exam1 {

    public static void main(String[] args){

       TrainJourney  gumiToBusan =  new TrainJourney(0, new TrainJourney(1000,null));
       TrainJourney seoulToGumi = new TrainJourney(0,new TrainJourney(200, null));
//
//       TrainJourney seoulToBusan = OldLinkedList.link(seoulToGumi,gumiToBusan);
//       System.out.println("price :" + seoulToBusan.price + " , toBusan : "
//               + seoulToBusan.onward);

        TrainJourney stob = FunctionalLinkedList.append(seoulToGumi,gumiToBusan);
        System.out.println("price : " + stob.price);


    }
    // linked list

    static class OldLinkedList {

        static TrainJourney link (TrainJourney a , TrainJourney b){
           if(a == null) return b;
           TrainJourney t = a;
           while (t.onward != null){
               t = t.onward;
           }
           t.onward = b;
           System.out.println( " a hash : " + a.hashCode() + " , b : " + b.hashCode());
           return a;
        }
    }

    static class FunctionalLinkedList{
        static TrainJourney append(TrainJourney a , TrainJourney b){
            return a == null ? b : new TrainJourney(a.price, append(a.onward,b));
        }
    }

    static class TrainJourney{
        int price;
        TrainJourney onward;

        public TrainJourney(int price, TrainJourney onward) {
            this.price = price;
            this.onward = onward;
        }
    }


}
