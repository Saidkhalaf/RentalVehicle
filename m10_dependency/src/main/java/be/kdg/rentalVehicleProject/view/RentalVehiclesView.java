package be.kdg.rentalVehicleProject.view;

import be.kdg.rentalVehicleProject.model.RentalVehicle;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.logging.Logger;


public class RentalVehiclesView extends BorderPane {

    private static final Logger logger = Logger.getLogger(RentalVehiclesView.class.getName());

    private javafx.scene.control.TableView<RentalVehicle> tableView;

    private TextField modelInput;
    private TextField aantalPersonsInput;
    private DatePicker manufactureDateInput;
    private Button save;

    public RentalVehiclesView() {
        logger.info("Constructing RentalVehicleView");
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes(){
        tableView = new TableView<>();
        modelInput = new TextField();
        aantalPersonsInput = new TextField();
        manufactureDateInput = new DatePicker();
        save = new Button("Save");
    }

    private void layoutNodes(){
        super.setCenter(tableView);
        BorderPane.setMargin(tableView, new Insets(10));

        save.setMinWidth(Button.USE_PREF_SIZE);
        HBox hBox = new HBox(modelInput, aantalPersonsInput, manufactureDateInput, save);
        hBox.setSpacing(10);
        super.setBottom(hBox);
        BorderPane.setMargin(hBox, new Insets(10));

        modelInput.setPromptText("Model");
        aantalPersonsInput.setPromptText("Aantal Persons");
        manufactureDateInput.setPromptText("Manufacture Date");

        TableColumn<RentalVehicle, String> col1 = new TableColumn<>("Model");
        col1.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<RentalVehicle, String> col2 = new TableColumn<>("Aantal Persons");
        col2.setCellValueFactory(new PropertyValueFactory<>("antalPersons"));

        TableColumn<RentalVehicle, LocalDate> col3 = new TableColumn<>("Manufacture Date");
        col3.setCellValueFactory(new PropertyValueFactory<>("manufactureDate"));

        tableView.getColumns().addAll(col1, col2, col3);
    }

    protected TableView<RentalVehicle> getTableView() {return tableView;}

    protected TextField getModelInput() {return modelInput;}

    protected TextField getAantalPersonsInput() {return aantalPersonsInput;}

    protected DatePicker getManufactureDateInput() {return manufactureDateInput;}

    protected Button getSave() {return save;}
}
