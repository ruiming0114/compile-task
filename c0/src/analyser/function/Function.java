package analyser.function;

import analyser.statement.BlockStmt;

import java.util.ArrayList;

public class Function {
    public Object name;
    public ArrayList<FunctionParam> paramList;
    public String returnType;
    public BlockStmt body;

    public Function(Object name,ArrayList<FunctionParam> paramList,String returnType,BlockStmt blockStmt){
        this.name = name;
        this.paramList = paramList;
        this.returnType = returnType;
        this.body = blockStmt;
    }

    @Override
    public String toString() {
        return "Function\n" + "name: " + name + "\n" + "paramList: " +paramList + "\n" + "returnType: " +returnType + "\n" +"body: " + body;
    }
}
