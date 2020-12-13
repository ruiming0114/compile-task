package analyser.statement;

import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

public class BreakStmt extends Stmt {

    public BreakStmt() {
        super(StmtType.Break_Stmt);
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable, int level, int funcNo) throws AnalyseError {
        if (!this.isLoop){
            throw new AnalyseError();
        }
        instructions.add(new Instruction(Operation.break_flag));
    }
}
