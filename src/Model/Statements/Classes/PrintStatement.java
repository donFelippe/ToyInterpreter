package Model.Statements.Classes;

import Model.Dictionary.MyIDictionary;
import Model.ProgramState;
import Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Statements.StatementInterface;
import Model.Values.ValueInterface;
import java.lang.reflect.Type;

public class PrintStatement implements StatementInterface {
    ExpressionInterface exp;
    public PrintStatement(ExpressionInterface exp) {
        this.exp = exp;
    }
    public ExpressionInterface getExp() {
        return exp;
    }
    public String toString(){
        return "print(" + exp.toString() + ")";
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ValueInterface val = exp.eval(state.getSymTable(), state.getHeap());
        state.getOut().add(val);
        return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String, Type> typeEnv) throws
            MyException{
        exp.typecheck(typeEnv);
        return typeEnv;
    }
    @Override
    public StatementInterface deepCopy() {
        return new PrintStatement(exp.deepCopy());
    }
}
