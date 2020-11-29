package analyser.expr;

import error.AnalyseError;

public class AsExpr extends Expr {
    public Expr expr;
    public String type;

    public AsExpr(Expr expr,String value) throws AnalyseError {
        super();
        super.exprType = ExprType.As_Expr;
        this.expr = expr;
        switch (value) {
            case "int":
            case "void":
            case "double":
                this.type = value;
                break;
            default:
                throw new AnalyseError();
        }
    }
}
