package analyser.statement;

import analyser.expr.Expr;
import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

public class WhileStmt extends Stmt {

    public Expr condition;
    public BlockStmt whileBlock;

    public WhileStmt(Expr condition,BlockStmt whileBlock){
        super(StmtType.While_Stmt);
        this.condition = condition;
        this.whileBlock = whileBlock;
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable, int level,int funcNo) throws AnalyseError {
        whileBlock.setLoop(true);

        ArrayList<Instruction> conditionTemp = new ArrayList<>();
        ArrayList<Instruction> whileTemp = new ArrayList<>();

        condition.generate(conditionTemp,symbolTable,level);
        whileBlock.generate(whileTemp,symbolTable,level,funcNo);

        instructions.add(new Instruction(Operation.br,0));
        instructions.addAll(conditionTemp);
        instructions.add(new Instruction(Operation.brtrue,1));
        instructions.add(new Instruction(Operation.br,whileTemp.size()+1));
        int count = 0;
        for (Instruction instruction :whileTemp){
            if (instruction.operation == Operation.break_flag){
                instruction.operation = Operation.br;
                instruction.value = whileTemp.size()-count;
            }
            if (instruction.operation == Operation.continue_flag){
                instruction.operation = Operation.br;
                instruction.value = -(conditionTemp.size()+3+count);
            }
            count++;
        }
        instructions.addAll(whileTemp);
        instructions.add(new Instruction(Operation.br,-(whileTemp.size()+conditionTemp.size()+3)));
    }

    @Override
    public String toString() {
        return super.toString() + "condition: " + condition +"\n" + "whileBlock: " + whileBlock;
    }
}
