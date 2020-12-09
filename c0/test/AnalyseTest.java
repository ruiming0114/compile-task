import analyser.Analyser;
import analyser.program.Program;
import analyser.util.NumberUtil;
import error.AnalyseError;
import error.TokenizeError;
import org.junit.Test;
import tokenizer.TokenIterator;
import tokenizer.Tokenizer;

import java.io.File;
import java.io.IOException;

import java.math.BigInteger;
import java.util.Scanner;

public class AnalyseTest {

    @Test
    public void AnalyseTest() throws IOException, TokenizeError, AnalyseError {
        Scanner scanner = new Scanner(new File("./c0/test/input.txt"));
        TokenIterator iterator = new TokenIterator(scanner);
        Tokenizer tokenizer = new Tokenizer(iterator);
        Analyser analyser = new Analyser(tokenizer);
        Program program = analyser.AnalyseProgram();
        program.generate();
        System.out.println(Coder.global(program));
    }

    @Test
    public void TotalTest() throws TokenizeError, AnalyseError, IOException {
        Coder.generate("./c0/test/input","./c0/test/output");
    }

    @Test
    public void OtherTest(){}
}
