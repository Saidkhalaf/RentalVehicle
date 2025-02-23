    import be.kdg.rentalVehicleProject.kollections.Kollections;
    import be.kdg.rentalVehicleProject.model.RentalVehicle;
    import be.kdg.rentalVehicleProject.model.RentalVehicleFactory;
    import be.kdg.rentalVehicleProject.model.VehicleType;

    import java.time.LocalDate;
    import be.kdg.rentalVehicleProject.kollections.lists.List;
    import java.util.stream.Stream;

    public class Demo_8 {
        public static void main(String[] args) {

            // Factory Tests !
            System.out.println("Empty RentalVehicle :");
            System.out.println(RentalVehicleFactory.newEmptyRentalVehicle());

            System.out.println("\nFilled RentalVehicle :");
            System.out.println(RentalVehicleFactory.newFilledRentalvehicle("BMW", 150.0, 6,
                    VehicleType.VAN ,LocalDate.of(2022,10,15), "ART123" ));

            System.out.println("\n30 random rentalVehicles gesorteerd op Model :");
            Stream.generate(RentalVehicleFactory::newRandomRentalVehicle).limit(30).sorted(RentalVehicle::compareTo).forEach(System.out::println);

            // 1) Test the random list function
            System.out.println("PerformanceTester");
            System.out.println("===================================");
            List<RentalVehicle> rentalVehicles = PerformanceTester.randomList(20);
            printList(rentalVehicles);

            // 2. Compare performance between ArrayList and LinkedList
            System.out.println("compareArrayListAndLinkedList");
            System.out.println("===================================");
            PerformanceTester.compareArrayListAndLinkedList(20000);
            System.out.println();

            //3) Test selectionSort
            System.out.println("\nSelectionSort\n===============");
            List<RentalVehicle> rentalVehicles2 = PerformanceTester.randomList(30);
            System.out.println("Before sorting:");
            printList(rentalVehicles2);

            System.out.println("\nAfter sorting:");
            Kollections.selectionSort(rentalVehicles2);
            printList(rentalVehicles2);

            // 4) Test mergeSort
            System.out.println("\n\nMergesort\n===============");
            List<RentalVehicle> rentalVehicles3 = PerformanceTester.randomList(30);
            System.out.println("Before sorting:");
            printList(rentalVehicles3);

            System.out.println("\nAfter sorting:");
            Kollections.mergeSort(rentalVehicles3);
            printList(rentalVehicles3);

            // 5. Test compareCounters for both sorting strategies
//            System.out.println("\n\nCompare counters");
//            System.out.println("\n===============");
//            System.out.println("SelectionSort");
//            PerformanceTester.testSelectionSort();
//
//            System.out.println("MergeSort");
//            PerformanceTester.testMergeSort();

            // 6. Test quickSort method
            List<RentalVehicle> rentalVehicles4 = PerformanceTester.randomList(30);

            System.out.println("\nQuickSort Before sorting:");
            System.out.println("===================");
            printList(rentalVehicles4);

            // Sort the list using quickSort
            Kollections.quickSort(rentalVehicles4);

            System.out.println("\nQuickSort After sorting:");
            System.out.println("===================");
            printList(rentalVehicles4);

            // 7. Searching
            List<RentalVehicle> rentalVehicle5 = PerformanceTester.randomList(30);
            Kollections.mergeSort(rentalVehicle5);

            // Search for a specific element
            RentalVehicle r1 = rentalVehicle5.get(7);

            // Search for an unexisting element
            RentalVehicle r2 = RentalVehicleFactory.newRandomRentalVehicle();

            // Run the research !
            System.out.println("Index of existing Vehicle: " + Kollections.lineairSearch(rentalVehicle5, r1));
            System.out.println("Index of existing Vehicle: " + Kollections.binarySearch(rentalVehicle5, r1));

            System.out.println("Index of non-existing Vehicle: " + Kollections.lineairSearch(rentalVehicle5, r2));
            System.out.println("Index of non-existing Vehicle: " + Kollections.binarySearch(rentalVehicle5, r2));

            // 8. ListMap vs HashMap
            PerformanceTester.compareListMapToHashMap(1000);

            // 9. ArraySet vs TreeSet
            System.out.println();
            PerformanceTester.compareArraySetToTreeSet(1000);







        }
        private static void printList(List<RentalVehicle> rentalVehicles){
            System.out.println();
            for (int i=0; i < rentalVehicles.size(); i++){
                System.out.println(rentalVehicles.get(i));
            }
        }
    }
