import be.kdg.rentalVehicleProject.kollections.Kollections;
import be.kdg.rentalVehicleProject.kollections.maps.HashMap;
import be.kdg.rentalVehicleProject.kollections.maps.ListMap;
import be.kdg.rentalVehicleProject.kollections.maps.Map;
import be.kdg.rentalVehicleProject.kollections.sets.ArraySet;
import be.kdg.rentalVehicleProject.kollections.sets.TreeSet;
import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicleFactory;

import be.kdg.rentalVehicleProject.kollections.lists.ArrayList;
import be.kdg.rentalVehicleProject.kollections.lists.LinkedList;
import be.kdg.rentalVehicleProject.kollections.lists.List;
import be.kdg.rentalVehicleProject.model.VehicleType;

import java.time.LocalDate;
import java.util.Random;
import be.kdg.rentalVehicleProject.kollections.sets.Set;

public class PerformanceTester {

    public static List<RentalVehicle> randomList(int n) {
        List<RentalVehicle> myList = new LinkedList<>();
        new Random().ints(n).forEach(i -> myList.add(RentalVehicleFactory.newRandomRentalVehicle()));
        return myList;
    }

    public static void compareArrayListAndLinkedList(int n) {
        //add at beginning
        long addBeginALStart = System.currentTimeMillis();
        ArrayList<RentalVehicle> arrayList = new ArrayList<>();
        for (int i =0; i < n; i++){
            arrayList.add(0, RentalVehicleFactory.newRandomRentalVehicle());
        }
        long addBeginALDuration = System.currentTimeMillis()-addBeginALStart;

        long addBeginLLStart = System.currentTimeMillis();
        LinkedList<RentalVehicle> linkedList = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            linkedList.add(0, RentalVehicleFactory.newRandomRentalVehicle());
        }
        long addBeginLLDuration = System.currentTimeMillis()-addBeginLLStart;



        //get at end

        long getEndALStart = System.currentTimeMillis();
        RentalVehicle r1 = arrayList.get(arrayList.size()-1);
        long getEndALDuration = System.currentTimeMillis()-getEndALStart;

        long getEndLLStart = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            linkedList.get(i);
        }
        long getEndLLDuration = System.currentTimeMillis()-getEndLLStart;

        System.out.println("Adding " + n + " to ArrayList: " + addBeginALDuration + "ms");
        System.out.println("Adding " + n + " to LinkedList: " + addBeginLLDuration + "ms");
        System.out.println("Getting " + n + " element from ArrayList: " + getEndALDuration + "ms");
        System.out.println("Getting " + n + " element from LinkedList: " + getEndLLDuration + "ms");
    }

    public static void testSelectionSort() {
        for (int n = 1000; n < 20000; n += 1000) {
            RentalVehicle.compareCounter = 0;
            List<RentalVehicle> rentalVehicles = randomList(n);
            Kollections.selectionSort(rentalVehicles);

            System.out.println(n + ";" + RentalVehicle.compareCounter);
        }
    }

    public static void testMergeSort() {
        for (int n = 1000; n < 200000; n += 1000) {
            RentalVehicle.compareCounter = 0;
            List<RentalVehicle> rentalVehicles = randomList(n);
            Kollections.mergeSort(rentalVehicles);

            System.out.println(n + ";" + RentalVehicle.compareCounter);
        }
    }

        public static void compareListMapToHashMap(int n) {
            long start;
            long duration;

            Map<RentalVehicle, String> listMap = new ListMap<>();
            Map<RentalVehicle, String> hashMap= new HashMap<>(n);

            fillMap(listMap, n);
            fillMap(hashMap, n);

            //Listmap test
            RentalVehicle.equalsCounter = 0;
            start = System.nanoTime();
            performGetOnMap(listMap,n);
            duration = System.nanoTime()-start;
            System.out.printf("%nListMap: n = %5d, equalsCount = %8d, nanoSec = %15d",n,RentalVehicle.equalsCounter,duration);

            //Hashmap test
            RentalVehicle.equalsCounter = 0;
            start = System.nanoTime();
            performGetOnMap(hashMap, n);
            duration = System.nanoTime()-start;
            System.out.printf("%nHashMap: n = %5d, equalsCount = %8d, nanoSec = %15d",n,RentalVehicle.equalsCounter,duration);
        }

    public static void compareArraySetToTreeSet(int n) {
        long start;
        long end;
        Set<RentalVehicle> arraySet = new ArraySet<>();
        Set<RentalVehicle> treeSet= new TreeSet<>();

        // ArraySet test
        RentalVehicle.equalsCounter = 0;
        RentalVehicle.compareCounter = 0;
        start = System.nanoTime();
        new Random().ints(n).forEach(i -> arraySet.add(RentalVehicleFactory.newRandomRentalVehicle()));
        end = System.nanoTime();
        System.out.printf("%nArraySet, n = %5d: equalscount : %-7d",n, RentalVehicle.equalsCounter);
        System.out.printf("%nArraySet, n = %5d: comparecount: %-7d",n, RentalVehicle.compareCounter);
        System.out.printf("%nArraySet, n = %5d: nanosec     : %-7d",n, end-start);


        // TreeSet test
        RentalVehicle.equalsCounter = 0;
        RentalVehicle.compareCounter = 0;
        start = System.nanoTime();
        new Random().ints(n).forEach(i -> treeSet.add(RentalVehicleFactory.newRandomRentalVehicle()));
        end = System.nanoTime();
        System.out.printf("%nTreeSet,  n = %5d: equalscount : %-7d",n, RentalVehicle.equalsCounter);
        System.out.printf("%nTreeSet,  n = %5d: comparecount: %-7d",n, RentalVehicle.compareCounter);
        System.out.printf("%nTreeSet,  n = %5d: nanosec     : %-7d",n, end-start);
    }

    private static void performGetOnMap(Map<RentalVehicle, String> map, int n) {
        for (int i = 0; i< n; i++) {
            map.get(RentalVehicleFactory.newFilledRentalvehicle(
                    "citroen",
                    140.0,
                    6,
                    VehicleType.VAN,
                    LocalDate.of(2023, 03, 30),
                    "AFD126"
            ));
        }
    }

    private static void fillMap(Map<RentalVehicle, String> map, int n) {
        for (int i = 0; i < n; i++) {
            // model, rentalVehicle, aantalPersons, Vehicle Type, manufactureDate, VehicleRegNr
            map.put(RentalVehicleFactory.newFilledRentalvehicle(
                    "citroen",
                    140.0,
                    6,
                    VehicleType.VAN,
                    LocalDate.of(2023, 03, 30),
                    "AFD126"
            ),"Ik ben de waarde "+i);
        }
    }


}
