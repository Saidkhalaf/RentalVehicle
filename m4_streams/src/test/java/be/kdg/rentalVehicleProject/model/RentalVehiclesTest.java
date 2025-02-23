package be.kdg.rentalVehicleProject.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.shadow.com.univocity.parsers.common.NormalizedString.toArray;

class RentalVehiclesTest {
    private RentalVehicle rv1, rv2, rv3, rv4, rv5;
    private RentalVehicles rentalVehicles;

    @BeforeEach
    public void setUp() {
        rentalVehicles = new RentalVehicles();
        rv1 = new RentalVehicle("Nissan", 110.0, 4,VehicleType.CAR, LocalDate.of(2022, 05,30), "ADF766");
        rv3 = new RentalVehicle("Toyota", 120.0, 4,VehicleType.CAR, LocalDate.of(2023, 05,30), "ADF345");
        rv2 = new RentalVehicle("BMW", 150.0, 6,VehicleType.SUV, LocalDate.of(2021, 05,15), "ATU245");
        rv4 = new RentalVehicle("Fiat", 160.0, 4,VehicleType.CAR, LocalDate.of(2020, 07,10), "CDF345");
        rv5 = new RentalVehicle("Opel Corsa", 170.0, 4,VehicleType.CAR, LocalDate.of(2021, 02,20), "ARE545");
        rentalVehicles.addVehicle(rv1);
        rentalVehicles.addVehicle(rv2);
        rentalVehicles.addVehicle(rv3);
        rentalVehicles.addVehicle(rv4);
        rentalVehicles.addVehicle(rv5);

    }

    @Test
    public void testAdd() {
        RentalVehicle rv = new RentalVehicle("Renault", 115.0, 6,
                VehicleType.SUV, LocalDate.of(2023, 02,20), "FGR445");

        assertTrue(rentalVehicles.addVehicle(rv),
                "De toegevoegde vehicle moet uniek zijn");
    }


    @Test
    public void testRemove() {
        int initialSize = rentalVehicles.getSize();

        //Verwijder een bestaand object
        boolean removed = rentalVehicles.remove(rv3.getVehicleRegNr());
        int sizeAfterRemove = rentalVehicles.getSize();

        assertTrue(removed, "Het object moet succesvol verwijderd zijn");
        assertEquals(initialSize - 1, sizeAfterRemove, "De grootte moet verminderd zijn na verwijdering");


        //Verwijder een niet-bestaand object
        RentalVehicle nonExistingVehicle = new RentalVehicle("Non-existing", 0.0, 0,
                VehicleType.CAR, LocalDate.of(2022, 01, 01), "ABC123");
        removed = rentalVehicles.remove(nonExistingVehicle.getVehicleRegNr());
        int sizeAfterNonExistingRemoval = rentalVehicles.getSize();

        assertFalse(removed, "Het niet-bestaande object kan niet verwijderd worden");
        assertEquals(sizeAfterRemove, sizeAfterNonExistingRemoval,
                "De grootte moet hetzelfde blijven na poging tot verwijdering van niet-bestaand object");
    }

    @Test
    public void testSort() {
        List<RentalVehicle> rentalVehicleList =rentalVehicles.sortedBy(RentalVehicle::getManufactureDate);
        assertAll(
                () ->assertEquals(rv4, rentalVehicleList.get(0)),
                () -> assertEquals(rv5, rentalVehicleList.get(1)),
                () -> assertEquals(rv2, rentalVehicleList.get(2)),
                () -> assertEquals(rv1, rentalVehicleList.get(3)),
                () -> assertEquals(rv3, rentalVehicleList.get(4))
        );
    }

    @Test
    public void testSort2() {

        List<RentalVehicle> rentalVehicleList = rentalVehicles.sortedBy(RentalVehicle::getManufactureDate);
        assertArrayEquals(new RentalVehicle[]{rv4,rv5,rv2,rv1,rv3}, rentalVehicleList.toArray(),
                "First 5 vehicles should be in the expected order sorted by ManufactureDate");
    }

}