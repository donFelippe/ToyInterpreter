package Model.Statements.Classes;

import Model.Dictionary.MyIDictionary;
import Model.ProgramState;
import Exceptions.MyException;
import Model.Stack.MyIStack;
import Model.Stack.MyStack;
import Model.Statements.StatementInterface;

import java.lang.reflect.Type;

public class ForkStmt implements StatementInterface {
    StatementInterface stmt;
    public ForkStmt(StatementInterface statement) {
        this.stmt = statement;
    }
    @Override
    public String toString() { return "fork(" + stmt + ");"; }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<StatementInterface> stack = new MyStack<>();
        return new ProgramState(stack, state.getSymTable().clone(), state.getOut(), state.getFileTable(), state.getHeap(), stmt);
    }
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        stmt.typecheck(typeEnv.clone());
        return typeEnv;
    }

    @Override
    public StatementInterface deepCopy() {
        return new ForkStmt(stmt.deepCopy());
    }
}
