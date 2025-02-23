package be.kdg.rentalVehicleProject.database;

import be.kdg.rentalVehicleProject.data.Data;
import be.kdg.rentalVehicleProject.exceptions.RentalVehicleException;
import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.VehicleType;
import org.w3c.dom.CDATASection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentalVehicleDbDao implements RentalVehicleDao{

    private Connection connection;
    private static RentalVehicleDbDao INSTANCE;
    private final Logger logger = Logger.getLogger("be.kdg.rentalVehicleProject.model.RentalVehicle");

    // Singleton stuff
    public static RentalVehicleDbDao getInstance(String databasePath){
        if (INSTANCE == null){
            INSTANCE = new RentalVehicleDbDao(databasePath);
        }
        return INSTANCE;
    }

    private RentalVehicleDbDao(String databasePath) {
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:"+databasePath, "sa", "");
            System.out.println("connected to database");
            createTable();
        } catch (SQLException e) {
            System.err.println("Failed to connect to datbase: " + databasePath);
            System.exit(1);
        }
    }

    public void close(){
        if (connection != null){
            try{
                connection.close();
            }catch (SQLException e){
                logger.severe("Unexpected error while closing database connection: " + e.getMessage());
                throw new RentalVehicleException(e);
            }
        }
    }
    private void createTable(){
        try {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "rentalVehiclesDb", null);
            if (!tables.next()) {
                System.out.println("Creating Table");
                Statement statement = connection.createStatement();
                statement.execute("DROP TABLE rentalVehiclesDb IF EXISTS");
                String query = "CREATE TABLE rentalVehiclesDb " +
                        "(id INTEGER NOT NULL IDENTITY," +
                        "model VARCHAR(50) NOT NULL ," +
                        "producedVehicles DECIMAL(12,00) NOT NULL ," +
                        "rentalPricePerDay DECIMAL(12,00) NOT NULL ," +
                        "aantalPersons INTEGER NOT NULL ," +
                        "vehicleType VARCHAR(25) NOT NULL ," +
                        "manufactureDate DATE NOT NULL ," +
                        "vehicleRegNr VARCHAR(25) NOT NULL ) ";
                statement.execute(query);
                statement.close();
                Data.getData().forEach(this::insert);
                logger.info("Tabel aangemaakt en gevuld met data");
            }
        }catch (SQLException e){
            logger.severe( "Onverwachte fout bij aanmaken tabel:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(RentalVehicle rentalVehicle) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO rentalVehiclesDb" +
                    "(model, producedVehicles, rentalPricePerDay, aantalPersons, vehicleType, manufactureDate,vehicleRegNr) " +
                    "VALUES (?,?, ?,?,?,?,?);");


            preparedStatement.setString(1, rentalVehicle.getModel());
            preparedStatement.setDouble(2, rentalVehicle.getProducedVehicles());
            preparedStatement.setDouble(3, rentalVehicle.getRentalPricePerDay());
            preparedStatement.setInt(4, rentalVehicle.getAntalPersons());
            preparedStatement.setString(5, rentalVehicle.getVehicleType().name());
            preparedStatement.setDate(6, Date.valueOf(rentalVehicle.getManufactureDate()));
            preparedStatement.setString(7, rentalVehicle.getVehicleRegNr());



            int rowsAffected = preparedStatement.executeUpdate();

            boolean result = rowsAffected == 1;
            preparedStatement.close();
            logger.info("RentalVehicle inserted: " + result);
            return result;
        }catch (SQLException e){
            logger.warning("Unexpected error while inserting into database: " + e.getMessage());
            throw new RentalVehicleException(e);
        }
    }

    @Override
    public RentalVehicle retrieve(String model) {
        ResultSet resultSet = null;
        RentalVehicle rentalVehicle = null;
        Statement statement = null;
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM rentalVehiclesDb WHERE model = '" + model + "'");
            while (resultSet.next()) {
                rentalVehicle = new RentalVehicle(
                        resultSet.getInt("id"),
                        resultSet.getString("model"),
                        resultSet.getDouble("producedVehicles"),
                        resultSet.getDouble("rentalPricePerDay"),
                        resultSet.getInt("aantalPersons"),
                        VehicleType.valueOf(resultSet.getString("vehicleType")),
                        resultSet.getDate("manufactureDate").toLocalDate(),
                        resultSet.getString("vehicleRegNr")
                );
            }
                statement.close();
                return rentalVehicle;
        }catch (SQLException e){
            logger.severe("Unexpected error while retrieving from database: " + e.getMessage());
            throw new RentalVehicleException(e);
        }
    }

    @Override
    public boolean update(RentalVehicle rentalVehicle) {
        try{
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE rentalVehiclesDb SET model = ?, producedVehicles = ?, rentalPricePerDay = ?, aantalPersons = ?, " +
                    "vehicleType = ?, manufactureDate = ?, vehicleRegNr = ? WHERE id = ?");

            preparedStatement.setString(1, rentalVehicle.getModel());
            preparedStatement.setDouble(2,rentalVehicle.getProducedVehicles());
            preparedStatement.setDouble(3,rentalVehicle.getRentalPricePerDay());
            preparedStatement.setInt(4, rentalVehicle.getAntalPersons());
            preparedStatement.setString(5, rentalVehicle.getVehicleType().name());
            preparedStatement.setDate(6, Date.valueOf(rentalVehicle.getManufactureDate()));
            preparedStatement.setString(7, rentalVehicle.getVehicleRegNr());
            preparedStatement.setInt(8, rentalVehicle.getId());



            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;

        }catch (SQLException e){
            logger.warning("Unexpected error while updating the database: " + e.getMessage());
            throw new RentalVehicleException(e);
        }
    }

    @Override
    public boolean delete(String model) {
        try{
            Statement statement = connection.createStatement();
            boolean success = false;
            PreparedStatement prep;
            if (model.equals("*")){
                success = statement.execute("DELETE FROM rentalVehiclesDb");
            }
            else{
                success = statement.execute("DELETE FROM rentalVehiclesDb WHERE model = '" + model + "'");
            }
            statement.close();
            return success;
        }catch (SQLException e){
            logger.warning("Unexpected error while deleting from database: " + e.getMessage());
            throw new RentalVehicleException(e);
        }
    }
    @Override
    public List<RentalVehicle> sortedOn(String query) {
        ResultSet resultSet = null;
        List<RentalVehicle> rentalVehicles = new ArrayList<>();
        Statement statement = null;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    RentalVehicle rentalVehicle = new RentalVehicle(
                            resultSet.getInt("id"),
                            resultSet.getString("model"),
                            resultSet.getDouble("producedVehicles"),
                            resultSet.getDouble("rentalPricePerDay"),
                            resultSet.getInt("aantalPersons"),
                            VehicleType.valueOf(resultSet.getString("vehicleType")),
                            resultSet.getDate("manufactureDate").toLocalDate(),
                            resultSet.getString("vehicleRegNr")
                    );
                    rentalVehicles.add(rentalVehicle);
                }
            System.out.println("opgehaalde data:");
                rentalVehicles.forEach(System.out::println);
            statement.close();
        } catch (SQLException e){
            logger.severe("Unexpected error while sorting the database: " + e.getMessage());
            throw new RentalVehicleException(e);
        }
        return rentalVehicles;
    }

    @Override
    public List<RentalVehicle> getAllRentalVehicles() {
        return sortedOn("SELECT * FROM rentalVehiclesDb");
    }

    public List<RentalVehicle> sortedOnModel(){
        String query = "SELECT * FROM rentalVehiclesDb ORDER BY model";
        return sortedOn(query);
    }

    public List<RentalVehicle> sortedOnManufactureDate() {

        String query = "SELECT * FROM rentalVehiclesDb ORDER BY manufactureDate";
        return sortedOn(query);
    }

    public List<RentalVehicle> sortedOnAantalPersons(){
        String query = "SELECT * FROM rentalVehiclesDb ORDER BY aantalPersons";
        return sortedOn(query);
    }

}
