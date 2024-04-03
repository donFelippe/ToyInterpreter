package Model.Expressions.Classes;
import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Heap.MyIHeap;
import Model.Dictionary.MyIDictionary;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.ValueInterface;

import java.lang.reflect.Type;

public class LogicalExpression implements ExpressionInterface {
    ExpressionInterface e1;
    ExpressionInterface e2;
    String op;

    public LogicalExpression(String op, ExpressionInterface e1, ExpressionInterface e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    @Override
    public String toString() {
        return String.valueOf(e1) + String.valueOf(op) + String.valueOf(e2);
    }

    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> tbl, MyIHeap<ValueInterface> heap) throws MyException {
        ValueInterface nr1 = e1.eval(tbl, heap);
        if (nr1.getType().equals(new BoolType())) {
            ValueInterface nr2 = e2.eval(tbl, heap);
            if(nr2.getType().equals(new BoolType())){
                BoolValue v1 = (BoolValue) nr1;
                BoolValue v2 = (BoolValue) nr2;
                if(op.equals("&&"))
                    return new BoolValue(v1.getVal() && v2.getVal());
                if(op.equals("||"))
                    return new BoolValue(v1.getVal() || v2.getVal());
            } else throw new MyException("Operand 2 is not boolean");
        } else throw new MyException("Operand 1 is not boolean");
        throw new MyException("Invalid expression");
    }

    @Override
    public ExpressionInterface deepCopy() {
        return new LogicalExpression(op, e1.deepCopy(), e2.deepCopy());
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException{
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return (Type) new BoolType();
            } else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }
}
