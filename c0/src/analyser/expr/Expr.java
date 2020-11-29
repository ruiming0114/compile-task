package analyser.expr;

public class Expr {
    public ExprType exprType;

    @Override
    public String toString() {
        return exprType.toString() + '\n';
    }
}
