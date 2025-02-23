package be.kdg.rentalVehicleProject.model;

import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentalVehicles {

    private Set<RentalVehicle> rentalVehicleSet;
    private final Logger logger = Logger.getLogger(RentalVehicles.class.getName());

    public RentalVehicles() {

        rentalVehicleSet = new TreeSet<>();
        logger.setLevel(Level.FINER);
    }


    //Voeg een RentalVehicle-object toe aan de set :
    public boolean add(RentalVehicle rentalVehicle) {

        logger.finer(rentalVehicle.getModel() + " successfully added!");
        return rentalVehicleSet.add(rentalVehicle);
    }

    //Verwijder een RentalVehicle-object op basis van het unieke attrubuut vehicleRegNr
    public boolean remove(String vehicleRegNr) {
        Iterator<RentalVehicle> iterator = rentalVehicleSet.iterator();
        while (iterator.hasNext()) {
            RentalVehicle vehicle = iterator.next();
            if (vehicle.getVehicleRegNr().equals(vehicleRegNr)) {
                logger.finer(vehicle.getVehicleRegNr() + " successfully removed!");
                return rentalVehicleSet.remove(vehicle);
            }
        }
        return false; //If no matching object is found, return false
    }

    // Zoek een RentalVehicle-object op basis van het unieke attribuut vehicleRegNr
    public RentalVehicle search(String vehicleRegNr) {
        for (RentalVehicle vehicle : rentalVehicleSet) {
            if (vehicle.getVehicleRegNr().equals(vehicleRegNr)) {
                return vehicle;
            }
        }
        return null;
    }

    // General sorting method
    public List<RentalVehicle> sortedBy(Function<RentalVehicle, Comparable> function){
        List<RentalVehicle> sortedList = new ArrayList<>(rentalVehicleSet);
        sortedList.sort(Comparator.comparing(function));
        return sortedList;
    }

    // Geeft het aantal objecten in de set terug
    public int getSize() {
        return rentalVehicleSet.size();
    }

    public Set<RentalVehicle> getRentalVehicleSet() {return rentalVehicleSet; }
}

