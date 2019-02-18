package modernjava.functional;

/**
 * Created by yjlee on 2019-02-04.
 */
public class Exam5 {

    public static void main(String []args){

        SimplifyExprVisitor simplifyExprVisitor = new SimplifyExprVisitor();
        BinOp expr = new BinOp("+",new Number() , new Number());
        expr.accept(simplifyExprVisitor);
    }

    static class BinOp extends Expr{
        String opname;
        Expr left;
        Expr right;

        public BinOp(String opname, Expr left, Expr right) {
            this.opname = opname;
            this.left = left;
            this.right = right;
        }

        public Expr accept(SimplifyExprVisitor simplifyExprVisitor)
        {
            return simplifyExprVisitor.visit(this);
        }
    }
    static class Number extends Expr{
        int val;
    }

    static class Expr{

    }

    static class SimplifyExprVisitor{
        public Expr visit(BinOp e){
            if("+".equals(e.opname) &&  e.right instanceof Number){
                return e.left;
            }
            return e;
        }
    }
}
