package analyser.expr;

public class AssignExpr extends Expr {
    public Object ident;
    public Expr expr;

    public AssignExpr(Object ident,Expr expr){
        super();
        super.exprType = ExprType.Assign_Expr;
        this.ident = ident;
        this.expr = expr;
    }


    @Override
    public String toString() {
        return super.toString() + "ident: " + ident + "\n" +"expr: " + expr ;
    }
}
