package analyser.expr;

import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;
import tokenizer.TokenType;

import java.util.ArrayList;

public class LiteralExpr extends Expr {
    Object value;

    public LiteralExpr(TokenType tokenType,Object value) throws AnalyseError {
        super();
        super.exprType = ExprType.Literal_Expr;
        this.value = value;
        switch (tokenType){
            case Uint:
                this.valueType = ValueType.Int;
                break;
            case Double:
                this.valueType = ValueType.Double;
                break;
            case String:
                this.valueType = ValueType.String;
                break;
            case Char:
                this.valueType = ValueType.Char;
                break;
            default:
                throw new AnalyseError();
        }
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable,int level) throws AnalyseError {
        switch (valueType){
            case Int:
                instructions.add(new Instruction(Operation.push,value));
        }
    }

    @Override
    public String toString() {
        return super.toString() + "value: " + value ;
    }
}
