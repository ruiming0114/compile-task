package analyser.symbol;

import analyser.expr.ValueType;

public class SymbolEntry {
    boolean isConstant;
    boolean isInitialized;
    ValueType type;
    int stackOffset;

    public SymbolEntry(boolean isConstant,boolean isInitialized,ValueType type,int stackOffset){
        this.type = type;
        this.isConstant = isConstant;
        this.isInitialized = isInitialized;
        this.stackOffset = stackOffset;
    }

    public ValueType getType() {
        return type;
    }

    public void setType(ValueType type) {
        this.type = type;
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

    public int getStackOffset() {
        return stackOffset;
    }

    public void setStackOffset(int stackOffset) {
        this.stackOffset = stackOffset;
    }

    @Override
    public String toString() {
        return "isConstant: " + isConstant + ", isInitialized: " + isConstant  + ", type: "  + type + ", offset: " + stackOffset + "\n";
    }
}
