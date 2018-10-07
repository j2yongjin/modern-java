package java.basic.innerclass;

import java.util.ArrayList;

public class Network {
    public class Member{
        private String name;
        private ArrayList<Member> friends;

        public Member(String name){
            this.name = name;
            this.friends = new ArrayList<>();
        }

        public void deactivate(){
            unenroll(this);  // inner class 는 outer class의 멤버 ( members)에 접근 가능
        }

    }

    private ArrayList<Member> members = new ArrayList<>();

    public Member enroll(String name){
        Member newMember = new Member(name);
        members.add(newMember);
        return newMember;
    }

    public void unenroll(Member member){
        members.remove(member);
    }

}
