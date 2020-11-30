package analyser.statement;

import analyser.expr.Expr;
import error.AnalyseError;

public class DeclStmt extends Stmt {

    public String kind;    //let or const
    public Object ident;
    public String type;
    public Expr expr;


    public DeclStmt(String kind,Object ident,String type) throws AnalyseError {
        super(StmtType.Decl_Stmt);
        if (kind.equals("const")){
            throw new AnalyseError();
        }
        this.kind = kind;
        this.ident = ident;
        this.type = type;
    }

    public DeclStmt(String kind,Object ident,String type,Expr expr){
        super(StmtType.Decl_Stmt);
        this.kind = kind;
        this.ident = ident;
        this.type = type;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return super.toString() + "kind: "+kind +"\n" + "ident: "+ ident +"\n" + "type: " +type + "\n" + "expr: " +expr ;
    }
}
