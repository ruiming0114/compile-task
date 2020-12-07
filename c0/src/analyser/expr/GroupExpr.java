package analyser.expr;

import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;

import java.util.ArrayList;

public class GroupExpr extends Expr {
    public Expr expr;

    public GroupExpr(Expr expr){
        super();
        super.exprType = ExprType.Group_Expr;
        this.expr = expr;
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable) throws AnalyseError {
        expr.generate(instructions,symbolTable);
        this.valueType = expr.valueType;
    }

    @Override
    public String toString() {
        return super.toString() + "expr: " + expr ;
    }
}
