package be.kdg.rentalVehicleProject.model;

import be.kdg.rentalVehicleProject.reflection.CanRun;

import java.time.LocalDate;
import java.util.Objects;

public class Vehicle implements Comparable<Vehicle> {
    private String vehicleRegNr; // Registration number
    private String model; // String
    private LocalDate manufactureDate; // int

    public Vehicle(String vehicleRegNr, String model, LocalDate manufactureDate) {
        this.vehicleRegNr = vehicleRegNr;
        this.model = model;
        this.manufactureDate = manufactureDate;
    }

    public String getVehicleRegNr() {
        return vehicleRegNr;
    }

    @CanRun()
    public void setVehicleRegNr(String vehicleRegNr) {
        if (vehicleRegNr == null || vehicleRegNr.isEmpty()) {
            throw new IllegalArgumentException("Vehicle registration number cannot be empty.");
        }
        this.vehicleRegNr = vehicleRegNr;
    }

    public String getModel() {
        return model;
    }

    @CanRun("Unknown")
    public void setModel(String model) {
        if (model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be empty.");
        }
        this.model = model;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate antalPersons) {
        if (antalPersons.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Manufacture date cannot be in the future.");
        }
        this.manufactureDate = antalPersons;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleRegNr);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle other = (Vehicle) obj;
        return Objects.equals(vehicleRegNr, other.vehicleRegNr);
    }

    @Override
    public int compareTo(Vehicle other) {
        return vehicleRegNr.compareTo(other.vehicleRegNr);
    }

    @Override
    public String toString() {
        return String.format("Model: %-25s (°%d)    RegNr: %s", model, manufactureDate.getYear(), vehicleRegNr);
    }
}
