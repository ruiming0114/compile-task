package analyser.expr;

import analyser.symbol.SymbolEntry;
import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

public class CallExpr extends Expr {
    public Object ident;
    public ArrayList<Expr> params;

    public CallExpr(Object ident,ArrayList<Expr> params){
        super();
        super.exprType = ExprType.Call_Expr;
        this.ident = ident;
        this.params = params;
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable, int level) throws AnalyseError {
        String name = (String)ident;
        switch (name){
            case "getint":
                instructions.add(new Instruction(Operation.stackalloc,1));
                if (params.size()!=0) {
                    throw new AnalyseError();
                }
                instructions.add(new Instruction(Operation.scani));
                this.valueType = ValueType.Int;
                break;
            case "getdouble":
                instructions.add(new Instruction(Operation.stackalloc,1));
                if (params.size()!=0) {
                    throw new AnalyseError();
                }
                instructions.add(new Instruction(Operation.scanf));
                this.valueType = ValueType.Double;
                break;
            case "getchar":
                instructions.add(new Instruction(Operation.stackalloc,1));
                if (params.size()!=0) {
                    throw new AnalyseError();
                }
                instructions.add(new Instruction(Operation.scanc));
                this.valueType = ValueType.Char;
                break;
            case "putint":
                instructions.add(new Instruction(Operation.stackalloc,0));
                if (params.size()!=1) {
                    throw new AnalyseError();
                }
                params.get(0).generate(instructions,symbolTable,level);
                if (params.get(0).valueType!=ValueType.Int){
                    throw new AnalyseError();
                }
                instructions.add(new Instruction(Operation.printi));
                this.valueType = ValueType.Void;
                break;
            case "putdouble":
                instructions.add(new Instruction(Operation.stackalloc,0));
                if (params.size()!=1) {
                    throw new AnalyseError();
                }
                params.get(0).generate(instructions,symbolTable,level);
                if (params.get(0).valueType!=ValueType.Double){
                    throw new AnalyseError();
                }
                instructions.add(new Instruction(Operation.printf));
                this.valueType = ValueType.Void;
                break;
            case "putchar":
                instructions.add(new Instruction(Operation.stackalloc,0));
                if (params.size()!=1) {
                    throw new AnalyseError();
                }
                params.get(0).generate(instructions,symbolTable,level);
                if (params.get(0).valueType!=ValueType.Int){
                    throw new AnalyseError();
                }
                instructions.add(new Instruction(Operation.printc));
                this.valueType = ValueType.Void;
                break;
            case "putln":
                instructions.add(new Instruction(Operation.stackalloc,0));
                if (params.size()!=0) {
                    throw new AnalyseError();
                }
                instructions.add(new Instruction(Operation.println));
                this.valueType = ValueType.Void;
                break;
            default:
                if (!symbolTable.isContain((String) ident,0)){
                    throw new AnalyseError();
                }
                SymbolEntry Func = symbolTable.getFunc((String) ident);
                int return_slot = 0;
                if (Func.getValueType() != ValueType.Void){
                    return_slot = 1;
                }
                instructions.add(new Instruction(Operation.stackalloc,return_slot));
                ArrayList<SymbolEntry> defineParams = symbolTable.getParams((String) ident);
                if (defineParams.size()!=params.size()){
                    throw new AnalyseError();
                }
                for (int i=0;i<defineParams.size();i++){
                    params.get(i).generate(instructions,symbolTable,level);
                    if (defineParams.get(i).getValueType() != params.get(i).valueType){
                        throw new AnalyseError();
                    }
                }
                instructions.add(new Instruction(Operation.call,Func.getFuncNo()));
                this.valueType = Func.getValueType();
        }
    }

    @Override
    public String toString() {
        return super.toString() + "ident: " + ident +"\n" + "params: " + params;
    }
}
