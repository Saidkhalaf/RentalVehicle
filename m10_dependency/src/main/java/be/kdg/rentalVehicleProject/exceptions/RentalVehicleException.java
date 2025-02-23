package be.kdg.rentalVehicleProject.exceptions;

public class RentalVehicleException extends RuntimeException{

    public RentalVehicleException() {}

    public RentalVehicleException(String message) {super(message);}

    public RentalVehicleException(String message, Throwable cause) {super(message, cause);}

    public RentalVehicleException(Throwable cause) {super(cause);}

    public RentalVehicleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
