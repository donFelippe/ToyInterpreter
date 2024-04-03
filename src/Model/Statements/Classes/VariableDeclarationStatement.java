package Model.Statements.Classes;

import Model.ProgramState;
import Exceptions.MyException;
import Model.Stack.MyIStack;
import Model.Dictionary.MyIDictionary;
import Model.Statements.StatementInterface;
import Model.Types.TypeInterface;
import Model.Values.ValueInterface;
import java.lang.reflect.Type;
public class VariableDeclarationStatement implements StatementInterface {
    String name;
    TypeInterface typ;

    public VariableDeclarationStatement(String name, TypeInterface typ) {
        this.name = name;
        this.typ = typ;
    }

    public String toString(){

        return typ.toString() + " " + name + ";";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<StatementInterface> stk = state.getExeStack();
        MyIDictionary<String, ValueInterface> symTable = state.getSymTable();
        if (symTable.isDefined(name)) {
            throw new MyException("Variable is already declared");
        } else {
            symTable.add(name, typ.defaultValue());
        }
        return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException{
        typeEnv.add(name, (Type) typ);
        return typeEnv;
    }

    @Override
    public StatementInterface deepCopy() {
        return new VariableDeclarationStatement(name, typ.deepCopy());
    }
}
