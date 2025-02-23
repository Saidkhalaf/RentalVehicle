package be.kdg.rentalVehicleProject.model;

import java.time.LocalDate;
import java.util.Objects;

public final class RentalVehicle implements Comparable<RentalVehicle> {
    private final String model; // String
    private final double rentalPricePerDay; // double
    private final int antalPersons; // int
    private final VehicleType vehicleType; // Enum
    private final LocalDate manufactureDate; // LocalDate
    private final String vehicleRegNr;

    public RentalVehicle(String model, double rentalPricePerDay, int antalPersons, VehicleType vehicleType, LocalDate manufactureDate, String vehicleRegNr) {
        this.model = model;
        this.rentalPricePerDay = rentalPricePerDay;
        this.antalPersons = antalPersons;
        this.vehicleType = vehicleType;
        this.manufactureDate = manufactureDate;
        this.vehicleRegNr = vehicleRegNr;
    }

    // Default constructor
    public RentalVehicle() {
        this("Unknown", 0.0, 0, VehicleType.OTHER, LocalDate.of(2000, 1, 1), "Unknown");
    }

    public String getModel() {
        return model;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public int getAntalPersons() {
        return antalPersons;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public String getVehicleRegNr() {
        return vehicleRegNr;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleRegNr);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RentalVehicle that = (RentalVehicle) obj;
        return Objects.equals(vehicleRegNr, that.vehicleRegNr);
    }

    @Override
    public int compareTo(RentalVehicle otherVehicle) {
        // Implement compareTo based on the 'vehicleRegNr' attribute
        return this.vehicleRegNr.compareTo(otherVehicle.vehicleRegNr);
    }

    @Override
    public String toString() {
        return String.format("Model: %-25s (°%d)    RegNr: %s     Vehicle_Type: %-20s Price: %-10.1f %d",
                model, manufactureDate.getYear(), vehicleRegNr, vehicleType, rentalPricePerDay, antalPersons);
    }
}
