import analyser.Analyser;
import analyser.program.Program;
import error.AnalyseError;
import error.TokenizeError;
import org.junit.Test;
import tokenizer.TokenIterator;
import tokenizer.Tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnalyseTest {

    @Test
    public void AnalyseExprTest() throws FileNotFoundException, TokenizeError, AnalyseError {
        Scanner scanner = new Scanner(new File("./c0/test/input.txt"));
        TokenIterator iterator = new TokenIterator(scanner);
        Tokenizer tokenizer = new Tokenizer(iterator);
        Analyser analyser = new Analyser(tokenizer);
        Program program  = analyser.AnalyseProgram();
        System.out.println(program);
    }
}
