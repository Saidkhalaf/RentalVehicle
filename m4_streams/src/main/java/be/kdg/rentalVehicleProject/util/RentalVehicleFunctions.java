package be.kdg.rentalVehicleProject.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class RentalVehicleFunctions {

    public static <T> List<T> filteredList(List<T> rentalVehiclesList, Predicate<T> predicate){

        /* List<T> filteredVehicles = new ArrayList<>();
        for (T vehicle : rentalVehiclesList){
            if (predicate.test(vehicle)){
                filteredVehicles.add(vehicle);
            }
        } */

        return rentalVehiclesList.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T> Double average(List<T> rentalVehicleList, ToDoubleFunction<T> mapper){
        /*
        double sum=0;
        for (T t : rentalVehicleList){
            sum += mapper.applyAsDouble(t);
        }
        return sum /rentalVehicleList.size();
         */

        return rentalVehicleList.stream()
                .mapToDouble(mapper)
                .average()
                .orElse(0.0);
    }

    public static<T> long countIf(List<T>rentalVehicleList, Predicate<T> predicate){

        /*
        long sum =0;
        for (T t : rentalVehicleList){
            if (predicate.test(t)){
                sum ++;
            }
        }
        return sum;
         */
        return rentalVehicleList
                .stream()
                .filter(predicate)
                .count();
    }







}
