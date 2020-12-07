package analyser.symbol;


import analyser.expr.ValueType;
import error.AnalyseError;

import java.util.HashMap;

public class SymbolTable {
    HashMap<String,SymbolEntry> table;
    int stackOffset;

    public SymbolTable(){
        this.table = new HashMap<>();
        this.stackOffset = 0;
    }

    private int getNextVariableOffset() {
        return stackOffset++;
    }

    public void addSymbol(String name, boolean isConstant, boolean isInitialized, ValueType type) throws AnalyseError {
        if (isContain(name)){
            throw new AnalyseError();
        }
        else {
            table.put(name,new SymbolEntry(isConstant,isInitialized,type,getNextVariableOffset()));
        }
    }

    public SymbolEntry getSymbol(String name){
        return table.get(name);
    }

    public boolean isContain(String name){
        return table.containsKey(name);
    }

    @Override
    public String toString() {
        return table.toString();
    }
}
