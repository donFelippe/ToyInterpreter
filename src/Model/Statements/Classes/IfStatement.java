package Model.Statements.Classes;

import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Stack.MyIStack;
import Model.Heap.MyIHeap;
import Model.Dictionary.MyIDictionary;
import Model.Statements.StatementInterface;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Values.ValueInterface;

import java.lang.reflect.Type;

public class IfStatement implements StatementInterface {
    ExpressionInterface exp;
    StatementInterface thenS;
    StatementInterface elseS;

    public IfStatement(ExpressionInterface exp, StatementInterface thenS, StatementInterface elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }
    @Override
    public String toString(){
        return "(IF(" + exp.toString() + ")THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))";
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symTbl = state.getSymTable();
        MyIStack<StatementInterface> stk = state.getExeStack();
        MyIHeap<ValueInterface> heap = state.getHeap();
        ValueInterface cond = exp.eval(symTbl, heap);

        if (!cond.getType().equals(new BoolType()))
            throw new MyException("Conditional expression is not a boolean");
        else {
            if (Boolean.parseBoolean(cond.toString()) == Boolean.TRUE)
                stk.push(thenS);
            else
                stk.push(elseS);
        }
        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.clone());
            elseS.typecheck(typeEnv.clone());
            return typeEnv;
        } else {
            throw new MyException("The condition of IF has not the type bool");
        }
    }

    @Override
    public StatementInterface deepCopy() {
        return new IfStatement(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }
}
