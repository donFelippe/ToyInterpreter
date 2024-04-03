package Model.Statements.Classes;

import Exceptions.MyException;
import Model.Dictionary.MyIDictionary;
import Model.Statements.StatementInterface;
import Model.Stack.MyIStack;
import Model.ProgramState;

import java.lang.reflect.Type;

public class ComposedStatement implements StatementInterface {
    StatementInterface first;
    StatementInterface second;

    public ComposedStatement(StatementInterface first, StatementInterface snd) {
        this.first = first;
        this.second = snd;
    }
    @Override
    public String toString() {
        return "(" + first.toString() + " " + second.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<StatementInterface> stk = state.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
        MyIDictionary<String,Type> typEnv2 = second.typecheck(typEnv1);
        return second.typecheck(first.typecheck(typeEnv));
    }
    @Override
    public StatementInterface deepCopy() {
        return new ComposedStatement(first.deepCopy(), second.deepCopy());
    }
}
