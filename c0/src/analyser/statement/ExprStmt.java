package analyser.statement;

import analyser.expr.Expr;
import analyser.expr.ExprType;
import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

public class ExprStmt extends Stmt{
    public Expr expr;

    public ExprStmt(Expr expr) {
        super(StmtType.Expr_Stmt);
        this.expr = expr;
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable) throws AnalyseError {
        expr.generate(instructions,symbolTable);
        if (expr.exprType == ExprType.Operator_Expr){
            instructions.add(new Instruction(Operation.popn,1));
        }
    }

    @Override
    public String toString() {
        return super.toString() + "expr: " + expr;
    }
}
