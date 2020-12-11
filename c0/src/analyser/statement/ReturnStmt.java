package analyser.statement;

import analyser.expr.Expr;
import analyser.expr.ValueType;
import analyser.symbol.SymbolEntry;
import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

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
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable,int level,int funcNo) throws AnalyseError {
        SymbolEntry Func = symbolTable.getFunc(funcNo);
        if (Func.getValueType() != ValueType.Void) {
            instructions.add(new Instruction(Operation.arga, 0));
            if (expr != null) {
                expr.generate(instructions, symbolTable, level);
            }
            instructions.add(new Instruction(Operation.store64));
        }
        instructions.add(new Instruction(Operation.ret));
    }

    @Override
    public String toString() {
        return super.toString() + "expr: " + expr;
    }
}
