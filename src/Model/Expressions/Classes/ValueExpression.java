package Model.Expressions.Classes;

import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Heap.MyIHeap;
import Model.Dictionary.MyIDictionary;
import Model.Values.ValueInterface;

import java.lang.reflect.Type;

public class ValueExpression implements ExpressionInterface {
    ValueInterface e;

    public ValueExpression(ValueInterface e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return String.valueOf(e);
    }

    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> tbl, MyIHeap<ValueInterface> heap) throws MyException {
        return e;
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new ValueExpression(e.deepCopy());
    }


    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return (Type) e.getType();
    }
}

