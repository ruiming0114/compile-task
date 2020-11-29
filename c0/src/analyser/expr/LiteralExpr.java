package analyser.expr;

import tokenizer.TokenType;

public class LiteralExpr extends Expr {
    TokenType tokenType;
    Object value;

    public LiteralExpr(TokenType tokenType,Object value){
        super();
        super.exprType = ExprType.Literal_Expr;
        this.tokenType = tokenType;
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + "tokenType: " + tokenType +"\n" + "value: " + value ;
    }
}
