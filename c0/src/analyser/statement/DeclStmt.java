package analyser.statement;

import analyser.expr.Expr;
import error.AnalyseError;

public class DeclStmt extends Stmt {

    public boolean cons;
    public Object ident;
    public String type;
    public Expr expr;


    public DeclStmt(boolean cons,Object ident,String type) throws AnalyseError {
        super(StmtType.Decl_Stmt);
        if (cons){
            throw new AnalyseError();
        }
        this.cons = false;
        this.ident = ident;
        this.type = type;
    }

    public DeclStmt(boolean cons,Object ident,String type,Expr expr){
        super(StmtType.Decl_Stmt);
        this.cons = cons;
        this.ident = ident;
        this.type = type;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return super.toString() + "isConst: "+cons +"\n" + "ident: "+ ident +"\n" + "type: " +type + "\n" + "expr: " +expr ;
    }
}
