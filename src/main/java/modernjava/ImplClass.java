package modernjava;

import java.util.List;

/**
 * Created by yjlee on 2018-12-13.
 */
public class ImplClass extends AbstractTestClass{

    public ImplClass(){

    }

    public ImplClass(List<String> list) {
        super(list);
    }

    @Override
    protected void add() {
        System.out.println("add");
    }

//    public void remove(){
//        System.out.println(" impl class");
//    }




}
