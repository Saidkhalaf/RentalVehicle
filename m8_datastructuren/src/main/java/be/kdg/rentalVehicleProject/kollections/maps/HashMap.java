package be.kdg.rentalVehicleProject.kollections.maps;


import be.kdg.rentalVehicleProject.kollections.lists.ArrayList;
import be.kdg.rentalVehicleProject.kollections.lists.List;
import be.kdg.rentalVehicleProject.kollections.sets.ArraySet;
import be.kdg.rentalVehicleProject.kollections.sets.Set;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 100;

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] buckets;
    private int size = 0;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int capacity) {
        buckets = new Node[capacity];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    @Override
    public void put(K key, V value) {
        int hashKey = hash(key);
        Node<K, V> bucket = buckets[hashKey];
        if (bucket == null) {
            buckets[hashKey] = new Node<>(key, value);
        } else {
            while (bucket.next != null) {
                bucket = bucket.next;
            }
            bucket.next = new Node<>(key, value);
        }
        size++;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        int keyIndex = hash(key);

        Node<K, V> bucket = buckets[keyIndex];

        while(bucket != null) {
            if (bucket.key.equals(key)) {
                return bucket.value;
            }
            bucket = bucket.next;
        }
        return null;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<>(size);
        for (Node<K, V> bucket : buckets) {
            Node<K, V> node = bucket;
            while (node != null) {
                values.add(node.value);
                node = node.next;
            }
        }
        return values;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new ArraySet<>();
        for (Node<K, V> bucket : buckets) {
            Node<K, V> node = bucket;
            while (node != null) {
                keySet.add(node.key);
                node = node.next;
            }
        }
        return keySet;
    }
}
