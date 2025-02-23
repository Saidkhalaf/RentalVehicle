package be.kdg.rentalVehicleProject.view;

import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.service.RentalVehiclesService;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

import java.util.logging.Logger;

public class RentalVehiclesPresenter {
    private static final Logger logger =Logger.getLogger(RentalVehiclesPresenter.class.getName());

    RentalVehiclesView view;
    RentalVehiclesService service;

    public RentalVehiclesPresenter(RentalVehiclesView view, RentalVehiclesService service) {
        this.view = view;
        this.service = service;
        this.addEventHandlers();
        this.showData();
    }

    public void addEventHandlers(){
        view.getSave().setOnAction(event -> {
            try{
                service.addRentalVehicle(
                        new RentalVehicle(
                                view.getModelInput().getText(),
                                Integer.parseInt(view.getAantalPersonsInput().getText()),
                                view.getManufactureDateInput().getValue()
                        )
                );
                showData();

            }catch (Exception e){
                logger.warning("Failed to add rentalVehicle: " + e);
                new Alert(Alert.AlertType.ERROR, "Failed to add rentalVehicle:\n" + e.getMessage()).showAndWait();
            }
        });
    }

    public void showData(){
        try {
            view.getTableView().setItems(FXCollections.observableList(service.getAllRentalVehicles()));
        }catch (Exception e){
            logger.warning("Unable to load rentalVehicles: " + e);
            new Alert(Alert.AlertType.ERROR, "Unable to load rentalVehicles:\n" + e.getMessage()).showAndWait();
        }
    }
}
