package be.kdg.rentalVehicleProject.persist;


import be.kdg.rentalVehicleProject.data.Data;
import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicles;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RentalVehicleDbDaoTest {

    private static RentalVehicleDbDao database;

    @BeforeAll
    static void beforeAll() {

        database = new RentalVehicleDbDao("db/rentalVehicles");
    }

    @AfterAll
    static void afterAll() {database.close();}

    @BeforeEach
    void setUp(){
        Data.getData().forEach(database::insert);
    }
    @AfterEach
    void afterEach(){
        database.delete("*");
    }

    @Test
    public void testInsert() {
        assertTrue(database.insert(Data.getData().get(19)), "Database could not insert items");
        //assertEquals(Data.getData().size(), database.sortedOnModel().size(), "Het aantal rijen klopt niet");
    }

    @Test
    public void testRetrieve() {
        assertNotNull(database.retrieve("Ford Focus"));
    }

    @Test
    public void testUpdate() {

        RentalVehicle rentalVehicleToUpdate = database.retrieve("Ford Focus");
        assertNotNull(rentalVehicleToUpdate, "vehicle is niet gevonden!");

        rentalVehicleToUpdate.setModel("Opel Corsa");
        database.update(rentalVehicleToUpdate);

        RentalVehicle updatedRentalVehicle = database.retrieve("Opel Corsa");
        assertEquals(rentalVehicleToUpdate, updatedRentalVehicle, "Vehicle niet geupdated");
    }

     @Test
    public void testDelete() {
         int sizeBeforeDelete = database.sortedOnModel().size();
         database.delete("Ford Focus");
         int sizeAfterDelete = database.sortedOnModel().size();

         assertEquals(sizeBeforeDelete - 1, sizeAfterDelete, "Vehicle niet verwijderd");
         assertFalse(database.delete("Ford Focus"), "Oei, de vehicle kan 2keer verwijderd worden");
    }

    @Test
    public void testSort(){
        // Sorted from data class
        RentalVehicles rentalVehicles = new RentalVehicles();

        Data.getData().forEach(rentalVehicles::add);

        List<RentalVehicle> sortedOnModelMemory = rentalVehicles.sortedOnModel();

        // Now, get database sorted list
        List<RentalVehicle> sortedOnModelDb = database.sortedOnModel();

        assertAll(
                () -> assertEquals(sortedOnModelMemory.get(1), sortedOnModelDb.get(1), "De sortering klopt niet"),
                () -> assertEquals(sortedOnModelMemory.get(2), sortedOnModelDb.get(2), "De sortering klopt niet"),
                () -> assertEquals(sortedOnModelMemory.get(3), sortedOnModelDb.get(3), "De sortering klopt niet")
        );
    }
}