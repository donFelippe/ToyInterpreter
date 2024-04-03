package Model.List;

import java.util.List;

public interface MyIList<T> {
    void add(T item);
    void delete(T item);
    List<T> getAll();
    int size();
    boolean isEmpty();
    String display();
}
