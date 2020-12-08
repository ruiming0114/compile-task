package analyser.statement;

import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;

import java.util.ArrayList;

public class Stmt {
    public StmtType stmtType;

    public Stmt(StmtType stmtType){
        this.stmtType = stmtType;
    }

    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable,int level) throws AnalyseError {
    }

    @Override
    public String toString() {
        return stmtType.toString() + "\n";
    }
}
