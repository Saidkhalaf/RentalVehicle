package be.kdg.rentalVehicleProject.persist;

import be.kdg.rentalVehicleProject.data.Data;
import be.kdg.rentalVehicleProject.model.RentalVehicles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RentalVehiclesSerializerTest {

    private RentalVehiclesSerializer serializer;
    private RentalVehicles rentalVehicles;

    @BeforeEach
    void setUp() {
        rentalVehicles = new RentalVehicles();
        Data.getData().forEach(rentalVehicles::add);
        serializer = new RentalVehiclesSerializer("db/rentalVehicles.ser");
    }

    @Test
    public void testSerialize() {
        assertDoesNotThrow(() -> serializer.serialize(rentalVehicles), "Serialize exception has been thrown!");
    }

    @Test
    public void testDeserialize() {
        try {
            RentalVehicles rentalVehicles1 = serializer.deserialize();
            assertEquals (rentalVehicles, rentalVehicles1, "Vehicles are not the same!");
        } catch (IOException | ClassNotFoundException e) {
            fail(e);

        }
    }
}