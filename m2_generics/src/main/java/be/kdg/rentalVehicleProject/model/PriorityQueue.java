package be.kdg.rentalVehicleProject.model;

import java.util.*;

public class PriorityQueue<T> implements FIFOQueue<T>{

    private TreeMap<Integer, LinkedList<T>> priorityQueue;

    public PriorityQueue() {
        // Initialisatie van de TreeMap met omgekeerde volgorde voor de sleutels
        this.priorityQueue = new TreeMap<>(Comparator.reverseOrder());
    }

    @Override
    public boolean enqueue(T element, int priority) {
        // Check if the element already exists in the queue.
        if (search(element) != -1) {
            return false;
        }

        // Check if there's an entry for this priority
        if (priorityQueue.containsKey(priority)) {
            priorityQueue.get(priority).addLast(element);
        } else {
            LinkedList<T> newPriorityList = new LinkedList<>();
            newPriorityList.add(element);
            priorityQueue.put(priority, newPriorityList);
        }

        return true;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!!!!");
        }

        // Find the highest priority
        int highestPriority = priorityQueue.firstKey();
        LinkedList<T> highestPriorityList = priorityQueue.get(highestPriority);

        // Dequeue the oldest element from the highest priority list
        T element = highestPriorityList.removeFirst();

        // Remove the priority entry if it becomes empty
        if (highestPriorityList.isEmpty()) {
            priorityQueue.remove(highestPriority);
        }

        return element;
    }

    private boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    @Override
    public int search(T element) {
        int position = 0; // Initialize the position

        // Iterate through the map entries
        for (Map.Entry<Integer, LinkedList<T>> entry : priorityQueue.entrySet()){
            LinkedList<T> elementsWithPriority = entry.getValue();

            // Check if the element is in this priority's list
            if (elementsWithPriority.contains(element)){
                // If found, return the position which is the current position plus
                // the index of the element within the list
                return position + elementsWithPriority.indexOf(element);
            }
            // Increment the position by the number of elements in this priority's list
            position += elementsWithPriority.size();
        }
        return -1; //Element niet gevonden
    }

    @Override
    public int getSize() {
        int size = 0;

        // Iterate through the map entries and sum the sizes of all priority lists
        for (LinkedList<T> elementsWithPriority : priorityQueue.values()) {
            size += elementsWithPriority.size();
        }
        return size;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        // Iterate through the map entries and append the priority and elements to the result
        for (Map.Entry<Integer, LinkedList<T>> entry : priorityQueue.entrySet()) {
            int priority = entry.getKey();
            LinkedList<T> elementsWithPriority = entry.getValue();

            for (T element : elementsWithPriority) {
                result.append(priority).append(": ").append(element).append("\n");
            }
        }
        return result.toString();
    }
}
