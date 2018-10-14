package yjlee.basic.inter;

public interface IntSequence {
    boolean hasNext();
    int next();

}

class DigitSequence implements IntSequence{

    int number;
    public DigitSequence(int n){
        number = n;
    }

    @Override
    public boolean hasNext() {
        return number!=0;
    }

    @Override
    public int next() {
        int result = number % 10;
        number /=10;
        return result;
    }
}

class SquareSequence implements IntSequence{

    private  int i;
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public int next() {
        return i * i;
    }
}
