package analyser.statement;

import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;

import java.util.ArrayList;

public class Stmt {
    public StmtType stmtType;
    public boolean isLoop;

    public Stmt(StmtType stmtType){
        this.stmtType = stmtType;
        this.isLoop =false;
    }

    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable,int level,int funcNo) throws AnalyseError {
    }

    @Override
    public String toString() {
        return stmtType.toString() + "\n";
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }
}
