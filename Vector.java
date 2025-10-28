package laba1;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Vector<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;
    
    public Vector() {
    	this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    
    public Vector(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }
    
    public void add(T element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }
    
    public void add(int index, T element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }
    
    @SuppressWarnings("unchecked")
    public T get(int index) {
        rangeCheck(index);
        return (T) elements[index];
    }
    
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        rangeCheck(index);
        T oldValue = (T) elements[index];
        elements[index] = element;
        return oldValue;
    }
    
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        rangeCheck(index);
        T oldValue = (T) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // Помощь сборщику мусора
        return oldValue;
    }
    
    public boolean remove(Object obj) {
        for (int i = 0; i < size; i++) {
            if (obj.equals(elements[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }
    
    // поиск индекса элемента
    public int indexOf(Object obj) {
        for (int i = 0; i < size; i++) {
            if (obj.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    // преобразование в массив
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) Arrays.copyOf(elements, size);
    }
    
    // метод для обеспечения недостающей емкости
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }
    
    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    
    // итератор
    @Override
    public Iterator<T> iterator() {
        return new VectorIterator();
    }
    
    private class VectorIterator implements Iterator<T> {
        private int cursor = 0;
        
        @Override
        public boolean hasNext() {
            return cursor < size;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) elements[cursor++];
        }
        
        @Override
        public void remove() {
            Vector.this.remove(--cursor);
        }
    }
    
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}


