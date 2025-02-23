import be.kdg.rentalVehicleProject.data.Data;
import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicles;
import be.kdg.rentalVehicleProject.model.VehicleType;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.logging.LogManager;

public class Demo_5 {
    public static void main(String[] args) {


        try {
            InputStream inputStream = Demo_5.class.getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        RentalVehicles rentalVehicles = new RentalVehicles();
        Data.getData().forEach(rentalVehicles::add);
        rentalVehicles.remove("ABC123");


        RentalVehicle rv1 = new RentalVehicle("", 80.0, 4, VehicleType.CAR, LocalDate.of(2022, 01,01), "ABC123");
        RentalVehicle rv2 = new RentalVehicle("Toyota Camry", -1.0, 4, VehicleType.CAR, LocalDate.of(2022, 01,01), "ABC123");
        RentalVehicle rv3 = new RentalVehicle("Toyota Camry", 80.0, -4, VehicleType.CAR, LocalDate.of(2022, 01,01), "ABC123");
        RentalVehicle rv4 = new RentalVehicle("Toyota Camry", 80.0, 4, VehicleType.CAR, LocalDate.of(2024, 01,01), "ABC123");
        RentalVehicle rv5 = new RentalVehicle("Toyota Camry", 80.0, 4, VehicleType.CAR, LocalDate.of(2022, 01,01), "");
        RentalVehicle rv6 = new RentalVehicle("Toyota Camry", 80.0, 4, null, LocalDate.of(2022, 01,01), "ABC123");

    }
}
