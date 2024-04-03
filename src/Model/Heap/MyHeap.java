package Model.Heap;

import Exceptions.MyException;

import java.util.*;

public class MyHeap<V> implements MyIHeap<V> {
    private final Map<Integer, V> dictionary;
    private int nextFree;

    public MyHeap() {
        dictionary = new HashMap<>();
        nextFree = 1;
    }

    public void setHeap(Map<Integer, V> heap) {
        this.dictionary.clear();
        this.dictionary.putAll(heap);
    }

    public Map<Integer, V> getHeap() {
        return Collections.unmodifiableMap(dictionary);
    }

    @Override
    public String toString() {
        StringBuilder dictString = new StringBuilder();
        for (Map.Entry<Integer, V> entry : dictionary.entrySet()) {
            dictString.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        System.out.println(dictString);
        return dictString.toString();
    }

    @Override
    public void add(V value) {
        dictionary.put(nextFree++, value);
    }

    public int getNextFree() {
        return nextFree;
    }

    @Override
    public String display() {
        return dictionary.toString();
    }

    @Override
    public V getElementByKey(Integer key) {
        return dictionary.get(key);
    }

    @Override
    public boolean isDefined(Integer key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void update(Integer key, V newValue) {
        dictionary.replace(key, newValue);
    }

    @Override
    public List<Map.Entry<Integer, V>> getAll() {
        return new ArrayList<>(dictionary.entrySet());
    }

    @Override
    public V remove(Integer key) throws MyException {
        try {
            return dictionary.remove(key);
        } catch (Exception ex) {
            throw new MyException(ex.getMessage());
        }
    }

    public Collection<V> values() {
        return dictionary.values();
    }

    public void setContent(Map<Integer, V> map) {
        dictionary.clear();
        dictionary.putAll(map);
    }
}
