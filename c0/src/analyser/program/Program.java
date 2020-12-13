package analyser.program;

import analyser.expr.CallExpr;
import analyser.expr.Expr;
import analyser.function.Function;
import analyser.function.FunctionParam;
import analyser.statement.DeclStmt;
import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

public class Program {
    public ArrayList<Object> list;
    public ArrayList<String> strArray;

    public ArrayList<Instruction> globalInstructions;
    public SymbolTable globalSymbolTable;
    int funcNo;

    public Program(ArrayList<Object> list,ArrayList<String> strArray) throws AnalyseError {
        for (Object object : list){
            if (!(object instanceof DeclStmt || object instanceof Function)){
                throw new AnalyseError();
            }
        }
        this.list = list;
        this.globalInstructions = new ArrayList<>();
        this.globalSymbolTable = new SymbolTable(strArray.size());
        this.funcNo=1;
        this.strArray = strArray;
    }

    public void generate() throws AnalyseError {
        for (Object obj:list){
            if (obj instanceof DeclStmt){
                ((DeclStmt) obj).generate(globalInstructions,globalSymbolTable,0,0);
            }
            else {
                ((Function) obj).generate(globalSymbolTable,funcNo);
                funcNo++;
            }
        }
        CallExpr callMain = new CallExpr("main",new ArrayList<Expr>());
        callMain.generate(globalInstructions,globalSymbolTable,0);
    }

    @Override
    public String toString() {
        return "Program\n" + "list: " + list;
    }
}
