package analyser.statement;

import analyser.expr.Expr;
import analyser.symbol.SymbolTable;
import error.AnalyseError;
import instruction.Instruction;
import instruction.Operation;

import java.util.ArrayList;

public class IfStmt extends Stmt {
    public Expr condition;
    public BlockStmt ifBlock;
    public Stmt elseBlock;

    public IfStmt(Expr condition,BlockStmt ifBlock,Stmt elseBlock){
        super(StmtType.If_Stmt);
        this.condition = condition;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    public IfStmt(Expr condition,BlockStmt ifBlock){
        super(StmtType.If_Stmt);
        this.condition = condition;
        this.ifBlock = ifBlock;
    }

    @Override
    public void generate(ArrayList<Instruction> instructions, SymbolTable symbolTable, int level,int funcNo) throws AnalyseError {
        ifBlock.isLoop = this.isLoop;
        condition.generate(instructions,symbolTable,level);
        ArrayList<Instruction> tempIf = new ArrayList<>();
        ArrayList<Instruction> tempElse = new ArrayList<>();
        ifBlock.generate(tempIf,symbolTable,level,funcNo);
        instructions.add(new Instruction(Operation.brtrue,1));
        instructions.add(new Instruction(Operation.br,tempIf.size()+1));
        instructions.addAll(tempIf);
        if (elseBlock!=null){
            elseBlock.isLoop = this.isLoop;
            elseBlock.generate(tempElse,symbolTable,level,funcNo);
            instructions.add(new Instruction(Operation.br,tempElse.size()+1));
            instructions.addAll(tempElse);
        }
        instructions.add(new Instruction(Operation.br,0));
    }

    @Override
    public String toString() {
        return super.toString() + "condition: " + condition + '\n' + "ifBlock: " + ifBlock + "\n" + "elseBlock: " + elseBlock;
    }
}
