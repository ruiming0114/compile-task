package analyser.expr;

import error.AnalyseError;

public class AsExpr extends Expr {
    public Expr expr;
    public int type;
    // 0 for int,1 for void,2 for double

    public AsExpr(Expr expr,String value) throws AnalyseError {
        super();
        super.exprType = ExprType.As_Expr;
        this.expr = expr;
        switch (value) {
            case "int":
                this.type = 0;
                break;
            case "void":
                this.type = 1;
                break;
            case "double":
                this.type = 2;
                break;
            default:
                throw new AnalyseError();
        }
    }
}
