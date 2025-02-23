package be.kdg.rentalVehicleProject.parsing;

import be.kdg.rentalVehicleProject.adapter.LocalDateGsonAdapter;
import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicles;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class RentalVehiclesGsonParser {
    public static void writeJson(RentalVehicles rentalVehicles, String fileName){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter()).create();

        String rentalVehiclesGson = gson.toJson(rentalVehicles);
        System.out.println(rentalVehiclesGson);

        try(PrintWriter jsonWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName)))){
            jsonWriter.write(rentalVehiclesGson);

            System.out.println("Json file saved");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static RentalVehicles readJson(String fileName){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter()).create();

        List<RentalVehicle> rentalVehicleList = null;

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            RentalVehicles rentalVehicles = gson.fromJson(reader, RentalVehicles.class);

            return rentalVehicles;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
