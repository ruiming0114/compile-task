package tokenizer;

public enum  TokenType {

    //Keyword
    Fn,
    Let,
    Const,
    As,
    While,
    If,
    Else,
    Return,

    Break,
    Continue,

    //Literal
    Uint,
    String,

    Double,
    Char,

    //Ident
    Ident,

    //Operator
    Plus,
    Minus,
    Mul,
    Div,
    Assign,
    Eq,
    Neq,
    Lt,     //<
    Gt,     //>
    Le,     //<=
    Ge,     //>=
    Lparen,
    Rparen,
    Lbrace,
    Rbrace,
    Arrow,
    Comma,
    Colon,
    Semicolon,

    Comment,

    EOF;

    @Override
    public java.lang.String toString() {
        switch (this){
            case Fn:
                return "Fn";
            case Let:
                return "Let";
            case Const:
                return "Const";
            case As:
                return "As";
            case While:
                return "While";
            case If:
                return "If";
            case Else:
                return "Else";
            case Return:
                return "Return";
            case Break:
                return "Break";
            case Continue:
                return "Continue";
            case Uint:
                return "Uint";
            case String:
                return "String";
            case Double:
                return "Double";
            case Char:
                return "Char";
            case Ident:
                return "Ident";
            case Plus:
                return "Plus";
            case Minus:
                return "Minus";
            case Mul:
                return "Mul";
            case Div:
                return "Div";
            case Assign:
                return "Assign";
            case Eq:
                return "Eq";
            case Neq:
                return "Neq";
            case Lt:
                return "Lt";
            case Gt:
                return "Gt";
            case Le:
                return "Le";
            case Ge:
                return "Ge";
            case Lparen:
                return "Lparen";
            case Rparen:
                return "Rparen";
            case Lbrace:
                return "Lbrace";
            case Rbrace:
                return "Rbrace";
            case Arrow:
                return "Arrow";
            case Comma:
                return "Comma";
            case Colon:
                return "Colon";
            case Semicolon:
                return "Semicolon";
            case Comment:
                return "Comment";
            case EOF:
                return "EOF";
            default:
                return "InvalidToken";
        }
    }
}
