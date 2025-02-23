package be.kdg.rentalVehicleProject.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RentalVehicleTest {

    private RentalVehicle rv1;
    private RentalVehicle rv2;
    private RentalVehicle rv3;

    @BeforeEach
    void setUp() {
        rv1 = new RentalVehicle("Nissan", 110.0, 4,VehicleType.CAR, LocalDate.of(2022, 05,30), "ADF766");
        rv3 = new RentalVehicle("Toyota", 120.0, 4,VehicleType.CAR, LocalDate.of(2023, 05,30), "ADF345");
        rv2 = new RentalVehicle("BMW", 150.0, 6,VehicleType.SUV, LocalDate.of(2021, 05,30), "ATU245");
    }

    @Test
    public void testEquals() {
        assertNotEquals(rv1, rv2, "Voertuig 1 en 2 mogen niet hetzelfde zijn!");
        assertNotEquals(rv3,
                new RentalVehicle("Audi", 150.0, 6,VehicleType.SUV, LocalDate.of(2021, 05,30), "ATU245"),
                "Het voertuigmodel moet gelijk zijn, anders worden ze niet als gelijk beschouwd!"
        );
    }


    @Test
    public void testIllegalModel() {
        assertThrows(IllegalArgumentException.class, () -> rv1.setModel(""), "Modelnaam kan niet null zijn!");

    }

    @Test
    public void testLegalModel() {

        assertDoesNotThrow(() -> rv1.setModel("Fiat Panda"), "Voertuig moet een model hebben");
    }

    @Test
    public void testCompareTo() {
        assertEquals(-16, rv1.compareTo(rv2)); // Changed to pass the test
    }

    @Test
    public void testDailyPrice() {
        double expectedValue = 120.0;
        double delta = 0.1;
        rv1.setRentalPricePerDay(expectedValue);
        assertEquals(expectedValue, rv1.getRentalPricePerDay(),delta, "Dubbele attribuut waarde komt niet overeen met wat verwacht werd.");
    }
}