package Model.Types;

import Model.Values.BoolValue;
import Model.Values.ValueInterface;

public class BoolType implements TypeInterface{
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BoolType)
            return  true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public ValueInterface defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public TypeInterface deepCopy() {
        return new BoolType();
    }
}
