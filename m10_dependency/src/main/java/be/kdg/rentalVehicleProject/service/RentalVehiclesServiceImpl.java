package be.kdg.rentalVehicleProject.service;

import be.kdg.rentalVehicleProject.database.RentalVehicleDao;
import be.kdg.rentalVehicleProject.model.RentalVehicle;

import java.util.List;

public class RentalVehiclesServiceImpl implements RentalVehiclesService{

    private RentalVehicleDao db;

    public RentalVehiclesServiceImpl(RentalVehicleDao db) {this.db = db;}
    @Override
    public List<RentalVehicle> getAllRentalVehicles() {return db.getAllRentalVehicles();}
    @Override
    public void addRentalVehicle(RentalVehicle rentalVehicle) { db.insert(rentalVehicle);}
}
