package be.kdg.rentalVehicleProject.model;

import be.kdg.rentalVehicleProject.adapter.LocalDateXmlAdapter;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.Objects;

@XmlType(propOrder = {"rentalPricePerDay", "aantalPersons", "vehicleType", "manufactureDate", "vehicleRegNr"})
public class RentalVehicle implements Comparable<RentalVehicle> {
    private String model; // String
    private double rentalPricePerDay; // double
    private int aantalPersons; // int
    private VehicleType vehicleType; // Enum
    private LocalDate manufactureDate; // LocalDate
    private String vehicleRegNr;

    public RentalVehicle(String model, double rentalPricePerDay, int aantalPersons, VehicleType vehicleType,
                         LocalDate manufactureDate, String vehicleRegNr) {
        setModel(model); // Using setter with validation
        setRentalPricePerDay(rentalPricePerDay);
        setAantalPersons(aantalPersons);
        setVehicleType(vehicleType);
        setManufactureDate(manufactureDate);
        setVehicleRegNr(vehicleRegNr);
    }

    // Default constructor
    public RentalVehicle() {
        RentalVehicle rentalVehicle = new RentalVehicle(
                "Toyota Camry", 80.0, 4, VehicleType.CAR, LocalDate.of(2022, 01,01), "ABC123"
        );
    }


    public String getModel() {
        return model;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public int getAantalPersons() {
        return aantalPersons;
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

    @XmlAttribute(name = "model")
    public void setModel(String model) {
        if(model.isBlank() || model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be empty!");
        }
        this.model = model;
    }


    @XmlElement(name = "daily-price")
    public void setRentalPricePerDay(double rentalPricePerDay) {
        if (rentalPricePerDay < 0.0) {
            throw new IllegalArgumentException("Rental price per day cannot be negative.");
        }
        this.rentalPricePerDay = rentalPricePerDay;
    }

    @XmlElement(name = "aantal-persons")
    public void setAantalPersons(int aantalPersons) {
        if (aantalPersons < 0) {
            throw new IllegalArgumentException("Number of persons cannot be negative.");
        }
        this.aantalPersons = aantalPersons;
    }

    @XmlElement(name = "manufacture-date")
    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    public void setManufactureDate(LocalDate manufactureDate) {
        if (manufactureDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Manufacture date cannot be in the future.");
        }
        this.manufactureDate = manufactureDate;
    }

    @XmlElement(name = "vehicle-reg-nr")
    public void setVehicleRegNr(String vehicleRegNr) {
        if (vehicleRegNr == null || vehicleRegNr.isEmpty()) {
            throw new IllegalArgumentException("Vehicle registration number cannot be empty.");
        }
        this.vehicleRegNr = vehicleRegNr;
    }

    @XmlElement(name = "vehicle-type")
    public void setVehicleType(VehicleType vehicleType) {
        if (vehicleType == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null.");
        }
        this.vehicleType = vehicleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
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
                model, manufactureDate.getYear(), vehicleRegNr, vehicleType, rentalPricePerDay, aantalPersons);
    }
}
