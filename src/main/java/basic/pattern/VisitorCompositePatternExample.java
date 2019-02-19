package basic.pattern;

/**
 * Created by yjlee on 2018-10-14.
 */
public class VisitorCompositePatternExample {

    static interface Base{
        void execute(Base target);
        void doJob(Foo foo);
        void doJob(Bar bar);
        void doJob(Baz baz);
    }

    static class Foo implements Base{

        @Override
        public void execute(Base target) {
            target.doJob(this);
        }

        @Override
        public void doJob(Foo foo) {
            System.out.println("foo object calls by yourself");
        }

        @Override
        public void doJob(Bar bar) {
            System.out.println("bar object calls from foo");
        }

        @Override
        public void doJob(Baz baz) {
            System.out.println("baz object calls from foo");
        }
    }

    static class Bar implements Base{

        @Override
        public void execute(Base target) {
            target.doJob(this);
        }

        @Override
        public void doJob(Foo foo) {
            System.out.println("foo object calls from bar");
        }

        @Override
        public void doJob(Bar bar) {
            System.out.println("bar object calls by yourself");
        }

        @Override
        public void doJob(Baz baz) {
            System.out.println("baz object calls from bar");
        }
    }

    static class Baz implements Base{

        @Override
        public void execute(Base target) {
            target.doJob(this);
        }

        @Override
        public void doJob(Foo foo) {
            System.out.println("foo object calls from baz");
        }

        @Override
        public void doJob(Bar bar) {
            System.out.println("bar object calls from baz");
        }

        @Override
        public void doJob(Baz baz) {
            System.out.println("baz object calls by yourself");
        }
    }

    public static void main(String[] args){
        Base object[] = {new Foo(),new Bar(),new Baz()};

        for(Base base:object){
            for(int i=0;i<object.length;i++) {
                base.execute(object[i]);
            }
            System.out.println("=================================");
        }
    }


}
