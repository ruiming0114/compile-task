package tokenizer;

import java.util.Objects;

public class Token {
    private TokenType tokenType;
    private Object value;

    public Token(TokenType tokenType,Object value){
        this.tokenType = tokenType;
        this.value = value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Token token = (Token) obj;
        return tokenType == token.tokenType && Objects.equals(value, token.value);
    }

    @Override
    public String toString() {
        return "Type: " + this.tokenType + ' ' + "Value: " + this.value;
    }
}
