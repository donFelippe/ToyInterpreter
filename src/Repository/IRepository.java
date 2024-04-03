package Repository;
import Model.ProgramState;
import Exceptions.MyException;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    void addProgram(ProgramState pg);
    List<ProgramState> getProgramList();
    public void logProgramStateExecution(ProgramState state) throws MyException, IOException;
    ProgramState getPrgAtIndex(int index) throws MyException;
    void setProgramsList(List<ProgramState> prg);
}