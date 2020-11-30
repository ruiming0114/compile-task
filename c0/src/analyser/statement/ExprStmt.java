package analyser.statement;

import analyser.expr.Expr;

public class ExprStmt extends Stmt{
    public Expr expr;

    public ExprStmt(Expr expr) {
        super(StmtType.Expr_Stmt);
        this.expr = expr;
    }

    @Override
    public String toString() {
        return super.toString() + "expr: " + expr;
    }
}
