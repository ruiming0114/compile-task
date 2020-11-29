package analyser.expr;

import tokenizer.TokenType;

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
    public String toString() {
        return super.toString()  +"expr1: "+ expr1.toString() + '\n' + "operator: " + operator + '\n' +"expr2: "+ expr2.toString();
    }
}
