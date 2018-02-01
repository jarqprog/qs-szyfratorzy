package application;

import java.util.Iterator;
import java.util.List;

class BoxIterator<T> implements Iterator{

    private int index = 0;
    private List<T> box;

    public BoxIterator(List<T> box){
        this.box = box;
    }

    public boolean hasNext(){
        if (index < box.size()){
            return true;
        }
        return false;
    }

    public Object next(){
        if (this.hasNext()){
            return box.get(index++);
        }
        return null;
    }

    public boolean hasObject(T t){
        while(this.hasNext()){
            if (this.next().equals(t)){
                return true;
            }
        }
        return false;
    }
}