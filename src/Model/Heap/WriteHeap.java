package Model.Heap;

import Model.ProgramState;
import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Dictionary.MyIDictionary;
import Model.Statements.StatementInterface;
import Model.Types.RefType;
import Model.Values.RefValue;
import Model.Values.ValueInterface;

import java.lang.reflect.Type;

public class WriteHeap implements StatementInterface {
    String variableName;
    ExpressionInterface expression;
    public WriteHeap(String name, ExpressionInterface exp) { variableName = name; expression = exp; }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symTable = state.getSymTable();
        MyIHeap<ValueInterface> heapTable = state.getHeap();

        if (symTable.isDefined(variableName)){
            ValueInterface variableValue = symTable.getElementByKey(variableName);
            if (variableValue.getType() instanceof RefType){
                RefValue variableReference = (RefValue)variableValue;
                int address = variableReference.getAddr();
                if (heapTable.isDefined(address)){
                    ValueInterface expressionValue = expression.eval(symTable, heapTable);
                    RefType locationType = (RefType)variableReference.getType();
                    if (locationType.getInner().equals(expressionValue.getType())){
                        heapTable.update(address, expressionValue);
                    }
                    else throw new MyException(expressionValue + " " + locationType + " unmatched");
                }
                else throw new MyException("Address " + address + " bad");
            }
            else throw new MyException("Variable " + variableName + " not ref");
        }
        else throw new MyException("Variable " + variableName + " undeclared!");
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new WriteHeap(variableName, expression.deepCopy());
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type varType = typeEnv.getElementByKey(variableName);
        Type expType = expression.typecheck(typeEnv);

        if (varType instanceof RefType) {
            RefType refVarType = (RefType) varType;
            Type innerType = (Type) refVarType.getInner();

            if (!innerType.equals(expType)) {
                throw new MyException("Types of " + variableName + " and " + expression + " do not match!");
            }

            return typeEnv;
        } else {
            throw new MyException("Variable " + variableName + " must be a RefType");
        }
    }
    @Override
    public String toString() { return "WH (" + variableName + ", " + expression + ");"; }
}
