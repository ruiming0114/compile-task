package analyser.statement;

import analyser.expr.Expr;

public class WhileStmt extends Stmt {

    public Expr condition;
    public BlockStmt whileBlock;

    public WhileStmt(Expr condition,BlockStmt whileBlock){
        super(StmtType.While_Stmt);
        this.condition = condition;
        this.whileBlock = whileBlock;
    }

    @Override
    public String toString() {
        return super.toString() + "condition: " + condition +"\n" + "whileBlock: " + whileBlock;
    }
}
