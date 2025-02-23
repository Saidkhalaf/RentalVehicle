package be.kdg.rentalVehicleProject.threading;

import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicleFactory;

import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RentalVehicleCallable implements Callable {

    private Predicate<RentalVehicle> predicate;

    public RentalVehicleCallable(Predicate<RentalVehicle> predicate) {
        this.predicate = predicate;
    }

    @Override
    public Object call() throws Exception {
        return Stream.generate(RentalVehicleFactory::newRandomRentalVehicle).filter(predicate).limit(1000).collect(Collectors.toList());
    }
}
