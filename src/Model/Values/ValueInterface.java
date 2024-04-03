package Model.Values;


import Model.Types.TypeInterface;

import java.lang.reflect.Type;

public interface ValueInterface extends Type {
    TypeInterface getType();
    ValueInterface deepCopy();
}
