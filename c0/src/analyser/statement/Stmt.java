package analyser.statement;

public class Stmt {
    public StmtType stmtType;

    public Stmt(StmtType stmtType){
        this.stmtType = stmtType;
    }

    @Override
    public String toString() {
        return stmtType.toString() + "\n";
    }
}
