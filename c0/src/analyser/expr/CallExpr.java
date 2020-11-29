package analyser.expr;

import java.util.ArrayList;

public class CallExpr extends Expr {
    public Object ident;
    public ArrayList<Expr> params;

    public CallExpr(Object ident,ArrayList<Expr> params){
        super();
        super.exprType = ExprType.Call_Expr;
        this.ident = ident;
        this.params = params;
    }

    @Override
    public String toString() {
        return super.toString() + "ident: " + ident +"\n" + "params: " + params;
    }
}
