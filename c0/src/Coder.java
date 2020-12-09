
import analyser.Analyser;
import analyser.expr.ValueType;
import analyser.function.Function;
import analyser.program.Program;
import analyser.symbol.SymbolEntry;
import analyser.util.NumberUtil;
import error.AnalyseError;
import error.TokenizeError;
import instruction.Instruction;
import tokenizer.TokenIterator;
import tokenizer.Tokenizer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Coder {

    public static void generate(String input,String output) throws IOException, TokenizeError, AnalyseError {
        Scanner scanner = new Scanner(new File(input));
        TokenIterator iterator = new TokenIterator(scanner);
        Tokenizer tokenizer = new Tokenizer(iterator);
        Analyser analyser = new Analyser(tokenizer);
        Program program = analyser.AnalyseProgram();
        program.generate();

        FileWriter writer = new FileWriter(output);
        writer.write("72 30 3b 3e");
        writer.write("\n");
        writer.write("00 00 00 01");
        writer.write("\n");

        writer.write(global(program));
        writer.write("\n");
        writer.write(func(program));
        writer.close();
    }

    public static String global(Program program){
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<SymbolEntry> list = program.globalSymbolTable.getGlobalSymbol();
        stringBuilder.append(NumberUtil.int32(list.size()));
        for (SymbolEntry symbolEntry:list){
            if (symbolEntry.isConstant()){
                stringBuilder.append("00").append("\n");
            }
            else {
                stringBuilder.append("01").append("\n");
            }
            if (symbolEntry.getValueType()!= ValueType.String){
                stringBuilder.append("00 00 00 08").append("\n");
            }
            stringBuilder.append("00 00 00 00 00 00 00 00").append("\n");
        }
        return stringBuilder.toString();
    }

    public static String func(Program program){
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Function> list = new ArrayList<>();
        for (Object obj : program.list){
            if (obj instanceof Function){
                list.add((Function)obj);
            }
        }
        stringBuilder.append(NumberUtil.int32(list.size()+1)).append("\n");
        stringBuilder.append("00 00 00 00\n");
        stringBuilder.append("00 00 00 00\n");
        stringBuilder.append("00 00 00 00\n");
        stringBuilder.append("00 00 00 00\n");
        stringBuilder.append(NumberUtil.int32(program.globalInstructions.size())).append('\n');
        for (Instruction instruction : program.globalInstructions){
            stringBuilder.append(instruction.generate()).append("\n");
        }
        int funcNo = 1;
        for (Function function:list){
            stringBuilder.append(NumberUtil.int32(funcNo)).append("\n");
            funcNo++;
            stringBuilder.append("00 00 00 01\n");
            stringBuilder.append(NumberUtil.int32(function.paramList.size())).append("\n");
            stringBuilder.append(NumberUtil.int32(function.symbolTable.getLocaCount())).append("\n");
            stringBuilder.append(NumberUtil.int32(function.instructions.size())).append("\n");
            for (Instruction instruction : function.instructions){
                stringBuilder.append(instruction.generate()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws TokenizeError, AnalyseError, IOException {
        Coder.generate(args[0],args[1]);
    }
}
