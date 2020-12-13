package analyser.expr;

import analyser.symbol.SymbolEntry;
import analyser.symbol.SymbolTable;
import analyser.symbol.SymbolType;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

public class AssignExpr extends Expr {
    public Object ident;
    public Expr expr;

    public AssignExpr(Object ident,Expr expr){
        super();
        super.exprType = ExprType.Assign_Expr;
        this.ident = ident;
        this.expr = expr;
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable,int level) throws AnalyseError {
        this.valueType = ValueType.Void;
        SymbolEntry symbolEntry = symbolTable.getSymbol((String) ident,level);
        if (symbolEntry.isConstant()){
            throw new AnalyseError();
        }
        if (symbolEntry.getLevel() == 0){
            instructions.add(new Instruction(Operation.globa,symbolEntry.getStackOffset()));
        }
        else if (symbolEntry.getSymbolType() == SymbolType.Params){
            instructions.add(new Instruction(Operation.arga,symbolEntry.getStackOffset()));
        }
        else {
            instructions.add(new Instruction(Operation.loca,symbolEntry.getStackOffset()));
        }
        expr.generate(instructions,symbolTable,level);
        if (expr.valueType != symbolEntry.getValueType()){
            throw new AnalyseError();
        }
        instructions.add(new Instruction(Operation.store64));
        symbolEntry.setInitialized(true);
    }

    @Override
    public String toString() {
        return super.toString() + "ident: " + ident + "\n" +"expr: " + expr ;
    }
}
