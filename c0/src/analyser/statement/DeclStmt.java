package analyser.statement;

import analyser.expr.Expr;
import analyser.expr.ValueType;
import analyser.symbol.SymbolTable;
import analyser.symbol.SymbolType;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

public class DeclStmt extends Stmt {

    public boolean cons;
    public Object ident;
    public ValueType type;
    public Expr expr;


    public DeclStmt(boolean cons,Object ident,String type) throws AnalyseError {
        super(StmtType.Decl_Stmt);
        if (cons){
            throw new AnalyseError();
        }
        this.cons = false;
        this.ident = ident;
        switch (type){
            case "int":
                this.type = ValueType.Int;
                break;
            case "double":
                this.type = ValueType.Double;
                break;
            default:
                throw new AnalyseError();
        }
    }

    public DeclStmt(boolean cons,Object ident,String type,Expr expr) throws AnalyseError {
        super(StmtType.Decl_Stmt);
        this.cons = cons;
        this.ident = ident;
        this.expr = expr;
        switch (type){
            case "int":
                this.type = ValueType.Int;
                break;
            case "double":
                this.type = ValueType.Double;
                break;
            default:
                throw new AnalyseError();
        }
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable,int level) throws AnalyseError {
        String name = (String) ident;
        if (symbolTable.isContain(name,level)){
            throw new AnalyseError();
        }
        if(cons){
            symbolTable.addSymbol(name,true,true, SymbolType.Var,type,level);
            instructions.add(new Instruction(Operation.loca,symbolTable.getSymbol(name).getStackOffset()));
            expr.generate(instructions,symbolTable,level);
            if (expr.valueType != type){
                throw new AnalyseError();
            }
            instructions.add(new Instruction(Operation.store64));
        }
        else {
            if (expr == null){
                symbolTable.addSymbol(name,false,false,SymbolType.Var,type,level);
            }
            else {
                symbolTable.addSymbol(name,false,true,SymbolType.Var,type,level);
                instructions.add(new Instruction(Operation.loca,symbolTable.getSymbol(name).getStackOffset()));
                expr.generate(instructions,symbolTable,level);
                if (expr.valueType != type){
                    throw new AnalyseError();
                }
                instructions.add(new Instruction(Operation.store64));
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "isConst: "+cons +"\n" + "ident: "+ ident +"\n" + "type: " +type + "\n" + "expr: " +expr ;
    }
}
