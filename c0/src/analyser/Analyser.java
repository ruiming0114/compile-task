package analyser;

import analyser.expr.*;
import analyser.function.Function;
import analyser.function.FunctionParam;
import analyser.program.Program;
import analyser.statement.*;
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

    private Token expect(TokenType tokenType) throws TokenizeError, AnalyseError {
        Token token = peekToken();
        if (token.getTokenType() == tokenType){
            return nextToken();
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
        while (true){
            if (operatorList.contains(peek.getTokenType())){
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
            else {
                while (!operatorStack.isEmpty()){
                    OperatorExpr expr = new OperatorExpr(exprStack.pop(),operatorStack.pop(),exprStack.pop());
                    exprStack.push(expr);
                }
                return exprStack.pop();
            }
        }
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
                expect(TokenType.Rparen);
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

    public Stmt AnalyseStmt() throws TokenizeError, AnalyseError {
        Token peek = peekToken();
        if (peek.getTokenType() == TokenType.Let || peek.getTokenType() == TokenType.Const){
            return AnalyseDeclStmt();
        }
        else if (peek.getTokenType() == TokenType.If){
            return AnalyseIfStmt();
        }
        else if (peek.getTokenType() == TokenType.While){
            return AnalyseWhileStmt();
        }
        else if (peek.getTokenType() == TokenType.Break){
            return AnalyseBreakStmt();
        }
        else if (peekToken().getTokenType() == TokenType.Continue){
            return AnalyseContinueStmt();
        }
        else if (peekToken().getTokenType() == TokenType.Return){
            return AnalyseReturnStmt();
        }
        else if (peekToken().getTokenType() == TokenType.Lbrace){
            return AnalyseBlockStmt();
        }
        else if (peekToken().getTokenType() == TokenType.Semicolon){
            return AnalyseEmptyStmt();
        }
        else {
            return AnalyseExprStmt();
        }
    }

    private Stmt AnalyseExprStmt() throws TokenizeError, AnalyseError {
        Expr expr = AnalyseExpr();
        expect(TokenType.Semicolon);
        return new ExprStmt(expr);
    }

    private Stmt AnalyseDeclStmt() throws TokenizeError, AnalyseError {
        Token peek = peekToken();
        if (peek.getTokenType() == TokenType.Let){
            expect(TokenType.Let);
            Object ident = expect(TokenType.Ident).getValue();
            expect(TokenType.Colon);
            String ty = AnalyseTy();
            if (peekToken().getTokenType() == TokenType.Assign){
                expect(TokenType.Assign);
                Expr expr = AnalyseExpr();
                expect(TokenType.Semicolon);
                return new DeclStmt(false,ident,ty,expr);
            }
            else {
                expect(TokenType.Semicolon);
                return new DeclStmt(false,ident,ty);
            }
        }
        else {
            expect(TokenType.Const);
            Object ident = expect(TokenType.Ident).getValue();
            expect(TokenType.Colon);
            String ty = AnalyseTy();
            expect(TokenType.Assign);
            Expr expr = AnalyseExpr();
            expect(TokenType.Semicolon);
            return new DeclStmt(true,ident,ty,expr);
        }
    }

    private String AnalyseTy() throws TokenizeError, AnalyseError {
        String value = (String) expect(TokenType.Ident).getValue();
        if ("int".equals(value) || "double".equals(value) || "void".equals(value)) {
            return value;
        }
        throw new AnalyseError();
    }

    private Stmt AnalyseBlockStmt() throws TokenizeError, AnalyseError {
        expect(TokenType.Lbrace);
        ArrayList<Stmt> stmts = new ArrayList<>();
        while (peekToken().getTokenType() != TokenType.Rbrace){
            stmts.add(AnalyseStmt());
        }
        expect(TokenType.Rbrace);
        return new BlockStmt(stmts);
    }

    private Stmt AnalyseEmptyStmt() throws TokenizeError, AnalyseError {
        expect(TokenType.Semicolon);
        return new EmptyStmt();
    }

    private Stmt AnalyseReturnStmt() throws TokenizeError, AnalyseError {
        expect(TokenType.Return);
        if (peekToken().getTokenType() == TokenType.Semicolon){
            expect(TokenType.Semicolon);
            return new ReturnStmt();
        }
        else {
            Expr expr = AnalyseExpr();
            expect(TokenType.Semicolon);
            return new ReturnStmt(expr);
        }
    }

    private Stmt AnalyseWhileStmt() throws TokenizeError, AnalyseError {
        expect(TokenType.While);
        Expr expr = AnalyseExpr();
        BlockStmt blockStmt = (BlockStmt) AnalyseBlockStmt();
        return new WhileStmt(expr,blockStmt);
    }

    private Stmt AnalyseIfStmt() throws TokenizeError, AnalyseError {
        expect(TokenType.If);
        Expr condition = AnalyseExpr();
        BlockStmt ifBlock = (BlockStmt) AnalyseBlockStmt();
        if (peekToken().getTokenType() == TokenType.Else){
            expect(TokenType.Else);
            if (peekToken().getTokenType() == TokenType.Lbrace){
                BlockStmt elseBlock = (BlockStmt) AnalyseBlockStmt();
                return new IfStmt(condition,ifBlock,elseBlock);
            }
            else {
                IfStmt ifStmt = (IfStmt) AnalyseIfStmt();
                return new IfStmt(condition,ifBlock,ifStmt);
            }
        }
        else {
            return new IfStmt(condition,ifBlock);
        }
    }

    private Stmt AnalyseBreakStmt() throws TokenizeError, AnalyseError {
        expect(TokenType.Break);
        expect(TokenType.Semicolon);
        return new BreakStmt();
    }

    private Stmt AnalyseContinueStmt() throws TokenizeError, AnalyseError {
        expect(TokenType.Continue);
        expect(TokenType.Semicolon);
        return new ContinueStmt();
    }

    private FunctionParam AnalyseFunctionParam() throws TokenizeError, AnalyseError {
        boolean isConst = false;
        if (peekToken().getTokenType() == TokenType.Const){
            expect(TokenType.Const);
            isConst = true;
        }
        Object ident = expect(TokenType.Ident).getValue();
        expect(TokenType.Colon);
        String ty = AnalyseTy();
        return new FunctionParam(isConst,ident,ty);
    }

    public Function AnalyseFunction() throws TokenizeError, AnalyseError {
        expect(TokenType.Fn);
        Object name = expect(TokenType.Ident).getValue();
        expect(TokenType.Lparen);
        ArrayList<FunctionParam> params = new ArrayList<>();
        if (peekToken().getTokenType() != TokenType.Rparen) {
            params.add(AnalyseFunctionParam());
            while (peekToken().getTokenType() == TokenType.Comma) {
                expect(TokenType.Comma);
                params.add(AnalyseFunctionParam());
            }
        }
        expect(TokenType.Rparen);
        expect(TokenType.Arrow);
        String ty = AnalyseTy();
        BlockStmt stmt = (BlockStmt) AnalyseBlockStmt();
        return new Function(name,params,ty,stmt);
    }

    public Program AnalyseProgram() throws TokenizeError, AnalyseError {
        ArrayList<Object> list = new ArrayList<>();
        while (peekToken().getTokenType() != TokenType.EOF){
            Token peek = peekToken();
            if (peek.getTokenType() == TokenType.Let || peek.getTokenType() == TokenType.Const){
                list.add(AnalyseDeclStmt());
            }
            else if (peek.getTokenType() == TokenType.Fn){
                list.add(AnalyseFunction());
            }
            else {
                throw new AnalyseError();
            }
        }
        return new Program(list);
    }
}
