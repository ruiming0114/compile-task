package analyser;

import analyser.expr.*;
import error.AnalyseError;
import error.TokenizeError;
import tokenizer.Token;
import tokenizer.TokenType;
import tokenizer.Tokenizer;

import java.util.*;

public class Analyser {

    Tokenizer tokenizer;

    Token peekedToken = null;

    ArrayList<TokenType> operatorList = new ArrayList<>(Arrays.asList(TokenType.Plus,TokenType.Minus,TokenType.Mul,TokenType.Div,TokenType.Eq,TokenType.Neq,TokenType.Lt,TokenType.Gt,TokenType.Le,TokenType.Ge));

    public static int[][] matrix = {
            {1,1,-1,-1,1,1,1,1,1,1},
            {1,1,-1,-1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {-1,-1,-1,-1,1,1,1,1,1,1},
            {-1,-1,-1,-1,1,1,1,1,1,1},
            {-1,-1,-1,-1,1,1,1,1,1,1},
            {-1,-1,-1,-1,1,1,1,1,1,1},
            {-1,-1,-1,-1,1,1,1,1,1,1},
            {-1,-1,-1,-1,1,1,1,1,1,1},
    };

    public static Map<TokenType,Integer> location = new HashMap<>();

    static {
        location.put(TokenType.Plus,0);
        location.put(TokenType.Minus,1);
        location.put(TokenType.Mul,2);
        location.put(TokenType.Div,3);
        location.put(TokenType.Gt,4);
        location.put(TokenType.Lt,5);
        location.put(TokenType.Ge,6);
        location.put(TokenType.Le,7);
        location.put(TokenType.Eq,8);
        location.put(TokenType.Neq,9);
    }

    public Analyser(Tokenizer tokenizer){
        this.tokenizer = tokenizer;
    }

    private Token peekToken() throws TokenizeError {
        if (peekedToken == null){
            peekedToken = tokenizer.nextToken();
        }
        return peekedToken;
    }

    private Token nextToken() throws TokenizeError {
        if (peekedToken != null){
            Token token = peekedToken;
            peekedToken = null;
            return token;
        }
        return tokenizer.nextToken();
    }

    private void expect(TokenType tokenType) throws TokenizeError, AnalyseError {
        Token token = peekToken();
        if (token.getTokenType() == tokenType){
            nextToken();
            return;
        }
        throw new AnalyseError();
    }

    public Expr AnalyseExpr() throws TokenizeError, AnalyseError {
        Expr expr1 = AnalyseUnaryExpr();
        if (operatorList.contains(peekToken().getTokenType())){
            return AnalyseOPGExpr(expr1);
        }
        else {
            return expr1;
        }
    }

    private Expr AnalyseOPGExpr(Expr expr1) throws TokenizeError, AnalyseError {
        Stack<TokenType> operatorStack = new Stack<>();
        Stack<Expr> exprStack = new Stack<>();
        exprStack.push(expr1);
        Token peek = peekToken();
        while (operatorList.contains(peek.getTokenType())){
            if (operatorStack.isEmpty()){
                operatorStack.push(nextToken().getTokenType());
            }
            else if (matrix[location.get(operatorStack.peek())][location.get(peek.getTokenType())] == 1){
                while (!operatorStack.isEmpty() && matrix[location.get(operatorStack.peek())][location.get(peek.getTokenType())] == 1){
                    OperatorExpr expr = new OperatorExpr(exprStack.pop(),operatorStack.pop(),exprStack.pop());
                    exprStack.push(expr);
                }
                operatorStack.push(nextToken().getTokenType());
            }
            else {
                operatorStack.push(nextToken().getTokenType());
            }
            exprStack.push(AnalyseUnaryExpr());
            peek = peekToken();
        }
        return new OperatorExpr(exprStack.pop(),operatorStack.pop(),exprStack.pop());
    }

    private Expr AnalyseUnaryExpr() throws TokenizeError, AnalyseError {
        Token peek = peekToken();
        if (peek.getTokenType() == TokenType.Minus){
            return AnalyseNegateExpr();
        }
        else if(peek.getTokenType() == TokenType.Lparen){
            return AnalyseGroupExpr();
        }
        else if(peek.getTokenType() == TokenType.Uint || peek.getTokenType() == TokenType.Double || peek.getTokenType() == TokenType.String || peek.getTokenType() == TokenType.Char){
            return AnalyseLiteralExpr();
        }
        else if (peek.getTokenType() == TokenType.Ident){
            return AnalyseIdentExpr();
        }
        else {
            throw new AnalyseError();
        }
    }

    private Expr AnalyseNegateExpr() throws TokenizeError, AnalyseError {
        expect(TokenType.Minus);
        return new NegateExpr(AnalyseUnaryExpr());
    }

    private Expr AnalyseGroupExpr() throws TokenizeError, AnalyseError {
        expect(TokenType.Lparen);
        Expr expr = AnalyseExpr();
        expect(TokenType.Rparen);
        return new GroupExpr(expr);
    }

    private Expr AnalyseLiteralExpr() throws TokenizeError {
        Token token = nextToken();
        return new LiteralExpr(token.getTokenType(),token.getValue());
    }

    private Expr AnalyseIdentExpr() throws TokenizeError, AnalyseError {
        Token token = nextToken();
        Token peek = peekToken();
        if (peek.getTokenType() == TokenType.Assign){
            nextToken();
            return new AssignExpr(token.getValue(),AnalyseExpr());
        }
        else if (peek.getTokenType() == TokenType.Lparen){
            expect(TokenType.Lparen);
            if (peekToken().getTokenType() == TokenType.Rparen){
                return new CallExpr(token.getValue(),null);
            }
            else {
                ArrayList<Expr> params = new ArrayList<>();
                params.add(AnalyseExpr());
                while (peekToken().getTokenType() == TokenType.Comma){
                    expect(TokenType.Comma);
                    params.add(AnalyseExpr());
                }
                expect(TokenType.Rparen);
                return new CallExpr(token.getValue(),params);
            }
        }
        else {
            return new IdentExpr(token.getValue());
        }
    }
}
