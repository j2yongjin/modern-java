package modernjava;

/**
 * Created by yjlee on 2018-12-16.
 */
public class TestAbstractApplication {

    public static void main(String[] args){

        TestAbstractA testAbstractA = new TestAbstractImpl();
        testAbstractA.run1();

        TestAbstractImpl testAbstract = new TestAbstractImpl();
        testAbstract.run1();

        interface1 in = new interfaceImpl();
        in.inter1();

        interface2 in2 = new interfaceImpl();
        in2.inter2();

        combinationInterface combinationInterface = new interfaceImpl();
        combinationInterface.inter1();
        combinationInterface.inter2();
        combinationInterface.defaultTest();




    }



}

abstract class TestAbstractA{
    public void run1(){
        test1();
    }
    protected abstract void test1();
}

class TestAbstractImpl extends TestAbstractA{

    @Override
    protected void test1() {
        System.out.println(" test 1");
    }
}
interface interface1 {
    void inter1();
    default void defaultTest(){
        System.out.println("defaultTest");
    }


}

interface interface2{
    void inter2();

}

class interfaceImpl implements combinationInterface {

    @Override
    public void inter1() {
        System.out.println(" test1");
    }

    @Override
    public void inter2() {
        System.out.println(" test2");
    }
}

interface  combinationInterface extends interface1,interface2{

}

