import error.TokenizeError;
import org.junit.Test;
import tokenizer.Token;
import tokenizer.TokenIterator;
import tokenizer.TokenType;
import tokenizer.Tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TokenizerTest {

    @Test
    public void lexUIntOrDoubleTest() throws FileNotFoundException, TokenizeError {
        Scanner scanner = new Scanner(new File("./test/input.txt"));
        TokenIterator iterator = new TokenIterator(scanner);
        Tokenizer tokenizer = new Tokenizer(iterator);
        Token token = tokenizer.nextToken();
        while(token.getTokenType()!= TokenType.EOF){
            System.out.println(token);
            token = tokenizer.nextToken();
        }
    }
}
