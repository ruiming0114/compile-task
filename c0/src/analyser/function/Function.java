package analyser.function;

import analyser.expr.ValueType;
import analyser.statement.BlockStmt;
import analyser.symbol.SymbolEntry;
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
    int paramsOffset;

    public Function(Object name,ArrayList<FunctionParam> paramList,String returnType,BlockStmt blockStmt) throws AnalyseError {
        this.name = name;
        this.paramList = paramList;
        this.body = blockStmt;
        this.instructions = new ArrayList<>();
        this.symbolTable = new SymbolTable();
        this.paramsOffset = 1;
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

    public void generate(SymbolTable globalSymbolTable,int funcNo) throws AnalyseError {
        globalSymbolTable.addFunc((String) name,false,false,returnType,funcNo,0);
        for (FunctionParam param :paramList){
            symbolTable.addParam((String)param.ident,param.cons,true,param.valueType,paramsOffset,1,funcNo);
            paramsOffset++;
        }
        symbolTable.addGlobalSymbol(globalSymbolTable);
        body.generate(instructions,symbolTable,0,funcNo);
        for (SymbolEntry symbolEntry:symbolTable.table){
            if (symbolEntry.getSymbolType() == SymbolType.Params){
                symbolEntry.setName("");
                symbolEntry.setLevel(0);
                globalSymbolTable.table.add(symbolEntry);
            }
        }
    }

    @Override
    public String toString() {
        return "Function\n" + "name: " + name + "\n" + "paramList: " +paramList + "\n" + "returnType: " +returnType + "\n" +"body: " + body;
    }
}
