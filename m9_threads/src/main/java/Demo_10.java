import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicleFactory;
import be.kdg.rentalVehicleProject.model.VehicleType;
import be.kdg.rentalVehicleProject.threading.RentalVehicleAttacker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Demo_10 {
    public static void main(String[] args) throws InterruptedException {

        List<RentalVehicle> rentalVehicleList = new ArrayList<>();
        Stream.generate(RentalVehicleFactory::newRandomRentalVehicle).limit(1000).forEach(rentalVehicleList::add);

        RentalVehicleAttacker run1 = new RentalVehicleAttacker(rentalVehicleList, r -> r.getVehicleType().equals(VehicleType.CAR));
        RentalVehicleAttacker run2 = new RentalVehicleAttacker(rentalVehicleList, r -> r.getManufactureDate().isAfter(LocalDate.of(2023, 1, 1)));
        RentalVehicleAttacker run3 = new RentalVehicleAttacker(rentalVehicleList, r -> r.getRentalPricePerDay() > 120.0);

        Thread thread1 = new Thread(run1);
        Thread thread2 = new Thread(run2);
        Thread thread3 = new Thread(run3);

        // Starting threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Waiting for threads to complete
        thread1.join();
        thread2.join();
        thread3.join();

        // Synchronized block to avoid ConcurrentModificationException
        synchronized (rentalVehicleList){
        System.out.println("\n\nNa uitzuivering:");
        System.out.println("Vehicles met Vehicle type CAR                    : " + rentalVehicleList.stream().filter(r -> r.getVehicleType().equals(VehicleType.CAR)).count());
        System.out.println("Vehicles manufactured na 1/1/2023                : " + rentalVehicleList.stream().filter(r -> r.getManufactureDate().isAfter(LocalDate.of(2023, 1, 1))).count());
        System.out.println("Vehicles met DailyPrice meer dan 120 zijn        : " + rentalVehicleList.stream().filter(r -> r.getRentalPricePerDay() > 120.0).count());

        }

    }
}
