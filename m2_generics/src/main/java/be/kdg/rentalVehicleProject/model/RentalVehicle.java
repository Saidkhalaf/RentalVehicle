
package be.kdg.rentalVehicleProject.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The RentalVehicle class represents a rental vehicle with various properties such as model, rental price per day,
 * number of persons it can accommodate, vehicle type, manufacture date, and vehicle registration number.
 *
 * For more information about rental vehicles, see:
 * <a href="https://nl.wikipedia.org/wiki/RentalVehicle_(modern)">RentalVehicle on Wikipedia</a>
 *
 * @author Said Khalaf
 * @version 1.0
 * @see VehicleType
 */

public class RentalVehicle implements Comparable<RentalVehicle> {
    private String model; // String
    private double rentalPricePerDay; // double
    private int antalPersons; // int
    private VehicleType vehicleType; // Enum
    private LocalDate manufactureDate; // LocalDate
    private String vehicleRegNr;

    /**
     * Constructs a RentalVehicle with the given model, rental price per day, number of persons it can accommodate,
     * vehicle type, manufacture date, and vehicle registration number.
     *
     * @param model            The model of the rental vehicle.
     * @param rentalPricePerDay The rental price per day for the vehicle.
     * @param antalPersons      The number of persons the vehicle can accommodate.
     * @param vehicleType      The type of the vehicle (an enum).
     * @param manufactureDate   The manufacture date of the vehicle.
     * @param vehicleRegNr     The vehicle registration number.
     */
    public RentalVehicle(String model, double rentalPricePerDay, int antalPersons, VehicleType vehicleType,
                         LocalDate manufactureDate, String vehicleRegNr) {
        setModel(model); // Using setter with validation
        setRentalPricePerDay(rentalPricePerDay);
        setAntalPersons(antalPersons);
        setVehicleType(vehicleType);
        setManufactureDate(manufactureDate);
        setVehicleRegNr(vehicleRegNr);
    }

    /**
     * Default constructor for RentalVehicle with default values.
     */
    // Default constructor
    public RentalVehicle() {
        this("Unknown", 0.0, 0, VehicleType.OTHER, LocalDate.of(2000, 1, 1), "Unknown");
    }

    /**
     * Gets the model of the rental vehicle.
     *
     * @return The model of the rental vehicle.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the rental price per day for the vehicle.
     *
     * @return The rental price per day.
     */

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    /**
     * Gets the number of persons the vehicle can accommodate.
     *
     * @return The number of persons.
     */
    public int getAntalPersons() {
        return antalPersons;
    }

    /**
     * Gets the manufacture date of the vehicle.
     *
     * @return The manufacture date.
     */
    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    /**
     * Gets the vehicle registration number.
     *
     * @return The vehicle registration number.
     */
    public String getVehicleRegNr() {
        return vehicleRegNr;
    }

    /**
     * Gets the type of the rental vehicle.
     *
     * @return The type of the rental vehicle as a {@link VehicleType} enum.
     * @see VehicleType
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * Sets the model of the rental vehicle.
     *
     * @param model The model of the rental vehicle.
     */
    public void setModel(String model) {
        if (model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be empty.");
        }
        this.model = model;
    }

    /**
     * Sets the rental price per day for the vehicle.
     *
     * @param rentalPricePerDay The rental price per day.
     */
    public void setRentalPricePerDay(double rentalPricePerDay) {
        if (rentalPricePerDay < 0.0) {
            throw new IllegalArgumentException("Rental price per day cannot be negative.");
        }
        this.rentalPricePerDay = rentalPricePerDay;
    }

    /**
     * Sets the number of persons the vehicle can accommodate.
     *
     * @param antalPersons The number of persons.
     */
    public void setAntalPersons(int antalPersons) {
        if (antalPersons < 0) {
            throw new IllegalArgumentException("Number of persons cannot be negative.");
        }
        this.antalPersons = antalPersons;
    }

    /**
     * Sets the manufacture date of the vehicle.
     *
     * @param manufactureDate The manufacture date.
     */
    public void setManufactureDate(LocalDate manufactureDate) {
        if (manufactureDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Manufacture date cannot be in the future.");
        }
        this.manufactureDate = manufactureDate;
    }

    /**
     * Sets the vehicle registration number.
     *
     * @param vehicleRegNr The vehicle registration number.
     */
    public void setVehicleRegNr(String vehicleRegNr) {
        if (vehicleRegNr == null || vehicleRegNr.isEmpty()) {
            throw new IllegalArgumentException("Vehicle registration number cannot be empty.");
        }
        this.vehicleRegNr = vehicleRegNr;
    }

    /**
     * Sets the type of the rental vehicle.
     *
     * @param vehicleType The type of the rental vehicle as a {@link VehicleType} enum.
     * @see VehicleType
     */
    public void setVehicleType(VehicleType vehicleType) {
        if (vehicleType == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null.");
        }
        this.vehicleType = vehicleType;
    }

    /**
     * Computes the hash code of the rental vehicle based on its vehicle registration number.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(vehicleRegNr);
    }

    /**
     * Checks if this rental vehicle is equal to another object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RentalVehicle that = (RentalVehicle) obj;
        return Objects.equals(vehicleRegNr, that.vehicleRegNr);
    }

    /**
     * Compares two rental vehicles based on their vehicle registration numbers.
     *
     * @param otherVehicle The other rental vehicle to compare to.
     * @return A negative integer, zero, or a positive integer as this rental vehicle is less than, equal to, or
     * greater than the other rental vehicle.
     */
    @Override
    public int compareTo(RentalVehicle otherVehicle) {
        // Implement compareTo based on the 'vehicleRegNr' attribute
        return this.vehicleRegNr.compareTo(otherVehicle.vehicleRegNr);
    }

    /**
     * Returns a formatted string representation of the rental vehicle.
     *
     * @return A formatted string.
     */
    @Override
    public String toString() {
        return String.format("Model: %-25s (°%d)    RegNr: %s     Vehicle_Type: %-20s Price: %-10.1f %d",
                model, manufactureDate.getYear(), vehicleRegNr, vehicleType, rentalPricePerDay, antalPersons);
    }
}
