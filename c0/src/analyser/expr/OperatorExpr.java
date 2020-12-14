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
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable,int level) throws AnalyseError {
        expr1.generate(instructions,symbolTable,level);
        expr2.generate(instructions,symbolTable,level);
        if (!expr1.valueType.equals(expr2.valueType)){
            System.out.println(expr1.valueType);
            System.out.println(expr2.valueType);
            throw new AnalyseError();
        }
        this.valueType = expr1.valueType;
        switch (operator){
            case Plus:
                if (this.valueType == ValueType.Int){
                    instructions.add(new Instruction(Operation.addi));
                }
                else {
                    instructions.add(new Instruction(Operation.addf));
                }
                break;
            case Minus:
                if (this.valueType == ValueType.Int){
                    instructions.add(new Instruction(Operation.subi));
                }
                else {
                    instructions.add(new Instruction(Operation.subf));
                }
                break;
            case Mul:
                if (this.valueType == ValueType.Int){
                    instructions.add(new Instruction(Operation.muli));
                }
                else {
                    instructions.add(new Instruction(Operation.mulf));
                }
                break;
            case Div:
                if (this.valueType == ValueType.Int){
                    instructions.add(new Instruction(Operation.divi));
                }
                else {
                    instructions.add(new Instruction(Operation.divf));
                }
                break;
            case Gt:
                if (this.valueType == ValueType.Int){
                    instructions.add(new Instruction(Operation.cmpi));
                }
                else {
                    instructions.add(new Instruction(Operation.cmpf));
                }
                instructions.add(new Instruction(Operation.setgt));
                this.valueType = ValueType.Bool;
                break;
            case Lt:
                if (this.valueType == ValueType.Int){
                    instructions.add(new Instruction(Operation.cmpi));
                }
                else {
                    instructions.add(new Instruction(Operation.cmpf));
                }
                instructions.add(new Instruction(Operation.setlt));
                this.valueType = ValueType.Bool;
                break;
            case Ge:
                if (this.valueType == ValueType.Int){
                    instructions.add(new Instruction(Operation.cmpi));
                }
                else {
                    instructions.add(new Instruction(Operation.cmpf));
                }
                instructions.add(new Instruction(Operation.setlt));
                instructions.add(new Instruction(Operation.not));
                this.valueType = ValueType.Bool;
                break;
            case Le:
                if (this.valueType == ValueType.Int){
                    instructions.add(new Instruction(Operation.cmpi));
                }
                else {
                    instructions.add(new Instruction(Operation.cmpf));
                }
                instructions.add(new Instruction(Operation.setgt));
                instructions.add(new Instruction(Operation.not));
                this.valueType = ValueType.Bool;
                break;
            case Eq:
                if (this.valueType == ValueType.Int){
                    instructions.add(new Instruction(Operation.cmpi));
                }
                else {
                    instructions.add(new Instruction(Operation.cmpf));
                }
                instructions.add(new Instruction(Operation.not));
                this.valueType = ValueType.Bool;
                break;
            case Neq:
                if (this.valueType == ValueType.Int){
                    instructions.add(new Instruction(Operation.cmpi));
                }
                else {
                    instructions.add(new Instruction(Operation.cmpf));
                }
                this.valueType = ValueType.Bool;
                break;
        }

    }

    @Override
    public String toString() {
        return super.toString()  +"expr1: "+ expr1.toString() + '\n' + "operator: " + operator + '\n' +"expr2: "+ expr2.toString();
    }
}
