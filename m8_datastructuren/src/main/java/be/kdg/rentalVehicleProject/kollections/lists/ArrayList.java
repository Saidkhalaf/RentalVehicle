package be.kdg.rentalVehicleProject.kollections.lists;

import be.kdg.rentalVehicleProject.kollections.Kollections;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    public ArrayList(int size) {
        this.size = size;
    }

    private void expand() {
        //create a new array with double the elements length
        Object[] newElements = new Object[elements.length * 2];

        //copy over the elements
        System.arraycopy(elements, 0, newElements, 0, elements.length);

        //Replace the old array with the new one !
        elements = newElements;
    }

    @Override
    public void add(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }

        if (elements.length == size)
            expand();
        if (index == 0) {
            Object[] tempArr = new Object[elements.length + (size > elements.length ? 0 : 1)];
            System.arraycopy(elements, 0, tempArr, 1, elements.length);
            tempArr[0] = element;
            elements = tempArr;
        } else {
            elements[index] = element;
        }
        size++;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public boolean remove(E element) {
        return false;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public void set(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        elements[index] = element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        E oldValue = (E) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return oldValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        return (E) elements[index];
    }

    @Override
    public int indexOf(E element) {
        return Kollections.lineairSearch(this, element);
    }


}
