package basic.pattern;

/**
 * Created by yjlee on 2018-10-14.
 *
 * 데코레이터 패턴(Decorator pattern)이란 주어진 상황 및 용도에 따라
 * 어떤 객체에 책임을 덧붙이는 패턴으로,
 * 기능 확장이 필요할 때 서브클래싱 대신 쓸 수 있는 유연한 대안이 될 수 있다
 * 참고 : https://sourcemaking.com/design_patterns/decorator/java/1
 */
public class DecoratorPatternExample {

    static interface I{
        void doIt();
    }

    static class A implements I{

        @Override
        public void doIt() {
            System.out.println("A");
        }
    }

    static abstract class D implements I{
        private I core;
        public D (I inner){
            core = inner;
        }
        public void doIt(){
            core.doIt();
        }

    }

    static class X extends D{

        public X(I inner) {
            super(inner);
        }

        public void doIt(){
            super.doIt();
            doX();
        }
        private void doX(){
            System.out.println("X");
        }
    }

    static class Y extends D{

        public Y(I inner) {
            super(inner);
        }

        public void doIt(){
            super.doIt();
            doY();
        }
        private void doY(){
            System.out.println("Y");
        }
    }

    static class Z extends D{

        public Z(I inner) {
            super(inner);
        }

        public void doIt(){
            super.doIt();
            doZ();
        }
        private void doZ(){
            System.out.println("Z");
        }
    }

    public static void main(String[] args){
        I[] array = {
                new X(new A()) ,new Y(new X(new A())) ,
                new Z(new Y(new X(new A())))
        };

        for(I i:array){
            i.doIt();
            System.out.println("=======================");
        }
    }

}
