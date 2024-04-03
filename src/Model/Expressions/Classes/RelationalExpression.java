package Model.Expressions.Classes;

import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Heap.MyIHeap;
import Model.Dictionary.MyIDictionary;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.ValueInterface;

import java.lang.reflect.Type;

public class RelationalExpression implements ExpressionInterface {
    ExpressionInterface e1;
    ExpressionInterface e2;
    String op;

    public RelationalExpression(ExpressionInterface e1, ExpressionInterface e2, String op) {
        this.e1 = e1; this.e2 = e2; this.op = op; }


    @Override
    public ValueInterface eval(MyIDictionary<String, ValueInterface> tbl, MyIHeap<ValueInterface> heap) throws MyException {
        ValueInterface v1, v2;
        v1 = e1.eval(tbl, heap);
        if(v1.getType().equals(new IntType()))
        {
            v2 = e2.eval(tbl, heap);
            if(v1.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1 = i1.getVal();
                int n2 = i2.getVal();
                switch (op){
                    case "<":
                        return new BoolValue(n1 < n2);
                    case "<=":
                        return new BoolValue(n1 <= n2);
                    case "==":
                        return new BoolValue(n1 == n2);
                    case "!=":
                        return new BoolValue(n1 != n2);
                    case ">":
                        return new BoolValue(n1 > n2);
                    case ">=":
                        return new BoolValue(n1 >= n2);
                    default:
                        throw new MyException("incorrect operand");
                }
            }
            else{
                throw new MyException("second operand isn't an integer");
            }
        }
        else{
            throw new MyException("first operand isn't an integer");
        }
    }

    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException{
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return (Type) new IntType();
            } else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }
    @Override
    public ExpressionInterface deepCopy() {
        return new RelationalExpression(e1.deepCopy(), e2.deepCopy(), op);
    }

    public String toString() {
        return String.valueOf(e1) + String.valueOf(op) + String.valueOf(e2);
    }

}
