package yjlee.basic.dynamicdispatch;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yjlee on 2018-10-14.
 */
public class DoubleDynamicDispatch {

    //  Double Dispatch 사용

    // 등록     : 구조
    interface Post{
        void postOn(Sns sns);
    }

    //  Text 등록
    static class Text implements Post{
        @Override
        public void postOn(Sns sns) {

            sns.post(this);

//            if( sns instanceof FaceBook) {  // anti pattern
//                System.out.println(" text --> " + "FaceBook");
//            }
//
//            if(sns instanceof Twitter){
//                System.out.println(" text --> " + "Twitter");
//            }

        }

    }

    // 그림 등록
    static class Picture implements Post{
        @Override
        public void postOn(Sns sns) {
            sns.post(this);
//            if(sns instanceof FaceBook)   // anti pattern
//                System.out.println(" picture --> " + "facebook");
//
//            if(sns instanceof Twitter)
//                System.out.println(" picture --> " + "twitter");
//
        }
    }

    //  Sns    :  추가되는 구현   , 요소가 추가 될 경우  class 구현
    interface Sns{
        void post(Text post);
        void post(Picture post);
    }

    // facebook sns 구현
    static class FaceBook implements Sns{
        @Override
        public void post(Text post) {
            System.out.println(" text-facebook");
        }

        @Override
        public void post(Picture post) {

            System.out.println("picture-facework");
        }
    }

    // facebook sns 구현
    static class Twitter implements Sns{

        @Override
        public void post(Text post) {
            System.out.println(" text-twitter");
        }

        @Override
        public void post(Picture post) {

            System.out.println("picture-facework");
        }
    }

    static class GooglePlus implements Sns{

        @Override
        public void post(Text post) {
            System.out.println("text-googlePlus");
        }

        @Override
        public void post(Picture post) {
            System.out.println("picture-googlePlus");
        }
    }

    public static void main(String [] args) {
        List<Post> posts = Arrays.asList(new Text(), new Picture());
        List<Sns> sns = Arrays.asList(new FaceBook(), new Twitter() , new GooglePlus());

        posts.forEach(p -> sns.forEach( s -> p.postOn(s)));

        // equal
//        for(Post post:posts){    // post roof
//            for(Sns sns1 : sns){    // sns roof
//                post.postOn(sns1);
//            }
//        }




    }





}
