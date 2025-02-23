import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicles;
import be.kdg.rentalVehicleProject.model.Vehicle;
import be.kdg.rentalVehicleProject.reflection.ReflectionTools;

public class Demo_3 {
    public static void main(String[] args) {

        ReflectionTools.classAnalysis(Vehicle.class, RentalVehicle.class, RentalVehicles.class);

        try {
            Object result = ReflectionTools.runAnnotated(RentalVehicle.class);
            System.out.println("Aangemaakt object door runAnnotated:");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
