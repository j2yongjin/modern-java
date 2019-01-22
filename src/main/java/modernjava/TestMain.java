package modernjava;

import java.util.ArrayList;

/**
 * Created by yjlee on 2018-12-13.
 */
public class TestMain {

    public static void main(String[] args) {

        ImplClass implClass = new ImplClass(new ArrayList<>());
        implClass.remove();

        implClass.addAll();

    }
}
