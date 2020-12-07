package instruction;

public class Instruction {
    public Operation operation;
    public Object value;

    public Instruction(Operation operation){
        this.operation = operation;
    }

    public Instruction(Operation operation,Object value){
        this.operation = operation;
        this.value = value;
    }

    @Override
    public String toString() {
        return "operation: " + operation + ", value: "+ value +"\n";
    }
}
