package Model.Statements.Classes;

import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Heap.MyIHeap;
import Model.Dictionary.MyIDictionary;
import Model.Statements.StatementInterface;
import Model.ProgramState;
import Model.Types.TypeInterface;
import Model.Values.ValueInterface;

import java.lang.reflect.Type;

public class AssignmentStatement implements StatementInterface {
    String id;
    ExpressionInterface exp;

    public AssignmentStatement(String id, ExpressionInterface exp) {
        this.id = id;
        this.exp = exp;
    }

    public String toString() {
        return id + "=" + exp.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symTbl = state.getSymTable();
        MyIHeap<ValueInterface> heap = state.getHeap();
        if (symTbl.isDefined(id)) {
            ValueInterface val = exp.eval(symTbl, heap);
            TypeInterface typId = symTbl.getElementByKey(id).getType();
            if (val.getType().equals(typId))
                symTbl.update(id, val);
            else
                throw new MyException("declared type of variable " + id + " and type of the assigned expression does not match");
        } else throw new MyException("the used variable " + id + " was not declared before");
        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws
            MyException {
        Type typevar = typeEnv.getElementByKey(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }

    @Override
    public StatementInterface deepCopy() {
        return new AssignmentStatement(id, exp.deepCopy());
    }
}
