package analyser.expr;

import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;
import tokenizer.TokenType;

import java.util.ArrayList;

public class OperatorExpr extends Expr {
    public Expr expr1;
    public TokenType operator;
    public Expr expr2;

    public OperatorExpr(Expr expr2,TokenType operator,Expr expr1){
        super();
        super.exprType = ExprType.Operator_Expr;
        this.expr1 = expr1;
        this.operator = operator;
        this.expr2 = expr2;
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable) throws AnalyseError {
        expr1.generate(instructions,symbolTable);
        expr2.generate(instructions,symbolTable);
        if (!expr1.valueType.equals(expr2.valueType)){
            System.out.println(expr1.valueType);
            System.out.println(expr2.valueType);
            throw new AnalyseError();
        }
        this.valueType = expr1.valueType;
        switch (operator){
            case Plus:
                instructions.add(new Instruction(Operation.addi));
                break;
            case Minus:
                instructions.add(new Instruction(Operation.subi));
                break;
            case Mul:
                instructions.add(new Instruction(Operation.muli));
                break;
            case Div:
                instructions.add(new Instruction(Operation.divi));
                break;
            case Gt:
                instructions.add(new Instruction(Operation.cmpi));
                instructions.add(new Instruction(Operation.setgt));
                this.valueType = ValueType.Bool;
                break;
            case Lt:
                instructions.add(new Instruction(Operation.cmpi));
                instructions.add(new Instruction(Operation.setlt));
                this.valueType = ValueType.Bool;
                break;
            case Ge:
                instructions.add(new Instruction(Operation.cmpi));
                instructions.add(new Instruction(Operation.setlt));
                instructions.add(new Instruction(Operation.not));
                this.valueType = ValueType.Bool;
                break;
            case Le:
                instructions.add(new Instruction(Operation.cmpi));
                instructions.add(new Instruction(Operation.setgt));
                instructions.add(new Instruction(Operation.not));
                this.valueType = ValueType.Bool;
                break;
            case Eq:
                instructions.add(new Instruction(Operation.cmpi));
                instructions.add(new Instruction(Operation.not));
                this.valueType = ValueType.Bool;
                break;
            case Neq:
                instructions.add(new Instruction(Operation.cmpi));
                this.valueType = ValueType.Bool;
                break;
        }

    }

    @Override
    public String toString() {
        return super.toString()  +"expr1: "+ expr1.toString() + '\n' + "operator: " + operator + '\n' +"expr2: "+ expr2.toString();
    }
}
