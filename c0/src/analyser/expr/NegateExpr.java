package analyser.expr;

import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

public class NegateExpr extends Expr {
    public Expr expr;

    public NegateExpr(Expr expr){
        super();
        super.exprType = ExprType.Negate_Expr;
        this.expr = expr;
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable,int level) throws AnalyseError {
        expr.generate(instructions,symbolTable,level);
        this.valueType = expr.valueType;
        if (valueType == ValueType.Int){
            instructions.add(new Instruction(Operation.negi));
        }
        else if (valueType == ValueType.Double){
            instructions.add(new Instruction(Operation.negf));
        }

    }

    @Override
    public String toString() {
        return super.toString() + "expr: " + expr;
    }
}
