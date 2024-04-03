package Model.Statements.Classes;
import Model.ProgramState;
import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Stack.MyIStack;
import Model.Heap.MyIHeap;
import Model.Dictionary.MyIDictionary;
import Model.Statements.StatementInterface;
import Model.Values.StringValue;
import Model.Values.ValueInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

public class openRFile implements StatementInterface {
    private ExpressionInterface expression;

    public openRFile(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<StatementInterface> exeStack = state.getExeStack();
        MyIDictionary<String, ValueInterface> symbolTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<ValueInterface> heap = state.getHeap();
        ValueInterface value = expression.eval(symbolTable,heap);
        if (!(value instanceof StringValue)) {
            throw new MyException("Expression result is not a string.");
        }

        StringValue stringValue = (StringValue) value;
        String filename = stringValue.getVal();

        if (fileTable.isDefined(stringValue)) {
            throw new MyException("File '" + filename + "' is already open.");
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            fileTable.add(stringValue, bufferedReader);
        } catch (FileNotFoundException e) {
            throw new MyException("Error opening file '" + filename + "'. File not found.");
        } catch (Exception e) {
            throw new MyException("Error opening file '" + filename + "'.");
        }

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new openRFile(expression.deepCopy());
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
    @Override
    public String toString() {
        return "openRFile(" + expression.toString() + ")";
    }
}

