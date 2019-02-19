package basic.pattern;

/**
 * Created by yjlee on 2018-10-14.
 */
public class VisitorPatternExample2 {

    // 요소
    static interface Element{
        void accept(Visitor v);
    }

    static class Foo implements Element{

        @Override
        public void accept(Visitor v) {
            v.visit(this);
        }
        public String getFoo(){
            return "foo";
        }
    }

    static class Bar implements Element{

        @Override
        public void accept(Visitor v) {
            v.visit(this);
        }

        public String getBar(){
            return "bar";
        }


    }
    static class Baz implements Element{

        @Override
        public void accept(Visitor v) {
            v.visit(this);
        }

        public String getBaz(){
            return "baz";
        }
    }

    static interface Visitor {
        void visit(Foo foo);
        void visit(Bar bar);
        void visit(Baz baz);
    }

    static class UpVisitor implements Visitor{

        @Override
        public void visit(Foo foo) {
            System.out.println(" up " + foo.getFoo());
        }

        @Override
        public void visit(Bar bar) {
            System.out.println(" up " + bar.getBar());
        }

        @Override
        public void visit(Baz baz) {
            System.out.println(" up " + baz.getBaz());
        }
    }

    static class DownVisitor implements Visitor{

        @Override
        public void visit(Foo foo) {
            System.out.println(" down " + foo.getFoo());
        }

        @Override
        public void visit(Bar bar) {
            System.out.println(" down " + bar.getBar());
        }

        @Override
        public void visit(Baz baz) {
            System.out.println(" down " + baz.getBaz());
        }
    }

    public static void main(String[] args){

        // 요소 등록
        Element[] element = {new Foo(),new Bar(),new Baz()};

        // 방문자
        UpVisitor upVisitor = new UpVisitor();
        DownVisitor downVisitor = new DownVisitor();

        // 실행
        for(Element element1 : element){
            element1.accept(upVisitor);
        }

        for(Element element1: element){
            element1.accept(downVisitor);
        }
    }
}
