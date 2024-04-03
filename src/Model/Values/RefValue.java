package Model.Values;

import Model.Types.RefType;
import Model.Types.TypeInterface;

public class RefValue implements ValueInterface{
    int address;
    TypeInterface locationType;
    public int  getAddr(){ return address;}

    public RefValue(int addr, TypeInterface locationType){
        this.address = addr;
        this.locationType = locationType;
    }

    @Override
    public TypeInterface getType(){return new RefType(locationType);
    }

    @Override
    public ValueInterface deepCopy() {
        return new RefValue(address, locationType);
    }

    public String toString(){ return "("+ address + "," + locationType + ")";}

}
