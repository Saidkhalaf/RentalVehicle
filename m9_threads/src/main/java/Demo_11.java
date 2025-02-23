import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.VehicleType;
import be.kdg.rentalVehicleProject.threading.RentalVehicleCallable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo_11 {
    private static int TESTCOUNT = 0;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Long> runTimes = new ArrayList<>();

        //Pool maken
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (TESTCOUNT = 0; TESTCOUNT < 100; TESTCOUNT++){
            RentalVehicleCallable callable1 = new RentalVehicleCallable(r -> r.getVehicleType().equals(VehicleType.CAR));
            RentalVehicleCallable callable2 = new RentalVehicleCallable(r -> r.getManufactureDate().isAfter(LocalDate.of(2023,01,01)));
            RentalVehicleCallable callable3 = new RentalVehicleCallable(r -> r.getRentalPricePerDay() > 120.0);

            //Lists that you will keep responses
            List<Future<List<RentalVehicle>>> futureList = new ArrayList<>();

            // Add our "jobs" to the pool
            long timeStart = System.currentTimeMillis();
            futureList.add(pool.submit(callable1));
            futureList.add(pool.submit(callable2));
            futureList.add(pool.submit(callable3));

            // Wait for their values to be available
            for (var callableResult : futureList) {
                callableResult.get();
            }

            long timeEnd = System.currentTimeMillis();

            runTimes.add(timeEnd - timeStart);
        }

        System.out.printf("3 Futures verzamelen elk 1000 vehicles (gemiddelde uit 100 runs): %.2f ms",
                runTimes.stream().mapToDouble(Long::doubleValue).average().getAsDouble());

        pool.shutdown();

    }
}
