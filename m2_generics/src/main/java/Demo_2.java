import be.kdg.rentalVehicleProject.model.PriorityQueue;
import be.kdg.rentalVehicleProject.data.Data;
import be.kdg.rentalVehicleProject.model.RentalVehicle;

import java.util.List;
import java.util.Random;

public class Demo_2 {
    public static void main(String[] args) {
        var myQueue = new PriorityQueue<String>();
        myQueue.enqueue("Tokio", 2);
        myQueue.enqueue("Denver", 5);
        myQueue.enqueue("Rio", 2);
        myQueue.enqueue("Oslo", 3);
        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(myQueue.toString());
        System.out.println("aantal: " + myQueue.getSize());
        System.out.println("positie van Tokio: " + myQueue.search("Tokio"));
        System.out.println("positie van Nairobi: " + myQueue.search("Nairobi"));
        for (int i = 0; i < 4; i++) { // Dequeue all four elements
            System.out.println("Dequeue: " + myQueue.dequeue());
        }
        System.out.println("Size na dequeue: " + myQueue.getSize());

        var rentalVehicleQueue = new PriorityQueue<RentalVehicle>();

        // Get the list of RentalVehicle objects from the Data class
        List<RentalVehicle> rentalVehicles = Data.getData();

        // Enqueue each RentalVehicle object with a random priority (1 to 5)
        Random random = new Random();
        for (var rentalVehicle : rentalVehicles) {
            int priority = random.nextInt(5) + 1; // Random priority between 1 to 5
            rentalVehicleQueue.enqueue(rentalVehicle, priority);
        }

        // Print the contents of the PriorityQueue
        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(rentalVehicleQueue.toString());
        System.out.println("Aantal: " + rentalVehicleQueue.getSize());
        System.out.println("Positie van een willekeurige RentalVehicle: " + rentalVehicleQueue.search(rentalVehicles.get(0)));

        //You can use the var keyword for local variables with an initializer, and the type will be inferred by the compiler.
        //We can not use "var" for method parameters or method return types.
        //So With these changes the output still the same.
    }
}
