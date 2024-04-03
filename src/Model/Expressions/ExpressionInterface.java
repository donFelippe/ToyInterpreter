package Model.Expressions;

import Exceptions.MyException;
import Model.Heap.MyIHeap;
import Model.Dictionary.MyIDictionary;
import Model.Values.ValueInterface;

import java.lang.reflect.Type;

public interface ExpressionInterface {
    ValueInterface eval(MyIDictionary<String, ValueInterface> tbl, MyIHeap<ValueInterface> heap) throws MyException;
    ExpressionInterface deepCopy();

    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
