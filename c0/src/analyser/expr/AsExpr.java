package analyser.expr;

import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

public class AsExpr extends Expr {
    public Expr expr;
    public ValueType type;

    public AsExpr(Expr value,Expr expr) throws AnalyseError {
        super();
        super.exprType = ExprType.As_Expr;
        this.expr = expr;
        if (!(value instanceof IdentExpr)){
            throw new AnalyseError();
        }
        String str = (String) ((IdentExpr) value).ident;
        switch (str) {
            case "int":
                this.type = ValueType.Int;
                break;
            case "double":
                this.type = ValueType.Double;
                break;
            default:
                throw new AnalyseError();
        }
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable, int level) throws AnalyseError {
        expr.generate(instructions,symbolTable,level);
        this.valueType = type;
        if (valueType!=ValueType.Int && valueType!=ValueType.Double){
            throw new AnalyseError();
        }
        if (type == ValueType.Int){
            instructions.add(new Instruction(Operation.ftoi));
        }
        else {
            instructions.add(new Instruction(Operation.itof));
        }
    }

    @Override
    public String toString() {
        return super.toString() + "expr:" + expr +"\ntype:" + type;
    }
}
