package be.kdg.rentalVehicleProject.threading;

import be.kdg.rentalVehicleProject.model.RentalVehicle;

import java.util.List;
import java.util.function.Predicate;

public class RentalVehicleAttacker implements Runnable {

    private List<RentalVehicle> rentalVehicleList;
    private Predicate<RentalVehicle> predicate;
    private final Object lock = new Object();

    public RentalVehicleAttacker(List<RentalVehicle> rentalVehicleList, Predicate<RentalVehicle> predicate) {
        this.rentalVehicleList = rentalVehicleList;
        this.predicate = predicate;
    }

    @Override
    public void run() {
        synchronized (rentalVehicleList){
            rentalVehicleList.removeIf(predicate);
        }

    }
}
