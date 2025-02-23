package be.kdg.rentalVehicleProject.kollections.maps;


import be.kdg.rentalVehicleProject.kollections.Collection;
import be.kdg.rentalVehicleProject.kollections.sets.Set;

public interface Map<K, V> {
    void put(K key, V value);
    V get(K key);
    Collection<V> values();
    Set<K> keySet();
    int size();
}
