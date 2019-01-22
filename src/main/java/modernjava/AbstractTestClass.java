package modernjava;

import java.util.List;

/**
 * Created by yjlee on 2018-12-13.
 */
public abstract class AbstractTestClass {

    List<String> list;

    public AbstractTestClass(){};
    public AbstractTestClass(List<String> list) {
        this.list = list;
    }

    protected void remove(){
        System.out.println(" remove");
    }

    abstract void add();

    public void addAll(){
        add();
    }


}
