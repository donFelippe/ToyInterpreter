package Model.Stack;

import java.util.Stack;

public interface MyIStack<T> {
    T pop();
    void push(T v);
    Stack<T> getAll();
    boolean isEmpty();
    String display();
    String toString();
}
