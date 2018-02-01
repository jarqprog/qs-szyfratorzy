package application;

import java.util.ArrayList;
import java.util.List;

public class Box<E> extends ArrayList<E> implements List<E> {

    private List<E> box;
    private BoxIterator<E> iterator;

    public Box(){
        box = new ArrayList<>();
        iterator = new BoxIterator<>(box);
    }

    public boolean add(E e) {

        return box.add(e);
    }

    public List<E> get() {
        return box;
    }

    public void set(List<E> box) {
        this.box = box;
    }

    public BoxIterator<E> getIterator(){
        return iterator;
    }

    public void clear(){
        box.clear();
    }

    public int size(){
        return box.size();
    }

    public boolean remove(Object object){
        return box.remove(object);
    }

    public boolean has(E e){
        return iterator.hasObject(e);
    }

    public boolean addUnique(E e){
        if (has(e)){
            return false;
        }
        add(e);
        return true;
    }

    public E get(int index){
        if (index < size()){
            return box.get(index);
        }
        return null;
    }
}