package be.kdg.rentalVehicleProject.model;

import java.time.LocalDate;

public class RentalVehicle extends Vehicle {
    private double rentalPricePerDay;
    private VehicleType vehicleType;
    private int antalPersons;

    public RentalVehicle(String vehicleRegNr, String model, LocalDate manufactureDate, double rentalPricePerDay, VehicleType vehicleType, int antalPersons) {
        super(vehicleRegNr, model, manufactureDate);
        this.rentalPricePerDay = rentalPricePerDay;
        this.vehicleType = vehicleType;
        this.antalPersons = antalPersons;
    }

    // Default constructor
    public RentalVehicle() {
        super("Unknown", "Unknown", LocalDate.of(2000, 1, 1));
        this.rentalPricePerDay = 0.0;
        this.vehicleType = VehicleType.OTHER;
        this.antalPersons = 0;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        if (rentalPricePerDay < 0.0) {
            throw new IllegalArgumentException("Rental price per day cannot be negative.");
        }
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        if (vehicleType == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null.");
        }
        this.vehicleType = vehicleType;
    }

    public int getAntalPersons() {
        return antalPersons;
    }

    public void setAntalPersons(int antalPersons) {
        this.antalPersons = antalPersons;
    }

    @Override
    public String toString() {
        String vehicleString = super.toString(); // Get the superclass's string representation
        return String.format("%s    Vehicle_Type: %-20s Price: %-10.1f %d", vehicleString, vehicleType, rentalPricePerDay, antalPersons);
    }

}
