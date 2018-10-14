package yjlee.pattern;

/**
 * Created by yjlee on 2018-10-14.
 * 객체 지향 프로그래밍과 소프트웨어 공학에서 비지터 패턴(visitor pattern; 방문자 패턴)은 알고리즘을 객체 구조에서 분리시키는 디자인 패턴이다.
 * 이렇게 분리를
 * 하면 구조를 수정하지 않고도 실질적으로 새로운 동작을 기존의 객체 구조에 추가할 수 있게 된다. 개방-폐쇄 원칙을 적용하는 방법의 하나이다
 */
public class VisitorPatternExample1 {

    static interface CarElementVisitor{
        void visit(Wheel wheel);
        void visit(Engine engine);
        void visit(Body body);
        void visit(Car car);
    }

    static interface CarElement{
        void accept(CarElementVisitor visitor);
    }

    static class  Wheel implements CarElement{
        private String name;

        public Wheel(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void accept(CarElementVisitor visitor) {
            visitor.visit(this);
        }
    }

    static class Engine implements CarElement{

        @Override
        public void accept(CarElementVisitor visitor) {
            visitor.visit(this);
        }
    }

    static class Body implements CarElement{

        @Override
        public void accept(CarElementVisitor visitor) {
            visitor.visit(this);
        }
    }

    static class Car implements CarElement{

        CarElement[] carElements;

        public Car() {
            this.carElements = new CarElement[] {
                    new Wheel("front left") ,new Wheel("front right")
                    ,new Wheel("back left"),new Wheel("back right")
                    ,new Engine(),new Body()
            };
        }

        @Override
        public void accept(CarElementVisitor visitor) {

            for(CarElement carElement : this.carElements){
                carElement.accept(visitor);
            }

            visitor.visit(this);
        }
    }

    static class CarElementPrinterVisitor implements CarElementVisitor {

        @Override
        public void visit(Wheel wheel) {

            System.out.println("wheel " + wheel.getName());
        }

        @Override
        public void visit(Engine engine) {
            System.out.println("engine");

        }

        @Override
        public void visit(Body body) {
            System.out.println("body");
        }

        @Override
        public void visit(Car car) {
            System.out.println("car");
        }
    }

    static class CarElementDoVisitor implements CarElementVisitor{

        @Override
        public void visit(Wheel wheel) {
            System.out.println("Kicking wheel " + wheel.getName());
        }

        @Override
        public void visit(Engine engine) {

            System.out.println("start engine");
        }

        @Override
        public void visit(Body body) {
            System.out.println(" start body");
        }

        @Override
        public void visit(Car car) {
            System.out.println(" start body");
        }
    }

    public static void main(String[] args){
        Car car = new Car();
        car.accept(new CarElementPrinterVisitor());
        car.accept(new CarElementDoVisitor());
    }


}
