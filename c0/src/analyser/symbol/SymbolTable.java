package analyser.symbol;


import analyser.expr.ValueType;
import error.AnalyseError;

import java.util.ArrayList;

public class SymbolTable {
    public ArrayList<SymbolEntry> table;
    int stackOffset;

    public SymbolTable(){
        this.table = new ArrayList<>();
        this.stackOffset = 0;
    }

    private int getNextVariableOffset(){
        return stackOffset++;
    }

    public void addSymbol(String name, boolean isConstant, boolean isInitialized,SymbolType symbolType, ValueType valuetype,int level) throws AnalyseError {
        if (isContain(name,level)){
            throw new AnalyseError();
        }
        else {
            table.add(new SymbolEntry(name,isConstant,isInitialized,symbolType,valuetype,getNextVariableOffset(),level));
        }
    }

    public SymbolEntry getSymbol(String name){
        SymbolEntry res = null;
        int level = -1;
        for (SymbolEntry symbolEntry:table){
            if (symbolEntry.getName().equals(name) && symbolEntry.getLevel()>level){
                res = symbolEntry;
            }
        }
        return res;
    }

    public boolean isContain(String name,int level){
        for (SymbolEntry symbolEntry:table){
            if (symbolEntry.getName().equals(name) && symbolEntry.getLevel()==level){
                return true;
            }
        }
        return false;
    }

    public void addGlobalSymbol(SymbolTable globalSymbolTable){
        for (SymbolEntry symbolEntry: globalSymbolTable.table){
            if (symbolEntry.getLevel()==0){
                this.table.add(symbolEntry);
            }
        }
    }

    @Override
    public String toString() {
        return table.toString();
    }
}
