package be.kdg.rentalVehicleProject.model;

import java.time.LocalDate;
import java.util.Random;

public class RentalVehicleFactory {

    private RentalVehicleFactory() {

    }

    public static RentalVehicle newEmptyRentalVehicle() {
        return new RentalVehicle();
    }

    public static RentalVehicle newFilledRentalvehicle(String model, double rentalPricePerDay, int antalPersons,
                                                       VehicleType vehicleType, LocalDate manufactureDate, String vehicleRegNr) {

        return new RentalVehicle(model, rentalPricePerDay, antalPersons, vehicleType, manufactureDate, vehicleRegNr);
    }


    public static RentalVehicle newRandomRentalVehicle() {
        Random random = new Random();

        return new RentalVehicle(
        // Generate random values for the vehicle
        generateString(6, 2, true, 4, 15    ),
        generateRandomDouble(40, 170),
        generateRandomInt(1,8),
        VehicleType.values()[generateRandomInt(0,1)],
        generateRandomDate(1900, 2000), // between 1900 and 2000
        generateString(8, 1, true, 4, 8));
    }

    private static String generateString(int maxWordLength, int wordCount, boolean camelCase, int minLength, int maxLength) {
        StringBuilder sb = new StringBuilder();

        // Generate a random string length within the specified range
        int wordLength = (int) (Math.random() * (maxLength - minLength + 1)) + minLength;

        // Generate the letters
        for (int j = 0; j < wordLength; j++) {
            // Capitalize first letter if needed
            if (j == 0 && camelCase) {
                sb.append((char) (Math.random() * 26 + 65));
            } else {
                // Decide if it's a vowel or a consonant
                if (Math.random() < 0.33) {
                    sb.append((char) (Math.random() * 5 + 97));
                } else {
                    sb.append((char) (Math.random() * 21 + 98));
                }
            }
        }

        return sb.toString();
    }


    private static LocalDate generateRandomDate(int minYear, int maxYear){
        return LocalDate.of(minYear + (int) (Math.random() * ((maxYear - minYear) + 1)),
                1 + (int)(Math.random() * ((12 - 1) + 1)), 1 + (int)(Math.random() * ((28 - 1) + 1)));
    }

    private static int generateRandomInt(int min , int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    private static double generateRandomDouble(int min, int max){
        return min + (double)(Math.random() * ((max - min) + 1));
    }
}
