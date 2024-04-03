package Model.Statements.Classes;
import Model.ProgramState;
import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Heap.MyIHeap;
import Model.Dictionary.MyIDictionary;
import Model.Statements.StatementInterface;
import Model.Types.StringType;
import Model.Values.StringValue;
import Model.Values.ValueInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;

public class CloseRFile implements StatementInterface {
    private ExpressionInterface expression;

    public CloseRFile(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symTable = state.getSymTable();
        MyIHeap<ValueInterface> heap = state.getHeap();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        ValueInterface expValue = expression.eval(symTable, heap);
        if (expValue.getType().equals(new StringType())){
            StringValue file = (StringValue)expValue;
            if (fileTable.isDefined(file)){
                BufferedReader reader = fileTable.getElementByKey(file);
                try {
                    reader.close();
                }
                catch (IOException ex){
                    throw new MyException(ex.getMessage());
                }
                fileTable.remove(file);
            }
            else throw new MyException("File " + file + " does not exist!");
        }
        else throw new MyException("Expression must be a string!");

        return null;
    }
    @Override
    public StatementInterface deepCopy() {
        return new CloseRFile(expression.deepCopy());
    }
    @Override
    public String toString() {
        return "closeRFile(" + expression.toString() + ")";
    }
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type expType = expression.typecheck(typeEnv);

        if (expType.equals(new StringType())) {
            return typeEnv;
        } else {
            throw new MyException("Expression in closeRFile must be of string type");
        }
    }
}
