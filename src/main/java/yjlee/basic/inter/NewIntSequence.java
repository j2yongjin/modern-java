package yjlee.basic.inter;

public interface NewIntSequence {

    static NewIntSequence digitOf(int n){
        return new NewDigitSequence(n);
    }

    default boolean hasNext(){
        return true;
    }

    int next();

}

class NewDigitSequence implements NewIntSequence{
    int n=0;
    public NewDigitSequence(int n){
        this.n = n;
    }

    @Override
    public int next() {
        return 0;
    }
}
