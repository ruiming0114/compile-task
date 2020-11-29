package analyser.expr;

public class IdentExpr extends Expr {
    public Object ident;

    public IdentExpr(Object ident){
        super();
        super.exprType = ExprType.Ident_Expr;
        this.ident = ident;
    }

    @Override
    public String toString() {
        return super.toString() + "ident: " + ident;
    }
}
