package Model;

import Exceptions.MyException;
import Model.Stack.MyIStack;
import Model.Heap.MyIHeap;
import Model.List.MyIList;
import Model.Dictionary.MyIDictionary;
import Model.Statements.StatementInterface;
import Model.Values.StringValue;
import Model.Values.ValueInterface;

import java.io.BufferedReader;

public class ProgramState {
    MyIStack<StatementInterface> exeStack;
    MyIDictionary<String, ValueInterface> symTable;
    MyIList<ValueInterface> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIHeap<ValueInterface> heap;
    StatementInterface originalProgram;

    int id = 1;
    private static int nextFree = 1;
    public static synchronized int getNextFree(){
        nextFree++;
        return nextFree - 1;
    }
    public int getId(){
        return id;
    }
    public ProgramState(MyIStack<StatementInterface> exeStack,
                        MyIDictionary<String, ValueInterface> symTable,
                        MyIList<ValueInterface> out, MyIDictionary<StringValue, BufferedReader> files, MyIHeap<ValueInterface> heap, StatementInterface originalProgram) throws MyException {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = files;
        this.heap = heap;
        this.id = getNextFree();

        //originalProgram.typecheck(symTable);
        this.originalProgram = originalProgram.deepCopy();
        exeStack.push(this.originalProgram);

    }
    public String toString(){
        return "EXECUTION STACK:\n" + exeStack.toString()  + "\nSYMBOLS TABLE:\n" + symTable.toString() +
                "\nOUTPUT:\n" +  out.toString() + "\nFILE TABLE:\n" + fileTable.toString() + "\nHEAP:\n"+ heap.toString()+"\n";
    }
    public MyIStack<StatementInterface> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, ValueInterface> getSymTable() {
        return symTable;
    }

    public MyIList<ValueInterface> getOut() {
        return out;
    }

    public MyIHeap<ValueInterface> getHeap(){return heap;}

    public StatementInterface getOriginalProgram() {
        return originalProgram;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }
    public void setExecutionStack(MyIStack st) { exeStack = st; }
    public void setSymbolsTable(MyIDictionary<String, ValueInterface> dict) { symTable = dict; }
    public void setOutput(MyIList<ValueInterface> l) { out = l; }

    public void setHeap(MyIHeap<ValueInterface> h) {heap = h;}

    public void addStatement(StatementInterface st){
        exeStack.push(st);
    }

    public StatementInterface deleteStatement() throws Exception{
        return exeStack.pop();
    }
    public Boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }
    public ProgramState oneStep() throws MyException {
        if(exeStack.isEmpty()) throw new MyException("prostate stack is empty");
        StatementInterface crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
}
