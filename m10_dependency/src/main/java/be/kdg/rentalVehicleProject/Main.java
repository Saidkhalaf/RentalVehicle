package be.kdg.rentalVehicleProject;

import be.kdg.rentalVehicleProject.database.RentalVehicleDao;
import be.kdg.rentalVehicleProject.database.RentalVehicleDbDao;
import be.kdg.rentalVehicleProject.service.RentalVehiclesService;
import be.kdg.rentalVehicleProject.service.RentalVehiclesServiceImpl;
import be.kdg.rentalVehicleProject.view.RentalVehiclesPresenter;
import be.kdg.rentalVehicleProject.view.RentalVehiclesView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Application;
import java.util.logging.Logger;

public class Main extends Application {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        logger.info("Starting RentalVehicle Management System on thread: " + Thread.currentThread().getName());
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        RentalVehicleDao rentalVehicleDao = RentalVehicleDbDao.getInstance("db/rentalVehicles");
        RentalVehiclesService rentalVehiclesService = new RentalVehiclesServiceImpl(rentalVehicleDao);

        RentalVehiclesView rentalVehiclesView = new RentalVehiclesView();
        new RentalVehiclesPresenter(rentalVehiclesView, rentalVehiclesService);

        primaryStage.setTitle("RentalVehicle management system");
        primaryStage.setScene(new Scene(rentalVehiclesView));
        primaryStage.show();

    }
}
