package analyser.expr;

public class GroupExpr extends Expr {
    public Expr expr;

    public GroupExpr(Expr expr){
        super();
        super.exprType = ExprType.Group_Expr;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return super.toString() + "expr: " + expr ;
    }
}
