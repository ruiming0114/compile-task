package analyser.function;

public class FunctionParam {
    public boolean cons;
    public Object ident;
    public String type;

    public FunctionParam(boolean cons,Object ident,String type){
        this.cons = cons;
        this.ident = ident;
        this.type = type;
    }

    @Override
    public String toString() {
        return "FunctionParam\n" + "isConst: " +cons + "\n" + "ident: " + ident +"\n" + "type: " + type ;
    }
}
