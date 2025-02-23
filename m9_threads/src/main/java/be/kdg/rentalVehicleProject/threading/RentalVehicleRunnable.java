package be.kdg.rentalVehicleProject.threading;

import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicleFactory;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RentalVehicleRunnable implements Runnable{

    private Predicate<RentalVehicle> predicate;
    private List<RentalVehicle> rentalVehicleList;

    public RentalVehicleRunnable(Predicate<RentalVehicle> predicate) {
        this.predicate = predicate;
    }

    public RentalVehicleRunnable() {
    }

    @Override
    public void run() {
        rentalVehicleList = Stream.generate(RentalVehicleFactory::newRandomRentalVehicle).
                filter(predicate).limit(1000).collect(Collectors.toList());
    }

    public List<RentalVehicle> getRentalVehicleList() {
        return rentalVehicleList;
    }
}
