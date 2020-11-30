package analyser.statement;

import analyser.expr.Expr;

public class ReturnStmt extends Stmt {
    public Expr expr;

    public ReturnStmt() {
        super(StmtType.Return_Stmt);
    }

    public ReturnStmt(Expr expr) {
        super(StmtType.Return_Stmt);
        this.expr = expr;
    }

    @Override
    public String toString() {
        return super.toString() + "expr: " + expr;
    }
}
