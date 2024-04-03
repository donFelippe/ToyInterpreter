package Model.Statements;

import Exceptions.MyException;
import Model.Dictionary.MyIDictionary;
import Model.ProgramState;
import Model.Values.ValueInterface;

import java.lang.reflect.Type;

public interface StatementInterface {
    abstract ProgramState execute(ProgramState state) throws MyException;

    StatementInterface deepCopy();

    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
