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

    public void addFunc(String name, boolean isConstant, boolean isInitialized, ValueType valuetype,int FuncNo,int level) throws AnalyseError {
        if (isContain(name,level)){
            throw new AnalyseError();
        }
        else {
            table.add(new SymbolEntry(name,isConstant,isInitialized,SymbolType.Func,valuetype,FuncNo,level,FuncNo));
        }
    }

    public void addParam(String name, boolean isConstant, boolean isInitialized, ValueType valuetype,int paramsOffset,int level,int FuncNo) throws AnalyseError {
        if (isContain(name,level)){
            throw new AnalyseError();
        }
        else {
            table.add(new SymbolEntry(name,isConstant,isInitialized,SymbolType.Params,valuetype,paramsOffset,level,FuncNo));
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

    public SymbolEntry getFunc(String name){
        SymbolEntry res = null;
        for (SymbolEntry symbolEntry:table){
            if (symbolEntry.getName().equals(name)  && symbolEntry.getSymbolType() == SymbolType.Func){
                res = symbolEntry;
            }
        }
        return res;
    }

    public SymbolEntry getFunc(int FuncNo){
        SymbolEntry res = null;
        for (SymbolEntry symbolEntry:table){
            if (symbolEntry.getFuncNo() == FuncNo  && symbolEntry.getSymbolType() == SymbolType.Func){
                res = symbolEntry;
            }
        }
        return res;
    }

    public ArrayList<SymbolEntry> getParams(String name){
        if (getFunc(name)==null){
            return null;
        }
        int funcNo = getFunc(name).getFuncNo();
        ArrayList<SymbolEntry> res = new ArrayList<>();
        for (SymbolEntry symbolEntry:table){
            if (symbolEntry.getSymbolType() == SymbolType.Params && symbolEntry.getFuncNo() == funcNo){
                res.add(symbolEntry);
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
            if (symbolEntry.getLevel()==0 || (symbolEntry.getLevel()==1 && symbolEntry.getSymbolType() == SymbolType.Params)){
                this.table.add(symbolEntry);
            }
        }
    }

    public int getLocaCount(){
        int res = 0;
        for (SymbolEntry symbolEntry: table){
            if (symbolEntry.getSymbolType() == SymbolType.Var && symbolEntry.getLevel() == 1){
                res++;
            }
        }
        return res;
    }

    public ArrayList<SymbolEntry> getGlobalSymbol(){
        ArrayList<SymbolEntry> res = new ArrayList<>();
        for (SymbolEntry symbolEntry: table){
            if (symbolEntry.getLevel() == 0 && symbolEntry.getSymbolType() == SymbolType.Var || symbolEntry.getSymbolType() == SymbolType.Func){
                res.add(symbolEntry);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return table.toString();
    }
}
