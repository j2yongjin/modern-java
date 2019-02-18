package modernjava.functional;

/**
 * Created by yjlee on 2019-02-03.
 */
public class Exam2 {

    public static void main(String[] args){


    }

    static class Tree{
        private String key;
        private int val;
        private Tree left , right;

        public Tree(String key, int val, Tree left, Tree right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class TreeProcessor{
        public static int lookup(String k , int defaultVal , Tree t){

            if(t == null) return defaultVal;
            if(k.equals(t.key)) return t.val;
            return lookup(k,defaultVal,k.compareTo(t.key) < 0 ? t.left : t.right);

        }

        public static void add(String k , int val){


        }

        public static Tree update(String k , int newval , Tree t){
            if(t==null) {
                // 새로운 값 추가
            }else if(k.equals(t.key)){
                t.val = newval;
            }else if(k.compareTo(t.key) < 0){
                t.left = update(k,newval,t.left);
            }else{
                t.right = update(k,newval,t.right);
            }

            return t;
        }

        public static Tree fupdate(String k , int newval,Tree t){
            return (t == null) ? new Tree(k,newval,null , null)
                    :   k.equals(t.key) ?
                            new Tree(k,newval,t.left,t.right)
                            : k.compareTo(t.key) < 0 ?
                            new Tree(t.key, t.val,fupdate(k,newval,t.left),t.right) :
                            new Tree(t.key,t.val,t.left,fupdate(k,newval,t.left));
        }
    }
}
