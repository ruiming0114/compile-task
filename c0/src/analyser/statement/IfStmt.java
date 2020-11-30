package analyser.statement;

import analyser.expr.Expr;

public class IfStmt extends Stmt {
    public Expr condition;
    public BlockStmt ifBlock;
    public Stmt elseBlock;

    public IfStmt(Expr condition,BlockStmt ifBlock,Stmt elseBlock){
        super(StmtType.If_Stmt);
        this.condition = condition;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    public IfStmt(Expr condition,BlockStmt ifBlock){
        super(StmtType.If_Stmt);
        this.condition = condition;
        this.ifBlock = ifBlock;
    }

    @Override
    public String toString() {
        return super.toString() + "condition: " + condition + '\n' + "ifBlock: " + ifBlock + "\n" + "elseBlock: " + elseBlock;
    }
}
