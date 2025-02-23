package be.kdg.rentalVehicleProject.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class RentalVehicle implements Comparable<RentalVehicle>, Serializable {
    private int id;
    private String model; // String
    private transient double producedVehicles;
    private double rentalPricePerDay; // double

    private int antalPersons; // int
    private VehicleType vehicleType; // Enum
    private LocalDate manufactureDate; // LocalDate
    private String vehicleRegNr;
    @Serial
    private static final long serialVersionUID = 1L;

    public RentalVehicle(String model, double producedVehicles, double rentalPricePerDay, int antalPersons, VehicleType vehicleType,
                         LocalDate manufactureDate, String vehicleRegNr) {
        this(-1, model, producedVehicles, rentalPricePerDay, antalPersons, vehicleType, manufactureDate, vehicleRegNr);
    }


    public RentalVehicle(String model, int antalPersons, LocalDate manufactureDate) {
        this(-1, model, 0.0, 0.0, antalPersons, VehicleType.OTHER, manufactureDate, "AAAAAA");
    }

    public RentalVehicle(int id, String model, double producedVehicles, double rentalPricePerDay, int antalPersons, VehicleType vehicleType,
                         LocalDate manufactureDate, String vehicleRegNr) {
        setId(id);
        setModel(model);
        setProducedVehicles(producedVehicles);
        setRentalPricePerDay(rentalPricePerDay);
        setAntalPersons(antalPersons);
        setVehicleType(vehicleType);
        setManufactureDate(manufactureDate);
        setVehicleRegNr(vehicleRegNr);
    }


    // Default constructor
    public RentalVehicle() {
        RentalVehicle rentalVehicle = new RentalVehicle("Unknown", 0.0, 0.0, 4, VehicleType.OTHER, LocalDate.of(2000, 1, 1), "Unknown");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getModel() {
        return model;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public double getProducedVehicles() {
        return producedVehicles;
    }

    public void setProducedVehicles(double producedVehicles) {
        this.producedVehicles = producedVehicles;
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
            throw new IllegalArgumentException("Model cannot be empty.");
        }
        this.model = model;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        if (rentalPricePerDay < 0.0) {
            throw new IllegalArgumentException("Rental price per day cannot be negative.");
        }
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public void setAntalPersons(int antalPersons) {
        if (antalPersons < 0) {
            throw new IllegalArgumentException("Number of persons cannot be negative.");
        }
        this.antalPersons = antalPersons;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        if (manufactureDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Manufacture date cannot be in the future.");
        }
        this.manufactureDate = manufactureDate;
    }

    public void setVehicleRegNr(String vehicleRegNr) {
        if (vehicleRegNr == null || vehicleRegNr.isEmpty()) {
            throw new IllegalArgumentException("Vehicle registration number cannot be empty.");
        }
        this.vehicleRegNr = vehicleRegNr;
    }

    public void setVehicleType(VehicleType vehicleType) {
        if (vehicleType == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null.");
        }
        this.vehicleType = vehicleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModel());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RentalVehicle that = (RentalVehicle) obj;
        return model.equals(that.model);
    }

    @Override
    public int compareTo(RentalVehicle otherVehicle) {
        // Implement compareTo based on the 'vehicleRegNr' attribute
        return this.model.compareTo(otherVehicle.model);
    }

    @Override
    public String toString() {
        return String.format("Model: %-25s (°%d)    RegNr: %s     Vehicle_Type: %-20s Price: %-10.1f %d",
                model, manufactureDate.getYear(), vehicleRegNr, vehicleType, rentalPricePerDay, antalPersons);
    }
}
