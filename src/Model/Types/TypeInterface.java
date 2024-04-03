package Model.Types;

import Exceptions.MyException;
import Model.Dictionary.MyIDictionary;
import Model.Values.ValueInterface;


public interface TypeInterface {
    ValueInterface defaultValue();
    TypeInterface deepCopy();
    //TypeInterface typecheck(MyIDictionary<String,TypeInterface> typeEnv) throws MyException;
}
