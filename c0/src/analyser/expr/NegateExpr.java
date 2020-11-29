package analyser.expr;

public class NegateExpr extends Expr {
    public Expr expr;

    public NegateExpr(Expr expr){
        super();
        super.exprType = ExprType.Negate_Expr;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return super.toString() + "expr: " + expr;
    }
}
