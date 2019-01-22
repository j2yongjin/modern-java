package modernjava;

/**
 * Created by yjlee on 2018-12-13.
 */
public class ForwardingClass implements CommonInterface{
    @Override
    public void add() {
        System.out.println("addd");
    }

    @Override
    public void remove() {
        System.out.println("remove");

    }

    @Override
    public void addAll() {
        System.out.println("addall");
    }
}
