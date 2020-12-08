package analyser.expr;

import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;

import java.util.ArrayList;

public class Expr {
    public ExprType exprType;
    public ValueType valueType;

    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable,int level) throws AnalyseError {
    }

    @Override
    public String toString() {
        return exprType.toString() +"," + valueType+ '\n';
    }
}
