package be.kdg.rentalVehicleProject.kollections.sets;

import be.kdg.rentalVehicleProject.kollections.Collection;
import be.kdg.rentalVehicleProject.kollections.lists.List;

public interface Set<E> extends Collection<E> {
    List<E> toList();
}
