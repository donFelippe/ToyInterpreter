package Model.Statements.Classes;

import Model.ProgramState;
import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Stack.MyIStack;
import Model.Heap.MyIHeap;
import Model.Dictionary.MyIDictionary;
import Model.Statements.StatementInterface;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.ValueInterface;

import java.lang.reflect.Type;

public class WhileStatement implements StatementInterface {
    ExpressionInterface expr;
    StatementInterface stmt;
    public WhileStatement(ExpressionInterface exp, StatementInterface stmt) { this.expr = exp; this.stmt = stmt; }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, ValueInterface> symTable = state.getSymTable();
        MyIHeap<ValueInterface> heapTable = state.getHeap();
        MyIStack<StatementInterface> exeStack = state.getExeStack();

        ValueInterface expressionValue = expr.eval(symTable, heapTable);
        if (expressionValue.getType().equals(new BoolType())){
            BoolValue boolVal = (BoolValue)expressionValue;
            if (!boolVal.getVal()){
                return state;
            }
            else {
                exeStack.push(this);
                exeStack.push(stmt);
                return null;
            }
        }
        else throw new MyException("Expression " + expr + " must be a boolValue!");
    }
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type conditionType = expr.typecheck(typeEnv);
        if (conditionType.equals(new BoolType())) {
            stmt.typecheck(typeEnv.clone());
            return typeEnv;
        } else {
            throw new MyException("The condition of WHILE has not the type bool");
        }
    }

    @Override
    public StatementInterface deepCopy() {
        return new WhileStatement(expr.deepCopy(), stmt.deepCopy());
    }

    @Override
    public String toString() { return "while (" + expr + ") do " + stmt; }
}