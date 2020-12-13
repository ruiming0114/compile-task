package tokenizer;

import error.TokenizeError;

public class Tokenizer {

    private TokenIterator iterator;

    public Tokenizer (TokenIterator iterator){
        this.iterator = iterator;
    }

    public Token nextToken() throws TokenizeError {
        skipSpaceCharacters();
        if (iterator.isEOF()){
            return new Token(TokenType.EOF,"");
        }
        char peek = iterator.peekChar();
        if (Character.isDigit(peek)){
            return lexUIntOrDouble();
        }
        else if (peek == '_' || Character.isAlphabetic(peek)){
            return lexIdentOrKeyword();
        }
        else {
            return lexOthers();
        }
    }

    private Token lexOthers() throws TokenizeError {
        switch (iterator.nextChar()){
            case '+':
                return new Token(TokenType.Plus,"+");
            case '-':
                if (iterator.peekChar() == '>'){
                    iterator.nextChar();
                    return new Token(TokenType.Arrow,"->");
                }
                else {
                    return new Token(TokenType.Minus,"-");
                }
            case '*':
                return new Token(TokenType.Mul,"*");
            case '/':
                if (iterator.peekChar() == '/'){
                    iterator.nextChar();
                    while (iterator.peekChar() != '\n' && !iterator.isEOF()){
                        iterator.nextChar();
                    }
                    return nextToken();
                }
                else {
                    return new Token(TokenType.Div,"/");
                }
            case '=':
                if (iterator.peekChar() == '='){
                    iterator.nextChar();
                    return new Token(TokenType.Eq,"==");
                }
                else {
                    return new Token(TokenType.Assign,"=");
                }
            case '!':
                if (iterator.peekChar() == '='){
                    iterator.nextChar();
                    return new Token(TokenType.Neq,"!=");
                }
                else {
                    throw new TokenizeError();
                }
            case '<':
                if (iterator.peekChar() == '='){
                    iterator.nextChar();
                    return new Token(TokenType.Le,"<=");
                }
                else {
                    return new Token(TokenType.Lt,"<");
                }
            case '>':
                if (iterator.peekChar() == '='){
                    iterator.nextChar();
                    return new Token(TokenType.Ge,">=");
                }
                else {
                    return new Token(TokenType.Gt,">");
                }
            case '(':
                return new Token(TokenType.Lparen,"(");
            case ')':
                return new Token(TokenType.Rparen,")");
            case '{':
                return new Token(TokenType.Lbrace,"{");
            case '}':
                return new Token(TokenType.Rbrace,"}");
            case ',':
                return new Token(TokenType.Comma,",");
            case ':':
                return new Token(TokenType.Colon,":");
            case ';':
                return new Token(TokenType.Semicolon,";");
            case '\"':
                StringBuilder sb = new StringBuilder();
                while (!iterator.isEOF() && iterator.peekChar()!='\"'){
                    if (iterator.peekChar() == '\\'){
                        iterator.nextChar();
                        char peek = iterator.peekChar();
                        if (peek == '\''){
                            sb.append('\'');
                        }
                        else if (peek == '\"'){
                            sb.append('\"');
                        }
                        else if (peek == '\\'){
                            sb.append('\\');
                        }
                        else if (peek == 'r'){
                            sb.append('\r');
                        }
                        else if (peek == 'n'){
                            sb.append('\n');
                        }
                        else if (peek == 't'){
                            sb.append('\t');
                        }
                        else {
                            throw new TokenizeError();
                        }
                        iterator.nextChar();
                    }
                    else {
                        sb.append(iterator.nextChar());
                    }
                }
                if (iterator.nextChar() == '\"'){
                    return new Token(TokenType.String,sb.toString());
                }
                else {
                    throw new TokenizeError();
                }
            case '\'':
                char res;
                if (iterator.peekChar() == '\\'){
                    iterator.nextChar();
                    char peek = iterator.peekChar();
                    if (peek == '\''){
                        res = '\'';
                    }
                    else if (peek == '\"'){
                        res = '\"';
                    }
                    else if (peek == '\\'){
                        res = '\\';
                    }
                    else if (peek == 'n'){
                        res = '\n';
                    }
                    else if (peek == 't'){
                        res = '\t';
                    }
                    else if (peek == 'r'){
                        res = '\r';
                    }
                    else {
                        throw new TokenizeError();
                    }
                    iterator.nextChar();
                }
                else if (!iterator.isEOF()){
                    res = iterator.nextChar();
                }
                else {
                    throw new TokenizeError();
                }
                if(iterator.nextChar() == '\''){
                    return new Token(TokenType.Char, (int) res);
                }
                else {
                    throw new TokenizeError();
                }
            default:
                throw new TokenizeError();
        }
    }

    private Token lexIdentOrKeyword() {
        StringBuilder sb = new StringBuilder();
        sb.append(iterator.nextChar());
        while (!iterator.isEOF() && (iterator.peekChar() == '_' || Character.isAlphabetic(iterator.peekChar()) || Character.isDigit(iterator.peekChar()))){
            sb.append(iterator.nextChar());
        }
        String res = sb.toString();
        switch (res){
            case "fn":
                return new Token(TokenType.Fn,res);
            case "let":
                return new Token(TokenType.Let,res);
            case "const":
                return new Token(TokenType.Const,res);
            case "as":
                return new Token(TokenType.As,res);
            case "while":
                return new Token(TokenType.While,res);
            case "if":
                return new Token(TokenType.If,res);
            case "else":
                return new Token(TokenType.Else,res);
            case "return":
                return new Token(TokenType.Return,res);
            case "break":
                return new Token(TokenType.Break,res);
            case "continue":
                return new Token(TokenType.Continue,res);
            default:
                return new Token(TokenType.Ident,res);
        }
    }

    private Token lexUIntOrDouble() throws TokenizeError {
        StringBuilder sb1 = new StringBuilder();
        while (!iterator.isEOF() && Character.isDigit(iterator.peekChar())){
            sb1.append(iterator.nextChar());
        }
        if (iterator.peekChar()=='.') {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(sb1);
            sb2.append(iterator.nextChar());
            if (!iterator.isEOF() && Character.isDigit(iterator.peekChar())){
                while (!iterator.isEOF() && Character.isDigit(iterator.peekChar())) {
                    sb2.append(iterator.nextChar());
                }
                if (iterator.peekChar() == 'e' || iterator.peekChar() == 'E') {
                    sb2.append(iterator.nextChar());
                    int length = 1;
                    if (iterator.peekChar() == '+' || iterator.peekChar() == '-'){
                        sb2.append(iterator.nextChar());
                        length++;
                    }
                    if (!iterator.isEOF() && Character.isDigit(iterator.peekChar())) {
                        while (!iterator.isEOF() && Character.isDigit(iterator.peekChar())) {
                            sb2.append(iterator.nextChar());
                        }
                    }
                    else {
                        iterator.unread();
                        sb2.deleteCharAt(sb2.length()-1);
                        if (length == 2){
                            iterator.unread();
                            sb2.deleteCharAt(sb2.length()-1);
                        }
                    }
                }
                double res = Double.parseDouble(sb2.toString());
                return new Token(TokenType.Double,res);
            }
            else {
                iterator.unread();
            }
        }
        try {
            int res = Integer.parseInt(sb1.toString());
            return new Token(TokenType.Uint,res);
        }catch (Exception e){
            throw new TokenizeError();
        }
    }

    private void skipSpaceCharacters(){
        while (!iterator.isEOF() && Character.isWhitespace(iterator.peekChar())) {
            iterator.nextChar();
        }
    }
}
