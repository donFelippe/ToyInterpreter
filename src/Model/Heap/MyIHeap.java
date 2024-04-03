/*

package Model.Heap;

public interface IHeap <TKey, TValue> {
    java.util.Collection<TValue> values();

    void setContent(java.util.Map<TKey,TValue> map);

    void setFirstAvailablePosition();

    int getFirstAvailablePosition();

    int size();

    boolean containsKey(TKey tKey);

    boolean containsValue(TValue tValue);

    boolean isEmpty();

    void update(TKey tKey, TValue tValue);

    void insert(TKey tKey, TValue tValue);

    void clear();

    TValue getValue(TKey tKey);

    TValue remove(TKey tKey);

    java.util.Collection<TValue> getAllValues();

    java.util.Collection<TKey> getAllKeys();

    java.util.Map<TKey,TValue> getContent();
}
 */

package Model.Heap;


import Exceptions.MyException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MyIHeap<V> {
    void add(V value);
    String display();
    V getElementByKey(Integer key);
    boolean isDefined(Integer key);
    void update(Integer key, V newValue);
    List<Map.Entry<Integer, V>> getAll();
    void setHeap(Map<Integer, V> heap);
    Map<Integer, V> getHeap();
    V remove(Integer key) throws MyException;
    Collection<V> values();
    int getNextFree();
    void setContent(Map<Integer, V> map);
}
