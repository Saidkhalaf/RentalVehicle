package be.kdg.rentalVehicleProject.model;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

public class RentalVehicles {

    private ArrayBlockingQueue<RentalVehicle> rentalVehicleQueue;

    public RentalVehicles(int capacity) {
        this.rentalVehicleQueue = new ArrayBlockingQueue<>(capacity);
    }

    //Voeg een RentalVehicle-object toe aan de set :
    public boolean add(RentalVehicle rentalVehicle) {
        return rentalVehicleQueue.add(rentalVehicle);
    }

    //Verwijder een RentalVehicle-object op basis van het unieke attrubuut vehicleRegNr
    public boolean remove(String vehicleRegNr) {
        Iterator<RentalVehicle> iterator = rentalVehicleQueue.iterator();
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
        for (RentalVehicle vehicle : rentalVehicleQueue) {
            if (vehicle.getVehicleRegNr().equals(vehicleRegNr)) {
                return vehicle;
            }
        }
        return null;
    }

    // Gesorteerde lijst van RentalVehicle-objecten op naam
    public List<RentalVehicle> sortedOnName(){
        List<RentalVehicle> sortedList = new ArrayList<>(rentalVehicleQueue);
        sortedList.sort(Comparator.comparing(RentalVehicle:: getModel));
        return sortedList;
    }

    // Gesorteerde lijst van RentalVehicle-objecten op geboortedatum
    public List<RentalVehicle> sortedOnBirth() {
        List<RentalVehicle> sortedList = new ArrayList<>(rentalVehicleQueue);
        sortedList.sort(Comparator.comparing(RentalVehicle::getManufactureDate));
        return sortedList;
    }

    // Gesorteerde lijst van RentalVehicle-objecten op aantal slachtoffers
    public List<RentalVehicle> sortedOnAantalPersons() {
        List<RentalVehicle> sortedList = new ArrayList<>(rentalVehicleQueue);
        sortedList.sort(Comparator.comparingInt(RentalVehicle::getAntalPersons));
        return sortedList;
    }

    // Geeft het aantal objecten in de set terug
    public int getSize() {
        return rentalVehicleQueue.size();
    }

}

