package be.kdg.rentalVehicleProject.service;

import be.kdg.rentalVehicleProject.model.RentalVehicle;

import java.util.List;

public interface RentalVehiclesService {
    List<RentalVehicle> getAllRentalVehicles();

    void addRentalVehicle(RentalVehicle rentalVehicle);

}
