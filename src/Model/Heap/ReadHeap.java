package Model.Heap;

import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Dictionary.MyIDictionary;
import Model.Types.RefType;
import Model.Values.RefValue;
import Model.Values.ValueInterface;

import java.lang.reflect.Type;

public class ReadHeap implements ExpressionInterface {
    ExpressionInterface expression;
    public ReadHeap(ExpressionInterface exp) { expression = exp; }

    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> table, MyIHeap<ValueInterface> heap) throws MyException {
        ValueInterface val = expression.eval(table, heap);
        if (val.getType() instanceof RefType){
            RefValue reference = (RefValue)val;
            int address = reference.getAddr();
            ValueInterface valueAtAddress = heap.getElementByKey(address);
            if (valueAtAddress == null)
                throw new MyException("Address " + address + " bad");
            return valueAtAddress;
        }
        else
            throw new MyException("Not refValue");
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new ReadHeap(expression.deepCopy());
    }

    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException{
        Type typ=expression.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft =(RefType) typ;
            return (Type) reft.getInner();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }
    @Override
    public String toString() { return "RH(" + expression + ")"; }
}

