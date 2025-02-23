package be.kdg.rentalVehicleProject.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentalVehicle implements Comparable<RentalVehicle> {
    private String model; // String
    private double rentalPricePerDay; // double
    private int antalPersons; // int
    private VehicleType vehicleType; // Enum
    private LocalDate manufactureDate; // LocalDate
    private String vehicleRegNr;

    private final Logger logger = Logger.getLogger(RentalVehicle.class.getName());

    public RentalVehicle(String model, double rentalPricePerDay, int antalPersons, VehicleType vehicleType,
                         LocalDate manufactureDate, String vehicleRegNr) {
        setModel(model); // Using setter with validation
        setRentalPricePerDay(rentalPricePerDay);
        setAntalPersons(antalPersons);
        setVehicleType(vehicleType);
        setManufactureDate(manufactureDate);
        setVehicleRegNr(vehicleRegNr);
        logger.setLevel(Level.FINER);
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

    public void setModel(String model) {
        if (model.isEmpty()) {
            logger.severe("Model cannot be empty!");
        }
        this.model = model;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        if (rentalPricePerDay < 0.0) {
            logger.severe("Rental price for " +model+ " per day cannot be negative.");
        }
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public void setAntalPersons(int antalPersons) {
        if (antalPersons < 0) {
            logger.severe("Number of persons for " +model+ " cannot be negative.");
        }
        this.antalPersons = antalPersons;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        if (manufactureDate.isAfter(LocalDate.now())) {
            logger.severe("Manufacture  for " +model+ " cannot be in the future.");
        }
        this.manufactureDate = manufactureDate;
    }

    public void setVehicleRegNr(String vehicleRegNr) {
        if (vehicleRegNr == null || vehicleRegNr.isEmpty()) {
            logger.severe("Vehicle registration number for " +model+ " cannot be empty.");
        }
        this.vehicleRegNr = vehicleRegNr;
    }

    public void setVehicleType(VehicleType vehicleType) {
        if (vehicleType == null) {
            logger.severe("Vehicle type for " +model+ " cannot be null.");
        }
        this.vehicleType = vehicleType;
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
