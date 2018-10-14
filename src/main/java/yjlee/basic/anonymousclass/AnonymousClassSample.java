package yjlee.basic.anonymousclass;

public class AnonymousClassSample {



    public static void main(String[] args){

    }

    public static IntSequence randomInt(int low,int high){
        class RandomSequence implements IntSequence{
            @Override
            public int next() {
                return 0;
            }
        };
        return new RandomSequence();
    }
}

interface IntSequence {
    int next();

}

//class RandomSequence implements IntSequence{
//
//}
