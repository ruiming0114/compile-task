import analyser.Analyser;
import analyser.program.Program;
import analyser.statement.DeclStmt;
import analyser.statement.Stmt;
import analyser.symbol.SymbolEntry;
import error.AnalyseError;
import error.TokenizeError;
import org.junit.Test;
import tokenizer.TokenIterator;
import tokenizer.Tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class AnalyseTest {

    @Test
    public void AnalyseExprTest() throws FileNotFoundException, TokenizeError, AnalyseError {
        Scanner scanner = new Scanner(new File("./c0/test/input.txt"));
        TokenIterator iterator = new TokenIterator(scanner);
        Tokenizer tokenizer = new Tokenizer(iterator);
        Analyser analyser = new Analyser(tokenizer);
        Stmt stmt  =  analyser.AnalyseStmt();
        stmt.generate(analyser.instructions,analyser.symbolTable);
       analyser.AnalyseStmt().generate(analyser.instructions,analyser.symbolTable);
        System.out.println(analyser.instructions);
    }

    @Test
    public void OtherTest(){
        System.out.println(new BigInteger(String.valueOf(65536)).toString(16));
    }
}
