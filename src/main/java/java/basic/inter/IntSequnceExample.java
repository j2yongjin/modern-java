package java.basic.inter;

public class IntSequnceExample {
    public static void main(String[] args){
        IntSequence digits = new DigitSequence(1729);
        double avg = average(digits,100);
    }

    private static double average(IntSequence digits, int i) {
        int count = 0;
        double sum = 0;
        while (digits.hasNext() && count<i){
            count ++;
            sum += digits.next();

        }
        return count ==0 ? 0: sum/count;
    }
}
