import be.kdg.rentalVehicleProject.data.Data;
import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicles;
import be.kdg.rentalVehicleProject.model.VehicleType;
import be.kdg.rentalVehicleProject.util.RentalVehicleFunctions;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Demo_4 {
    public static void main(String[] args) {
        RentalVehicles rentalVehicles = new RentalVehicles();
        for (RentalVehicle vehicle : Data.getData()) {
            rentalVehicles.addVehicle(vehicle);
        }

        System.out.println("\n RentalVehicles gesorteerd op Model:");
        rentalVehicles.sortedBy(RentalVehicle::getModel).forEach(System.out::println);

        System.out.println("\n Rental Vehicles gesorteerd op rental prijs per day:");
        rentalVehicles.sortedBy(RentalVehicle::getRentalPricePerDay).forEach(System.out::println);

        System.out.println("\n Rental Vehicles gesorteerd op Manufacture Date :");
        rentalVehicles.sortedBy(RentalVehicle::getManufactureDate).forEach(System.out::println);


        Data data = new Data();
        List<RentalVehicle> vehiclesList = data.getData();

        // b. Roep drie keer de filtermethode op en geef telkens een ander Predicate mee.
        // c. Geef het Predicate bij de oproep mee als een Lambda-expression.

        List<RentalVehicle> filteredVehiclesByModel = RentalVehicleFunctions.filteredList(vehiclesList, vehicle -> vehicle.getModel().equals("Audi A4"));
        List<RentalVehicle> filteredVehiclesByPrice = RentalVehicleFunctions.filteredList(vehiclesList, vehicle -> vehicle.getRentalPricePerDay() <= 70);
        List<RentalVehicle> filteredVehiclesByManufactureDate = RentalVehicleFunctions.filteredList(vehiclesList, vehicle -> vehicle.getManufactureDate().getYear() > 2022);


        //d. Controleer de afdruk.
        System.out.println("Toepassing 3 keer FilteredList met telkens een ander Predicate:\n");
        System.out.println("Vehicle Model 'Audi A4' :");
        System.out.println(filteredVehiclesByModel);
        System.out.println();
        System.out.println("<<<<<<<<<<<<<< Filtered Vehicles op prijs < 80 >>>>>>>>>>>>>>>>>>>>");
        System.out.println(filteredVehiclesByPrice);
        System.out.println();
        System.out.println("<<<<<<<<<< Filtered Vehicles op ManufactureDate >>>>>>>>>>>>>>");
        System.out.println(filteredVehiclesByManufactureDate);


        System.out.println();
        System.out.println("<<<<<<<<<<<<<<< Average Data list >>>>>>>>>>>>>>>>>");
        List<RentalVehicle> rentalVehicleList = Data.getData();

        System.out.printf("Average number of prices: %.1f Euro\n",
                RentalVehicleFunctions.average(rentalVehicleList, RentalVehicle::getRentalPricePerDay));

        System.out.println();
        System.out.println("<<<<<<<<<<<<<<<Predicate>>>>>>>>>>>>>>>>>");
        long carVehiclesCount =RentalVehicleFunctions.countIf(rentalVehicleList, vehicle -> vehicle.getVehicleType() == VehicleType.CAR);
        System.out.println("Aantal Car Vehicles :" + carVehiclesCount);

        System.out.printf("Number of Vehicles with Rental price > 100 Euro per day :%d\n",
                RentalVehicleFunctions.countIf(rentalVehicleList, vehicle -> vehicle.getRentalPricePerDay() > 100));


        System.out.println("\n<<<<<<<<<<<<<<<<<<<STREAMS>>>>>>>>>>>>>>>>>>");
        List<RentalVehicle> rentalVehicleList1 = Data.getData();

        long count =rentalVehicleList1.stream().filter(vehicle -> vehicle.getManufactureDate()
                .isAfter(LocalDate.of(2022, 07, 10))).count();
        System.out.printf("Aantal Vehicles after '2022-07-10' is : %d\n", count);

        System.out.println("<<<<<<<< Sorteer op 2 attributen en afdruking >>>>>>>>>");
        System.out.println("Alle vehicles gesorteerd op rentalPrice en vervolgens op model:");
        rentalVehicleList1.stream().sorted(Comparator.comparingDouble(RentalVehicle::getRentalPricePerDay)
                .thenComparing(RentalVehicle::getModel)).forEach(System.out::println);

        String models =rentalVehicleList1.stream().map(vehicle -> vehicle.getModel().toUpperCase())
                .distinct().sorted(Comparator.reverseOrder()).collect(Collectors.joining(", "));
        System.out.println();
        System.out.printf("Alle Vehicles in hoofdletters, omgekeerd gesorteerd en zonder dubbels:\n%s", models);


        System.out.println("\n\n<<<<<<<<<<<< Optional Vehicle >>>>>>>>>>>>");
        System.out.println("Een willekeurige vehicle met meer dan 4 personen capaciteit :");
        Optional<RentalVehicle> optionalRentalVehicle = rentalVehicleList1.stream().filter(vehicle -> vehicle.getAntalPersons() > 4).findAny();
        System.out.println(optionalRentalVehicle);

        System.out.println("\n<<<<<<<<<<< Kampioenen >>>>>>>>>>>");
        System.out.println("Champion based on the highest price : ");
        RentalVehicle championByAantalPersons = rentalVehicleList1.stream()
                        .max(Comparator.comparingDouble(RentalVehicle::getAntalPersons))
                                .orElse(null);
        System.out.println(championByAantalPersons.getModel());

        System.out.println("Champion based on the latest manufacture date");
        RentalVehicle championByManDate = rentalVehicleList1.stream()
                        .max(Comparator.comparing(RentalVehicle::getManufactureDate))
                                .orElse(null);
        System.out.println(championByManDate.getModel());

        System.out.println("\n<<<<<<<<<<<< Sorted >>>>>>>>>>>>>");
        List<String> sortedToyotaVecles = rentalVehicleList1.stream().filter(vehicle -> vehicle.getModel()
                        .startsWith("Toyota"))
                        .map(RentalVehicle::getModel)
                        .sorted()
                        .collect(Collectors.toList());

        System.out.println("List of Toyota Vehicles : ");
        System.out.println(sortedToyotaVecles);


        System.out.println("\nSublist of Vehicles manufactured After 2022:");
        rentalVehicleList1.stream().filter( vehicle -> vehicle.getManufactureDate().isAfter(LocalDate.of(2022, 01, 01)))
                .forEach(System.out::println);

        System.out.println("\nSublist of Vehicles manufactured before 2022:");
        rentalVehicleList1.stream().filter(vehicle -> vehicle.getManufactureDate().isBefore(LocalDate.of(2022, 01,01)))
                .forEach(System.out::println);


        System.out.println("\ngroup all objects into certain categories :");
        Map<String, List<RentalVehicle>> vehiclesByRegNr = rentalVehicleList1.stream()
                .collect(Collectors.groupingBy(RentalVehicle::getVehicleRegNr));

        vehiclesByRegNr.forEach((k, v) -> {
            String vehicleModels = v.stream()
                    .sorted(Comparator.comparing(RentalVehicle::getModel))
                    .map(RentalVehicle::getModel)
                    .collect(Collectors.joining(", "));
                    System.out.println(k + " : " + vehicleModels);
        });

    }
}
