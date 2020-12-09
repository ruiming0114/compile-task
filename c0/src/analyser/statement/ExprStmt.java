package analyser.statement;

import analyser.expr.CallExpr;
import analyser.expr.Expr;
import analyser.expr.ExprType;
import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

public class ExprStmt extends Stmt{
    public Expr expr;

    public ExprStmt(Expr expr) {
        super(StmtType.Expr_Stmt);
        this.expr = expr;
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable,int level) throws AnalyseError {
        expr.generate(instructions,symbolTable,level);
        if (expr.exprType == ExprType.Call_Expr){
            String name = (String)(((CallExpr)expr).ident);
            if (name.equals("putint") || name.equals("putdouble") || name.equals("putchar") || name.equals("putstr") || name.equals("putln")){
                return;
            }
        }
        if (expr.exprType != ExprType.Assign_Expr ){
            instructions.add(new Instruction(Operation.popn,1));
        }
    }

    @Override
    public String toString() {
        return super.toString() + "expr: " + expr;
    }
}
