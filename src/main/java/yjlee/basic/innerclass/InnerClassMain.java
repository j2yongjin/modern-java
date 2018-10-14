package yjlee.basic.innerclass;

public class InnerClassMain {
    public static void main(String[] args) {

        Network network = new Network();
        Network.Member member = network.enroll("tom");

        member.deactivate();
    }
}
