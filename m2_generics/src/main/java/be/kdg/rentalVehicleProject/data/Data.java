package be.kdg.rentalVehicleProject.data;

import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.VehicleType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<RentalVehicle> getData(){
        List<RentalVehicle> rentalVehicles = new ArrayList<>();

        // Add 15 RentalVehicle objects with realistic data
        rentalVehicles.add(new RentalVehicle("Toyota Camry", 80.0, 4, VehicleType.CAR, LocalDate.of(2022, 01,01), "ABC123"));
        rentalVehicles.add(new RentalVehicle("Honda CR-V", 100.0, 5, VehicleType.SUV, LocalDate.of(2021, 12, 15), "XYZ456"));
        rentalVehicles.add(new RentalVehicle("Ford Focus", 70.0, 4, VehicleType.CAR, LocalDate.of(2020, 2, 10), "DEF789"));
        rentalVehicles.add(new RentalVehicle("Chevrolet Silverado", 150.0, 6, VehicleType.PICKUP_TRUCK, LocalDate.of(2019, 11, 5), "GHI101"));
        rentalVehicles.add(new RentalVehicle("Volkswagen Golf", 75.0, 4, VehicleType.CAR, LocalDate.of(2022, 3, 20), "JKL202"));
        rentalVehicles.add(new RentalVehicle("Jeep Wrangler", 120.0, 5, VehicleType.SUV, LocalDate.of(2021, 10, 8), "MNO303"));
        rentalVehicles.add(new RentalVehicle("Toyota Prius", 60.0, 4, VehicleType.HYBRID_CAR, LocalDate.of(2022, 4, 15), "PQR404"));
        rentalVehicles.add(new RentalVehicle("Ford Mustang", 130.0, 2, VehicleType.SPORTS_CAR, LocalDate.of(2021, 9, 25), "STU505"));
        rentalVehicles.add(new RentalVehicle("Nissan Rogue", 95.0, 5, VehicleType.SUV, LocalDate.of(2023, 5, 12), "VWX606"));
        rentalVehicles.add(new RentalVehicle("Honda Civic", 70.0, 4, VehicleType.CAR, LocalDate.of(2021, 8, 14), "YZA707"));
        rentalVehicles.add(new RentalVehicle("Tesla Model 3", 150.0, 4, VehicleType.ELECTRIC_VEHICLE, LocalDate.of(2021, 7, 9), "CDC909"));
        rentalVehicles.add(new RentalVehicle("Audi A4", 90.0, 4, VehicleType.LUXURY_CAR, LocalDate.of(2018, 6, 4), "EFE111"));
        rentalVehicles.add(new RentalVehicle("Mercedes-Benz E-Class", 120.0, 4, VehicleType.LUXURY_CAR, LocalDate.of(2022, 9, 10), "HHH313"));
        rentalVehicles.add(new RentalVehicle("BMW X5", 110.0, 5, VehicleType.SUV, LocalDate.of(2022, 8, 18), "III424"));
        rentalVehicles.add(new RentalVehicle("Audi A3", 90.0, 4, VehicleType.LUXURY_CAR, LocalDate.of(2022, 7, 25), "JJJ535"));
        rentalVehicles.add(new RentalVehicle("Toyota RAV4", 95.0, 5, VehicleType.SUV, LocalDate.of(2022, 6, 12), "KKK646"));
        rentalVehicles.add(new RentalVehicle("Ford Escape", 85.0, 5, VehicleType.SUV, LocalDate.of(2022, 5, 29), "LLL757"));
        rentalVehicles.add(new RentalVehicle("Chevrolet Equinox", 95.0, 5, VehicleType.SUV, LocalDate.of(2022, 3, 14), "NNN979"));
        rentalVehicles.add(new RentalVehicle("Volkswagen Passat", 80.0, 4, VehicleType.SEDAN, LocalDate.of(2022, 2, 22), "OOO090"));
        rentalVehicles.add(new RentalVehicle("Nissan Altima", 75.0, 4, VehicleType.SEDAN, LocalDate.of(2022, 1, 30), "PPP101"));

        return rentalVehicles;
    }
}
