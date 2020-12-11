import analyser.Analyser;
import analyser.expr.ValueType;
import analyser.function.Function;
import analyser.program.Program;
import analyser.symbol.SymbolEntry;
import analyser.symbol.SymbolType;
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

        FileOutputStream out = new FileOutputStream(new File(output));
        init(out);
        global(program,out);
        func(program,out);
        out.close();
    }

    public static void init(FileOutputStream out) throws IOException {
        out.write(new byte[]{0x72,0x30,0x3b,0x3e,0x00,0x00,0x00,0x01});
    }

    public static void global(Program program,FileOutputStream out) throws IOException {
        ArrayList<SymbolEntry> list = program.globalSymbolTable.getGlobalSymbol();
        out.write(NumberUtil.int32(list.size()+1));
        for (SymbolEntry symbolEntry:list){
            out.write(0x00);
            if (symbolEntry.getSymbolType() == SymbolType.Func){
                String name = symbolEntry.getName();
                out.write(NumberUtil.int32(name.length()));
                for (char c :name.toCharArray()){
                    out.write((byte)c);
                }
            }
            else {
                if (symbolEntry.isConstant()){
                    out.write(0x00);
                }
                else {
                    out.write(0x01);
                }
                if (symbolEntry.getValueType()!= ValueType.String){
                    out.write(new byte[]{0x00,0x00,0x00,0x08});
                }
                out.write(new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00});
            }
        }
        out.write(0x01);
        out.write(new byte[]{0x00,0x00,0x00,0x06});
        out.write(new byte[]{0x5f,0x73,0x74,0x61,0x72,0x74});
    }

    public static void func(Program program,FileOutputStream out) throws IOException {
        ArrayList<Function> list = new ArrayList<>();
        for (Object obj : program.list){
            if (obj instanceof Function){
                list.add((Function)obj);
            }
        }
        out.write(NumberUtil.int32(list.size()+1));
        out.write(new byte[]{0x00,0x00,0x00,0x00});
        out.write(new byte[]{0x00,0x00,0x00,0x00});
        out.write(new byte[]{0x00,0x00,0x00,0x00});
        out.write(new byte[]{0x00,0x00,0x00,0x00});
        out.write(NumberUtil.int32(program.globalInstructions.size()));
        for (Instruction instruction : program.globalInstructions){
            out.write(instruction.generate());
        }
        int funcNo = 1;
        for (Function function:list){
            out.write(NumberUtil.int32(funcNo));
            funcNo++;
            out.write(new byte[]{0x00,0x00,0x00,0x01});
            out.write(NumberUtil.int32(function.paramList.size()));
            out.write(NumberUtil.int32(function.symbolTable.getLocaCount()));
            out.write(NumberUtil.int32(function.instructions.size()));
            for (Instruction instruction : function.instructions){
                out.write(instruction.generate());
            }
        }
    }

    public static void main(String[] args) throws TokenizeError, AnalyseError, IOException {
        Coder.generate(args[0],args[1]);
    }
}
