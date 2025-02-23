package be.kdg.rentalVehicleProject.persist;

import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicles;

import java.io.*;

public class RentalVehiclesSerializer {
    private final String FILENAME;

    public RentalVehiclesSerializer(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    public void serialize(RentalVehicles rentalVehicles) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("db/rentalVehicles.ser")
        );
        objectOutputStream.writeObject(rentalVehicles);
    }

    public RentalVehicles deserialize() throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream("db/rentalVehicles.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        RentalVehicles rentalVehicles =(RentalVehicles) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return rentalVehicles;
    }
}
