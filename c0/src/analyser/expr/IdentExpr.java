package analyser.expr;

import analyser.symbol.SymbolEntry;
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
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable,int level) throws AnalyseError {
        SymbolEntry symbolEntry = symbolTable.getSymbol((String)ident);
        if (symbolEntry.getLevel() == 0){
            instructions.add(new Instruction(Operation.globa,symbolEntry.getStackOffset()));
        }
        else {
            instructions.add(new Instruction(Operation.loca,symbolEntry.getStackOffset()));
        }
        instructions.add(new Instruction(Operation.load64));
        this.valueType = symbolEntry.getValueType();
    }

    @Override
    public String toString() {
        return super.toString() + "ident: " + ident;
    }
}
