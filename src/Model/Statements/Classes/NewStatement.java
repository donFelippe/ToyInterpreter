package Model.Statements.Classes;

import Model.ProgramState;
import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Heap.MyIHeap;
import Model.Dictionary.MyIDictionary;
import Model.Statements.StatementInterface;
import Model.Types.RefType;
import Model.Types.TypeInterface;
import Model.Values.RefValue;
import Model.Values.ValueInterface;

import java.lang.reflect.Type;

public class NewStatement implements StatementInterface {
    String var_name;
    ExpressionInterface expr;
    public  NewStatement(String name, ExpressionInterface expr){
        this.var_name = name;
        this.expr = expr;
    }
    public String toString() { return "new(" + var_name + ", " + expr + ");"; }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symTable = state.getSymTable();
        MyIHeap<ValueInterface> heapTable = state.getHeap();
        if (symTable.isDefined(var_name)){
            ValueInterface val = symTable.getElementByKey(var_name);
            if (val.getType() instanceof RefType){
                RefValue reference = (RefValue)val;
                ValueInterface expVal = expr.eval(symTable, heapTable);
                RefType referenceType = (RefType) reference.getType();
                TypeInterface locationType = referenceType.getInner();
                int key = heapTable.getNextFree();
                if (expVal.getType().equals(locationType)){
                    heapTable.add(expVal);
                    symTable.add(var_name, new RefValue(key, locationType));
                }
                else throw new MyException("Types of " + expr + " and " + var_name + " do not match!");
            }
            else
                throw new MyException("Variable " + var_name + " must be a refType");
        }
        else
            throw new MyException("The variable " + var_name + " was not declared!");
        return null;
    }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException{
        Type typevar = typeEnv.getElementByKey(var_name);
        Type typexp = expr.typecheck(typeEnv);
        if (typevar.equals(new RefType((TypeInterface) typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }
    @Override
    public StatementInterface deepCopy() {
        return new NewStatement(var_name, expr.deepCopy());
    }
}
