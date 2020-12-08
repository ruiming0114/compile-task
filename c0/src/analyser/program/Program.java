package analyser.program;

import analyser.function.Function;
import analyser.statement.DeclStmt;
import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;

import java.util.ArrayList;

public class Program {
    public ArrayList<Object> list;

    public ArrayList<Instruction> globalInstructions;
    public SymbolTable globalSymbolTable;

    public Program(ArrayList<Object> list) throws AnalyseError {
        for (Object object : list){
            if (!(object instanceof DeclStmt || object instanceof Function)){
                throw new AnalyseError();
            }
        }
        this.list = list;
        this.globalInstructions = new ArrayList<>();
        this.globalSymbolTable = new SymbolTable();
    }

    public void generate() throws AnalyseError {
        for (Object obj:list){
            if (obj instanceof DeclStmt){
                ((DeclStmt) obj).generate(globalInstructions,globalSymbolTable,0);
            }
            else {
                ((Function) obj).generate(globalSymbolTable);
            }
        }
    }

    @Override
    public String toString() {
        return "Program\n" + "list: " + list;
    }
}
