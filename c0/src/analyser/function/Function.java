package analyser.function;

import analyser.expr.ValueType;
import analyser.statement.BlockStmt;
import analyser.symbol.SymbolTable;
import analyser.symbol.SymbolType;
import error.AnalyseError;
import instruction.Instruction;

import java.util.ArrayList;

public class Function {
    public Object name;
    public ArrayList<FunctionParam> paramList;
    public ValueType returnType;
    public BlockStmt body;

    public ArrayList<Instruction> instructions;
    public SymbolTable symbolTable;

    public Function(Object name,ArrayList<FunctionParam> paramList,String returnType,BlockStmt blockStmt) throws AnalyseError {
        this.name = name;
        this.paramList = paramList;
        this.body = blockStmt;
        this.instructions = new ArrayList<>();
        this.symbolTable = new SymbolTable();
        switch (returnType){
            case "int":
                this.returnType = ValueType.Int;
                break;
            case "double":
                this.returnType = ValueType.Double;
                break;
            case "void":
                this.returnType = ValueType.Void;
                break;
            default:
                throw new AnalyseError();
        }
    }

    public void generate(SymbolTable globalSymbolTable) throws AnalyseError {
        globalSymbolTable.addSymbol((String) name,false,false, SymbolType.Func,returnType,0);
        symbolTable.addGlobalSymbol(globalSymbolTable);
        for (FunctionParam param :paramList){
            symbolTable.addSymbol((String)param.ident,param.cons,true,SymbolType.Params,param.valueType,1);
        }
        body.generate(instructions,symbolTable,0);
    }

    @Override
    public String toString() {
        return "Function\n" + "name: " + name + "\n" + "paramList: " +paramList + "\n" + "returnType: " +returnType + "\n" +"body: " + body;
    }
}
