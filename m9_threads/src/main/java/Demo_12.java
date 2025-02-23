import be.kdg.rentalVehicleProject.model.RentalVehicleFactory;
import be.kdg.rentalVehicleProject.model.RentalVehicles;

import java.util.stream.Stream;

public class Demo_12 {
    public static void main(String[] args) throws Exception{

        RentalVehicles rentalVehicles = new RentalVehicles(10000);

        Runnable r = () -> Stream.generate(RentalVehicleFactory::newRandomRentalVehicle).limit(5000).forEach(rentalVehicles::add);

        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("\n\nAfter insertion by 2 threads met elk 5000 objects: vehicles = " + rentalVehicles.getSize());
    }
}
