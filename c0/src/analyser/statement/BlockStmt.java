package analyser.statement;


import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;

import java.util.ArrayList;

public class BlockStmt extends Stmt {
    ArrayList<Stmt> stmtList;

    public BlockStmt(ArrayList<Stmt> stmtList) {
        super(StmtType.Block_Stmt);
        this.stmtList = stmtList;
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable, int level,int funcNo) throws AnalyseError {
        level++;
        for(Stmt stmt:stmtList){
            stmt.isLoop = this.isLoop;
            stmt.generate(instructions,symbolTable,level,funcNo);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "stmtList: " + stmtList;
    }

    public boolean checkReturn(){
        for (Stmt stmt:stmtList){
            if (stmt.stmtType == StmtType.Return_Stmt){
                return true;
            }
        }
        return false;
    }
}
