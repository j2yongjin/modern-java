package modernjava;

/**
 * Created by yjlee on 2018-12-13.
 */
public class InstrumentClass extends ForwardingClass{

    @Override
    public void add() {
        System.out.println(" Instrument add call");
        super.add();
    }
}
