package be.kdg.rentalVehicleProject.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.*;

@XmlRootElement(name = "rentalVehicles")
public class RentalVehicles {

    private List<RentalVehicle> rentalVehicles;

    public RentalVehicles() {
        rentalVehicles = new ArrayList<>();
    }

    //Voeg een RentalVehicle-object toe aan de set :
    public boolean add(RentalVehicle rentalVehicle) {
        return rentalVehicles.add(rentalVehicle);
    }

    //Verwijder een RentalVehicle-object op basis van het unieke attrubuut vehicleRegNr
    public boolean remove(String vehicleRegNr) {
        Iterator<RentalVehicle> iterator = rentalVehicles.iterator();
        while (iterator.hasNext()) {
            RentalVehicle vehicle = iterator.next();
            if (vehicle.getVehicleRegNr().equals(vehicleRegNr)) {
                iterator.remove();
                return true;
            }
        }
        return false; //If no matching object is found, return false
    }

    // Zoek een RentalVehicle-object op basis van het unieke attribuut vehicleRegNr
    public RentalVehicle search(String vehicleRegNr) {
        for (RentalVehicle vehicle : rentalVehicles) {
            if (vehicle.getVehicleRegNr().equals(vehicleRegNr)) {
                return vehicle;
            }
        }
        return null;
    }

    // Gesorteerde lijst van RentalVehicle-objecten op naam
    public List<RentalVehicle> sortedOnName(){
        List<RentalVehicle> sortedList = new ArrayList<>(rentalVehicles);
        sortedList.sort(Comparator.comparing(RentalVehicle:: getModel));
        return sortedList;
    }

    // Gesorteerde lijst van RentalVehicle-objecten op geboortedatum
    public List<RentalVehicle> sortedOnBirth() {
        List<RentalVehicle> sortedList = new ArrayList<>(rentalVehicles);
        sortedList.sort(Comparator.comparing(RentalVehicle::getManufactureDate));
        return sortedList;
    }

    // Gesorteerde lijst van RentalVehicle-objecten op aantal slachtoffers
    public List<RentalVehicle> sortedOnAantalPersons() {
        List<RentalVehicle> sortedList = new ArrayList<>(rentalVehicles);
        sortedList.sort(Comparator.comparingInt(RentalVehicle::getAantalPersons));
        return sortedList;
    }

    // Geeft het aantal objecten in de set terug
    public int getSize() {
        return rentalVehicles.size();
    }

    public List<RentalVehicle> getRentalVehicles() {
        return rentalVehicles;
    }
    @XmlElement(name = "rentalVehicle")
    public void setRentalVehicles(List<RentalVehicle> rentalVehicles) {
        this.rentalVehicles = rentalVehicles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalVehicles that = (RentalVehicles) o;
        return Objects.equals(rentalVehicles, that.rentalVehicles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalVehicles);
    }


}

