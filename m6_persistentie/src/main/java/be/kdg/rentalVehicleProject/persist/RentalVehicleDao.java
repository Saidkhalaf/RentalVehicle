package be.kdg.rentalVehicleProject.persist;

import be.kdg.rentalVehicleProject.model.RentalVehicle;

import java.util.List;

public interface RentalVehicleDao {

    boolean insert(RentalVehicle rentalVehicle);
    boolean delete(String model);
    boolean update(RentalVehicle rentalVehicle);
    RentalVehicle retrieve(String model);
    List<RentalVehicle> sortedOn(String query);
}
