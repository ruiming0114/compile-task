package analyser.statement;


import java.util.ArrayList;

public class BlockStmt extends Stmt {
    ArrayList<Stmt> stmtList;

    public BlockStmt(ArrayList<Stmt> stmtList) {
        super(StmtType.Block_Stmt);
        this.stmtList = stmtList;
    }

    @Override
    public String toString() {
        return super.toString() + "stmtList: " + stmtList;
    }
}
