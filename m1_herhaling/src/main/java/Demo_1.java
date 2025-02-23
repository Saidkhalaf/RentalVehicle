import be.kdg.rentalVehicleProject.data.Data;
import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicles;
import be.kdg.rentalVehicleProject.model.VehicleType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Demo_1 {
    public static void main(String[] args) {

        //Maak een instantie van de multiklasse
        RentalVehicles rentalVehicles = new RentalVehicles();

        // Haal een gevulde datalist op vanuit de Data-klasse
        List<RentalVehicle> dataList = Data.getData();


        // Voeg elk object uit de dataList toe aan de multiklasse
        for (RentalVehicle vehicle : dataList) {

            //Controleer of het voertuig al in de multiklasse zit
            if (rentalVehicles.search(vehicle.getVehicleRegNr()) == null) {
                //Voeg het voertuig toe als het nog niet aanwezig is
                rentalVehicles.add(vehicle);
            } else {
                System.out.println("Dit voertuig bestaat al in de multiklasse.");
            }
        }

        // Voeg een dubbel object toe (dit zou niet moeten lukken)
        RentalVehicle duplicateVehicle = new RentalVehicle("Toyota Camry", 80.0, 4, VehicleType.CAR, LocalDate.of(2022, 01, 01), "ABC123");
        if (rentalVehicles.add(duplicateVehicle)) {
            System.out.println("Dit voertuig is toegevoegd aan de multiklasse.");
        } else {
            System.out.println("Dit voertuig bestaat al in de multiklasse en kon niet worden toegevoegd.");
        }

        // Test de search methode
        String regNr = "ABC123"; //registration Nr
        RentalVehicle foundVehicle = rentalVehicles.search(regNr);
        if (foundVehicle != null) {
            System.out.println("Gevonden voertuig met registratienummer " + regNr + ": " + foundVehicle.getModel());
        } else {
            System.out.println("Voertuig met registratienummer " + regNr + " niet gevonden.");
        }

        // Test de remove methode
        String removeRegNr = "XYZ456";
        if (rentalVehicles.remove(removeRegNr)) {
            System.out.println("Voertuig met registratienummer " + removeRegNr + " is verwijderd.");
        } else {
            System.out.println("Voertuig met registratienummer " + removeRegNr + " niet gevonden of kon niet worden verwijderd.");
        }

        // Test de getSize methode
        int size = rentalVehicles.getSize();
        System.out.println("Aantal voertuigen in de multiklasse: " + size);


        // Roep de sortedOnXXX methoden aan om de gesorteerde lijsten te verkrijgen
        List<RentalVehicle> sortedByName = new ArrayList<>(rentalVehicles.sortedOnName()) ;
        List<RentalVehicle> sortedByBirth = new ArrayList<>(rentalVehicles.sortedOnBirth());
        List<RentalVehicle> sortedByAantalPersons = new ArrayList<>(rentalVehicles.sortedOnAantalPersons());

        // Druk de gesorteerde lijsten af
        System.out.println("Gesorteerd op naam:");
        sortedByName.forEach(System.out::println);

        System.out.println("\nGesorteerd op geboortedatum:");
        sortedByBirth.forEach(System.out::println);

        System.out.println("\nGesorteerd op Aantal Personon:");
        sortedByAantalPersons.forEach(System.out::println);


    }
}
