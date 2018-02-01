package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Box<T> {

    private List<T> box;
    private BoxIterator<T> iterator;

    public Box(){
        box = new ArrayList<T>();
        iterator = new BoxIterator<T>(box);
    }

    public void add(T t) {
        box.add(t);
    }

    public List<T> getList() {
        return box;
    }    

    public BoxIterator<T> getIterator(){
        return iterator;
    }

    public void clear(){
        box.clear();
    }

    public int size(){
        return box.size();
    }

    public boolean remove(T t){
        if (box.remove(t)){
            return true;
        }
        return false;
    }

    public boolean has(T t){
        return iterator.hasObject(t);
    }

    public boolean addUnique(T t){
        if (has(t)){
            return false;
        }
        add(t);
        return true;
    }

    public T get(int index){
        if (index < size()){
            return box.get(index);
        }
        return null;
    }
}