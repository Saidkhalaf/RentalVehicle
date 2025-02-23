package be.kdg.rentalVehicleProject.kollections.sets;


import be.kdg.rentalVehicleProject.kollections.Kollections;
import be.kdg.rentalVehicleProject.kollections.lists.ArrayList;
import be.kdg.rentalVehicleProject.kollections.lists.List;

public class ArraySet<T> implements Set<T> {
    private List<T> elements;

    public ArraySet() {
        elements = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        if (Kollections.lineairSearch(elements, element)==-1) {
            elements.add(element);
        }
    }

    @Override
    public boolean remove(T element) {
        return elements.remove(element);
    }

    @Override
    public boolean contains(T element) {
        return elements.contains(element);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public List<T> toList() {
        return elements;
    }
}
