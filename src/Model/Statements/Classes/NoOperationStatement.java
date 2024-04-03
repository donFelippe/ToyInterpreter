package Model.Statements.Classes;

import Model.Dictionary.MyIDictionary;
import Model.ProgramState;
import Exceptions.MyException;
import Model.Statements.StatementInterface;

import java.lang.reflect.Type;

public class NoOperationStatement implements StatementInterface {
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new NoOperationStatement();
    }

    @Override
    public String toString() {
        return "no operation";
    }
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
