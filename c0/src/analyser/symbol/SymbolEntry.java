package analyser.symbol;

import analyser.expr.ValueType;

public class SymbolEntry {
    String name;
    boolean isConstant;
    boolean isInitialized;
    SymbolType symbolType;
    ValueType valueType;
    int stackOffset;
    int level;

    public SymbolEntry(String name,boolean isConstant,boolean isInitialized,SymbolType symbolType,ValueType type,int stackOffset,int level){
        this.name = name;
        this.symbolType = symbolType;
        this.level = level;
        this.valueType = type;
        this.isConstant = isConstant;
        this.isInitialized = isInitialized;
        this.stackOffset = stackOffset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConstant() {
        return isConstant;
    }

    public void setConstant(boolean constant) {
        isConstant = constant;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(SymbolType symbolType) {
        this.symbolType = symbolType;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public int getStackOffset() {
        return stackOffset;
    }

    public void setStackOffset(int stackOffset) {
        this.stackOffset = stackOffset;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "name:"+name+",isConstant:" + isConstant + ",isInitialized:" + isConstant  + ",symbolType:"  + symbolType +
                ",valueType:" + valueType+",offset:" + stackOffset + ",level:" + level+ "\n";
    }
}
