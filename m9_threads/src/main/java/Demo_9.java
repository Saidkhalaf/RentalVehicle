import be.kdg.rentalVehicleProject.model.VehicleType;
import be.kdg.rentalVehicleProject.threading.RentalVehicleRunnable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Demo_9 {

    private static int TESTCOUNT = 0 ;
    public static void main(String[] args) throws InterruptedException {
        List<Long> runTimes = new ArrayList<>();

        for (TESTCOUNT = 0; TESTCOUNT < 100; TESTCOUNT++) {
            RentalVehicleRunnable run1 = new RentalVehicleRunnable(r -> r.getVehicleType().equals(VehicleType.CAR));
            RentalVehicleRunnable run2 = new RentalVehicleRunnable(r -> r.getManufactureDate().isAfter(LocalDate.of(2023, 1, 1)));
            RentalVehicleRunnable run3 = new RentalVehicleRunnable(r -> r.getRentalPricePerDay() > 120.0);

            Thread thread1 = new Thread(run1);
            Thread thread2 = new Thread(run2);
            Thread thread3 = new Thread(run3);

            long timeStart = System.currentTimeMillis();
            thread1.start();
            thread2.start();
            thread3.start();

            thread1.join();
            thread2.join();
            thread3.join();
            long timeEnd = System.currentTimeMillis();

            run1.getRentalVehicleList().stream().limit(1).forEach(System.out::println);
            run2.getRentalVehicleList().stream().limit(1).forEach(System.out::println);
            run3.getRentalVehicleList().stream().limit(5).forEach(System.out::println);

            runTimes.add(timeEnd - timeStart);
        }

        System.out.printf("3 threads verzamelen elk 1000 vehicles (gemiddelde uit 100 runs): %.2f ms",
                runTimes.stream().mapToDouble(Long::doubleValue).average().getAsDouble());



    }
}
