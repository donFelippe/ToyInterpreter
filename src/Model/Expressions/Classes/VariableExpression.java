package Model.Expressions.Classes;
import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Heap.MyIHeap;
import Model.Dictionary.MyIDictionary;
import Model.Values.ValueInterface;

import java.lang.reflect.Type;

public class VariableExpression implements ExpressionInterface {
    String id;
    public VariableExpression(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return id;
    }
    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> tbl, MyIHeap<ValueInterface> heap) throws MyException {
        return tbl.getElementByKey(id);
    }
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException{
        return typeEnv.getElementByKey(id);
    }
    @Override
    public ExpressionInterface deepCopy() {
        return new VariableExpression(id);
    }
}
