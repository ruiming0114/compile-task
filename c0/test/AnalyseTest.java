import analyser.Analyser;
import analyser.function.Function;
import analyser.program.Program;
import analyser.statement.Stmt;
import analyser.symbol.SymbolTable;
import error.AnalyseError;
import error.TokenizeError;
import instruction.Instruction;
import org.junit.Test;
import tokenizer.TokenIterator;
import tokenizer.Tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class AnalyseTest {

    @Test
    public void AnalyseExprTest() throws FileNotFoundException, TokenizeError, AnalyseError {
        Scanner scanner = new Scanner(new File("./c0/test/input.txt"));
        TokenIterator iterator = new TokenIterator(scanner);
        Tokenizer tokenizer = new Tokenizer(iterator);
        Analyser analyser = new Analyser(tokenizer);
        Program program = analyser.AnalyseProgram();
        program.generate();
        System.out.println(program.globalInstructions);
        System.out.println(((Function) (program.list.get(1))).instructions);
        System.out.println(((Function) (program.list.get(1))).symbolTable);
    }

    @Test
    public void OtherTest(){
        System.out.println(new BigInteger(String.valueOf(65536)).toString(16));
    }
}
