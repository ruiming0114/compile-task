package analyser.expr;

import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

public class IdentExpr extends Expr {
    public Object ident;

    public IdentExpr(Object ident){
        super();
        super.exprType = ExprType.Ident_Expr;
        this.ident = ident;
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable) throws AnalyseError {
        int offset = symbolTable.getSymbol((String)ident).getStackOffset();
        instructions.add(new Instruction(Operation.loca,offset));
        instructions.add(new Instruction(Operation.load64));
        this.valueType = symbolTable.getSymbol((String)ident).getType();
    }

    @Override
    public String toString() {
        return super.toString() + "ident: " + ident;
    }
}
