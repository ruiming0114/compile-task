package analyser.program;

import analyser.function.Function;
import analyser.statement.DeclStmt;
import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;

import java.util.ArrayList;

public class Program {
    public ArrayList<Object> list;

    public Program(ArrayList<Object> list) throws AnalyseError {
        for (Object object : list){
            if (!(object instanceof DeclStmt || object instanceof Function)){
                throw new AnalyseError();
            }
        }
        this.list = list;
    }

    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable){
    }

    @Override
    public String toString() {
        return "Program\n" + "list: " + list;
    }
}
