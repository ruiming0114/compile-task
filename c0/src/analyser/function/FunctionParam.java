package analyser.function;

import analyser.expr.ValueType;
import error.AnalyseError;

public class FunctionParam {
    public boolean cons;
    public Object ident;
    public ValueType valueType;

    public FunctionParam(boolean cons,Object ident,String type) throws AnalyseError {
        this.cons = cons;
        this.ident = ident;
        switch (type){
            case "int":
                this.valueType = ValueType.Int;
                break;
            case "double":
                this.valueType = ValueType.Double;
                break;
            case "void":
                this.valueType = ValueType.Void;
                break;
            default:
                throw new AnalyseError();
        }
    }

    @Override
    public String toString() {
        return "FunctionParam\n" + "isConst: " +cons + "\n" + "ident: " + ident +"\n" + "type: " + valueType ;
    }
}
